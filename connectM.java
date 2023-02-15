import java.util.Scanner;
import java.util.Random;

/**
 * The connectM class represents a game of Connect M.
 * It allows one human player and one AI to take turns dropping pieces
 * into a N x N  quadratic grid until someone wins by getting "M" disks  
 * that must be connected contiguously in the grid in a row.
 */
public class connectM {
   
   /** The dimensions of the quadratic grid. (At least 3 but no larger than 10) */
    static final int N = 5;
    
  /** The number of consecutice pieces needed to win. (Higher than 1 but no higher than N) */
    static final int M = 4;

    /** The number of rows on the game board. */
    static final int ROWS = N;

    /** The number of columns on the game board. */
    static final int COLUMNS = N;

    /** The game board represented as a 2D integer array. */
    static int[][] board = new int[ROWS][COLUMNS];

    /** The Scanner object used to get user input. */
    static Scanner scanner = new Scanner(System.in);

    /** The number of the player whose turn it is. 1 or 2. */
    static int turn = 1;
    
     /** Random number generator for AI moves */
     //TODO: To be replaced with hueristic algorithm
    static Random random = new Random();
    

    /**
     * The main method that runs the Connect M game.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        boolean winner = false;

        while (!winner) {
            if (turn == 1) {
                // Human player
                printBoard();
                System.out.print("Player " + turn + " choose a column (1-" + N + "): ");
                int col = scanner.nextInt() - 1;
                System.out.println();
                while (col < 0 || col >= COLUMNS) {
                    System.out.print("\nInvalid column! Choose again (1-" + N + "): ");
                    col = scanner.nextInt() - 1;
                    System.out.println();
                }
                dropPiece(col);
            } else {
                // AI player
                int col = random.nextInt(N);//TODO: MAKE IT SMART
                System.out.println("Computer chose column " + (col + 1));
                dropPiece(col);
            }
            winner = checkForWin(M);
            turn = (turn == 1) ? 2 : 1;
        }
        printBoard();
        turn = (turn == 1) ? 2 : 1;
        if(turn == 2){
        System.out.println("Computer wins!");
        } else {
        System.out.println("Player " + turn + " wins!");
        }
    }

    /**
    * Prints the current state of the board to the console.
    */
    static void printBoard() {
    
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
               if(board[row][col] == 0)
               {
                  System.out.print("|   ");
               }
               else if(board[row][col] == 1)
               {
                System.out.print("| X ");
               }
               else if(board[row][col] == 2)
               {
                System.out.print("| O ");
               }
               else{}
            }
            System.out.println("|   ");
        }
    }

    /**
    * Drops a piece into the specified column for the current turn.
    * Pieces are dropped from the top down, filling the lowest empty row first.
    * @param col the column to drop the piece into
    */
    static void dropPiece(int col) {
        
    	if(checkIfFull(col) == true) {
    		if(turn == 1) {
    			turn = 2;
    		}
    		else if(turn == 2) {
    			turn = 1;
    		}
    		return;
    	}
    	for (int row = ROWS - 1; row >= 0; row--) {
    		
        	if (board[row][col] == 0) {
                board[row][col] = turn;
                break;
            }
        }
    }
    
    static boolean checkIfFull(int col) {
    	
    	boolean isFull = false;
    	
    	if(board[0][col] == 1 || board[0][col]==2) {
    		System.out.println("Column is full, choose a different column.");
    		isFull = true;
    	}
    	
    	return isFull;
    	
    }



   /**
    * Checks the current state of the game board to see if either player has won.
    * @param M the number of consecutive pieces needed to win
    * @return true if either player has won, false otherwise
    */
    static boolean checkForWin(int M) {
    	
    	/*if() {
    		//check for draw
    		System.exit(0);
    	}*/
    	// Check for horizontal win
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - M + 1; col++) {
                int count = 0;
                for (int i = 0; i < M; i++) {
                    if (board[row][col + i] == turn) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == M) {
                    return true;
                }
            }
        }

        // Check for vertical win
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS - M + 1; row++) {
                int count = 0;
                for (int i = 0; i < M; i++) {
                    if (board[row + i][col] == turn) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == M) {
                    return true;
                }
            }
        }

        // Check for diagonal win (left to right)
        for (int row = 0; row < ROWS - M + 1; row++) {
            for (int col = 0; col < COLUMNS - M + 1; col++) {
                int count = 0;
                for (int i = 0; i < M; i++) {
                     if (board[row + i][col + i] == turn) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == M) {
                    return true;
                }
            }
        }

        // Check for diagonal win (right to left)
        for (int row = M - 1; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - M + 1; col++) {
                int count = 0;
                for (int i = 0; i < M; i++) {
                    if (board[row - i][col + i] == turn) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == M) {
                    return true;
                }
            }
        }

        return false;
    }
}