//Daniel Ulate Leal and Elena Lucci
import java.util.Scanner;

/**
 * The connectM class represents a game of Connect M.
 * It allows one human player and one AI to take turns dropping pieces
 * into a N x N  quadratic grid until someone wins by getting "M" disks  
 * that must be connected contiguously in the grid in a row.
 */

public class connectM {

   private static int N; // size of the board
   private static int M; // number of disks to connect contiguously
   private static int[][] board; // the game board
   private static int humanPlayer; // the player number for the human player
   private static int computerPlayer; // the player number for the computer player
   private static int turn; // the current turn number
   private static int maxDepth; // the maximum depth to search in the game tree

    /**
     * The main method that runs the Connect M game.
     * @param args command line arguments
     */
   public static void main(String[] args) {
      if (args.length != 3) {
         System.out.println("Usage: connectM N M H");
         return;
      }
      N = Integer.parseInt(args[0]);
      M = Integer.parseInt(args[1]);
      int humanFirst = Integer.parseInt(args[2]);
      if (N < 3 || N > 10 || M < 2 || M > N || humanFirst < 0 || humanFirst > 1) {
         System.out.println("Invalid parameters");
         return;
      }
      board = new int[N][N];
      humanPlayer = 1;
      computerPlayer = 2;
      turn = 1;
      maxDepth = 10; // the maximum depth to search in the game tree (can be adjusted)
      if (humanFirst == 0) {
         int col = computerMove();
         dropDisk(col, computerPlayer);
         turn++;
      }
      while (true) {
         printBoard();
         int col = getHumanMove();
         dropDisk(col, humanPlayer);
         if (checkWin(humanPlayer)) {
            printBoard();
            System.out.println("You win!");
            break;
         }
         if (checkDraw()) {
            printBoard();
            System.out.println("Draw!");
            break;
         }
         col = computerMove();
         dropDisk(col, computerPlayer);
         turn++;
         if (checkWin(computerPlayer)) {
            printBoard();
            System.out.println("Computer wins!");
            break;
         }
         if (checkDraw()) {
            printBoard();
            System.out.println("Draw!");
            break;
         }
      }
   }

   /**
    * Prints the current state of the board to the console.
    */
   private static void printBoard() {
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if (board[i][j] == 0) {
               System.out.print(".");
            } else if (board[i][j] == humanPlayer) {
               System.out.print("O");
            } else {
               System.out.print("X");
            }
            System.out.print(" ");
         }
         System.out.println();
      }
      for (int j = 0; j < N; j++) {
         System.out.print("- ");
      }
      System.out.println();
      for (int j = 0; j < N; j++) {
         System.out.print(j + " ");
      }
      System.out.println();
   }

    /**
    * Get the human player's move
    * @return col
    */
   private static int getHumanMove() {
      Scanner scanner = new Scanner(System.in);
      while (true) {
         System.out.print("Enter column (0-" + (N-1) + "): ");
         int col = scanner.nextInt();
         if (col < 0 || col >= N || board[0][col] != 0) {
            System.out.println("Invalid move");
         } else {
            return col;
         }
      }
   }
    
   /**
    * Drops a piece into the specified column for the current turn.
    * Pieces are dropped from the top down, filling the lowest empty row first.
    * @param col the column to drop the piece into
    * @param player the player who is dropping the piece
    */
   private static void dropDisk(int col, int player) {
      for (int i = N-1; i >= 0; i--) {
         if (board[i][col] == 0) {
            board[i][col] = player;
            return;
         }
      }
   }
   
   private static int findEmptyRow(int col) {
    for (int row = N - 1; row >= 0; row--) {
        if (board[row][col] == 0) {
            return row;
        }
    }
    return -1; // Column is full
}
   
    /**
    * Checks the current state of the game board to see if a player has won.
    * @param player the player who's turn it is
    * @return true if the player has won, false otherwise
    */
   private static boolean checkWin(int player) {
       // Check rows
      for (int i = 0; i < N; i++) {
         for (int j = 0; j <= N-M; j++) {
            boolean win = true;
            for (int k = 0; k < M; k++) {
               if (board[i][j+k] != player) {
                  win = false;
                  break;
               }
            }
            if (win) {
               return true;
            }
         }
      }
       // Check columns
      for (int j = 0; j < N; j++) {
         for (int i = 0; i <= N-M; i++) {
            boolean win = true;
            for (int k = 0; k < M; k++) {
               if (board[i+k][j] != player) {
                  win = false;
                  break;
               }
            }
            if (win) {
               return true;
            }
         }
      }
       // Check diagonals
      for (int i = 0; i <= N-M; i++) {
         for (int j = 0; j <= N-M; j++) {
            boolean win = true;
            for (int k = 0; k < M; k++) {
               if (board[i+k][j+k] != player) {
                  win = false;
                  break;
               }
            }
            if (win) {
               return true;
            }
         }
      }
      for (int i = 0; i <= N-M; i++) {
         for (int j = M-1; j < N; j++) {
            boolean win = true;
            for (int k = 0; k < M; k++) {
               if (board[i+k][j-k] != player) {
                  win = false;
                  break;
               }
            }
            if (win) {
               return true;
            }
         }
      }
      return false;
   }
   
  /** 
  * Check if the game has ended in a draw
  * @return true if draw, false otherwise
  */
   private static boolean checkDraw() {
      for (int j = 0; j < N; j++) {
         if (board[0][j] == 0) {
            return false;
         }
      }
      return true;
   }
   
 
    /**
    * Get the computer player's move
    * @return col
    */
   private static int computerMove() {
    int bestCol = 0;
    int alpha = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
    int maxVal = Integer.MIN_VALUE;
    
    // Try each column and evaluate the best move using alpha-beta pruning
    for (int col = 0; col < N; col++) {
        if (board[0][col] == 0) {  // Check if column is not full
            int row = findEmptyRow(col); // Get the empty row in the column
            board[row][col] = computerPlayer; // Make the move
            
            int val = min(alpha, beta, 1);
            
            // Update the best move so far
            if (val > maxVal) {
                maxVal = val;
                bestCol = col;
            }
            
            board[row][col] = 0; // Undo the move
        }
    }
    
    return bestCol;
   }

   /**
    * Evaluates the minimum value of the game tree for the given state using alpha-beta pruning algorithm.
    * @param alpha the alpha value for the alpha-beta pruning
    * @param beta the beta value for the alpha-beta pruning
    * @param depth the current depth of the search in the game tree
    * @return the minimum value of the game tree for the given state
    */
   private static int min(int alpha, int beta, int depth) {
       if (checkWin(humanPlayer)) {
           return -1000; // The human wins
       } else if (checkWin(computerPlayer)) {
           return 1000; // The computer wins
       } else if (checkDraw() || depth == maxDepth) {
           return 0; // Draw or max depth reached
       }
       
       int minVal = Integer.MAX_VALUE;
       
       // Try each column and evaluate the worst outcome using alpha-beta pruning
       for (int col = 0; col < N; col++) {
           if (board[0][col] == 0) { // Check if column is not full
               int row = findEmptyRow(col); // Get the empty row in the column
               board[row][col] = humanPlayer; // Make the move
               
               int val = max(alpha, beta, depth + 1);
               
               // Update the minimum value so far and prune the search if possible
               if (val < minVal) {
                   minVal = val;
               }
               if (minVal <= alpha) {
                   board[row][col] = 0; // Undo the move
                   return minVal; // Prune the search
               }
               beta = Math.min(beta, minVal);
               board[row][col] = 0; // Undo the move
           }
       }
       
       return minVal;
   }
   
   /**
    * Evaluates the maximum value of the game tree for the given state using alpha-beta pruning algorithm.
    * @param alpha the alpha value for the alpha-beta pruning
    * @param beta the beta value for the alpha-beta pruning
    * @param depth the current depth of the search in the game tree
    * @return the maximum value of the game tree for the given state
    */
   private static int max(int alpha, int beta, int depth) {
       if (checkWin(humanPlayer)) {
           return -1000; // The human wins
       } else if (checkWin(computerPlayer)) {
           return 1000; // The computer wins
       } else if (checkDraw() || depth == maxDepth) {
           return 0; // Draw or max depth reached
       }
    
    int maxVal = Integer.MIN_VALUE;
   
   // Try each column and evaluate the best outcome using alpha-beta pruning
   for (int col = 0; col < N; col++) {
       if (board[0][col] == 0) { // Check if column is not full
           int row = findEmptyRow(col); // Get the empty row in the column
           board[row][col] = computerPlayer; // Make the move
           
           int val = min(alpha, beta, depth + 1);
           
           // Update the maximum value so far and prune the search if possible
           if (val > maxVal) {
               maxVal = val;
           }
           if (maxVal >= beta) {
               board[row][col] = 0; // Undo the move
               return maxVal; // Prune the search
           }
           alpha = Math.max(alpha, maxVal);
           board[row][col] = 0; // Undo the move
       }
   }
   
   return maxVal;
   }
   
}
