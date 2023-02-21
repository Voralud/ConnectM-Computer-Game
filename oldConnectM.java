import java.util.Scanner;
import java.util.Random;

/**
 * The connectM class represents a game of Connect M.
 * It allows one human player and one AI to take turns dropping pieces
 * into a N x N  quadratic grid until someone wins by getting "M" disks  
 * that must be connected contiguously in the grid in a row.
 */
public class oldConnectM {
   
   /** The dimensions of the quadratic grid. (At least 3 but no larger than 10) */
    static final int N = 5;
    
  /** The number of consecutive pieces needed to win. (Higher than 1 but no higher than N) */
    static final int M = 4;

    /** The number of rows on the game board. */
    static final int ROWS = N;

    /** The number of columns on the game board. */
    static final int COLUMNS = N;

    /** The game board represented as a 2D integer array. */
    static int[][] board = new int[ROWS][COLUMNS];
    static int[][] cBoard = new int[ROWS][COLUMNS];

    /** The Scanner object used to get user input. */
    static Scanner scanner = new Scanner(System.in);

    /** The number of the player whose turn it is. 1 or 2. */
    static int turn = 1;
    
   /** The maximum depth for the minimax algorithm */
    static final int MAX_DEPTH = 100;

    /**
     * The main method that runs the Connect M game.
     * @param args command line arguments
     */
    public static void main(String[] args) {
         initializeBoard();
        boolean winner = false;

        while (!winner) {
        cBoard = board;
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
                int col = minimax(MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
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
    * Initializes the Board with all zeroes
    */
    static void initializeBoard() {
    
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
               board[row][col] = 0;
            }
        }
    }

    /**
    * Drops a piece into the specified column for the current turn.
    * Pieces are dropped from the top down, filling the lowest empty row first.
    * @param col the column to drop the piece into
    */
    static void dropPiece(int col) {
        
    	if(checkIfFull(col) == true) {
    		System.out.println("Column is full, choose a different column.");
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
    
    
    /**
    TODO:Add description
    */
    static void unDropPiece(int col) {
    	for (int row = 0; row < ROWS; row++) {
    		
        	if (board[row][col] == 1 || board[row][col] == 2) {
                board[row][col] = 0;
                break;
            }
        }
    }
    
    static boolean checkIfFull(int col) {  	
    	return (board[0][col] == 1 || board[0][col]==2); 	
    }


   /**
    * Checks the current state of the game board to see if either player has won.
    * @param M the number of consecutive pieces needed to win
    * @return true if either player has won, false otherwise
    */
    static boolean checkForWin(int M) {
    	//Check for tie
    	boolean isTie = false;
    	int fullCounter = 0;
    	
    	for(int col=0;col<COLUMNS;col++){
    		if(checkIfFull(col) == true) {
    			fullCounter++;
    		}
    		if(fullCounter == (COLUMNS)) {
    			printBoard();
    			System.out.println("It's a tie!");
    			System.exit(0);
    		}
    	}
    	
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
    
       
/**
 * Returns the best move for the AI player using the minimax algorithm with alpha-beta pruning.
 * @param depth the current depth of the search
 * @param alpha the alpha value for pruning
 * @param beta the beta value for pruning
 * @param maximizingPlayer true if it's the AI player's turn, false otherwise
 * @return the index of the column that the AI player should play in
 */
static int minimax(int depth, int alpha, int beta, boolean maximizingPlayer) {
    int bestScore;
    int bestCol = -1;

    if (depth == 0 || checkForWinC(M)) {
        // Base case: we've reached the maximum search depth or the game is over
        bestScore = evaluate(2, M);  // Evaluate the board from the perspective of the AI player
        return bestScore;
    }

    if (maximizingPlayer) {
        bestScore = Integer.MIN_VALUE;
        for (int col = 0; col < COLUMNS; col++) {
            if (!checkIfFullC(col)) {
                dropPieceC(col);
                int score = minimax(depth - 1, alpha, beta, false);
                if (score > bestScore) {
                    bestScore = score;
                    bestCol = col;
                }
                unDropPieceC(col);
                alpha = Math.max(alpha, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }
        }
    } else {
        bestScore = Integer.MAX_VALUE;
        for (int col = 0; col < COLUMNS; col++) {
            if (!checkIfFullC(col)) {
                dropPieceC(col);
                int score = minimax(depth - 1, alpha, beta, true);
                if (score < bestScore) {
                    bestScore = score;
                    bestCol = col;
                }
                unDropPieceC(col);
                beta = Math.min(beta, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }
        }
    }

    if (depth == MAX_DEPTH) {
        // We've finished the search at the root node, so return the best column to play in
        return bestCol;
    } else {
        return bestScore;
    }
}

/* TODO: FIXTHIS
This code evaluates a game board for a game similar to Tic-Tac-Toe, where the goal is to get m consecutive pieces in a row, column, or diagonal. The function takes in a 2D character array representing the game board, a character representing the player's piece, and an integer m representing the number of consecutive pieces required to win. The function returns an integer score representing the "goodness" of the current game board for the player, with a higher score indicating a better position.

The code first evaluates each row and column for the number of consecutive pieces the player has, adding 1000 to the score for m consecutive pieces and 100 for more than m. It then evaluates each diagonal in a similar fashion.

The code then evaluates the difference in the number of pieces the player has compared to the opponent, adding this to the score. */

public static int evaluate(int player, int m) {
    int score = 0;
    int rows = N;
    int cols = N;
    
    // Evaluate Rows
    for (int i = 0; i < rows; i++) {
        int count = 0;
        for (int j = 0; j < cols; j++) {
            if (cBoard[i][j] == player) {
                count++;
                if (count == m) {
                    score += 1000;
                }
                else if (count > m) {
                    score += 100;
                }
            }
            else {
                count = 0;
            }
        }
    }
    
    // Evaluate Columns
    for (int j = 0; j < cols; j++) {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            if (cBoard[i][j] == player) {
                count++;
                if (count == m) {
                    score += 1000;
                }
                else if (count > m) {
                    score += 100;
                }
            }
            else {
                count = 0;
            }
        }
    }
    
    // Evaluate Diagonals
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (cBoard[i][j] == player) {
                // Check downward diagonal
                int count = 1;
                int x = i + 1;
                int y = j + 1;
                while (x < rows && y < cols && cBoard[x][y] == player) {
                    count++;
                    x++;
                    y++;
                }
                if (count == m) {
                    score += 1000;
                }
                else if (count > m) {
                    score += 100;
                }
                
                // Check upward diagonal
                count = 1;
                x = i - 1;
                y = j + 1;
                while (x >= 0 && y < cols && cBoard[x][y] == player) {
                    count++;
                    x--;
                    y++;
                }
                if (count == m) {
                    score += 1000;
                }
                else if (count > m) {
                    score += 100;
                }
            }
        }
    }
    
    // Evaluate Piece Count
    int playerCount = 0;
    int opponentCount = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (cBoard[i][j] == player) {
                playerCount++;
            }
            else if (cBoard[i][j] != ' ') {
                opponentCount++;
            }
        }
    }
    score += playerCount - opponentCount;
    
     return score;
   }
   
   //Helper functions for minimax (MIGH WANT TO REFACTOR THESE?!?)
        /**
    * Drops a piece into the specified column for the current turn.
    * Pieces are dropped from the top down, filling the lowest empty row first.
    * @param col the column to drop the piece into
    */
    static void dropPieceC(int col) {
        
           	if(checkIfFullC(col) == true) {
    		System.out.println("Column is full, choose a different column.");
    		if(turn == 1) {
    			turn = 2;
    		}
    		else if(turn == 2) {
    			turn = 1;
    		}
    		return;
    	}
    	for (int row = ROWS - 1; row >= 0; row--) {
    		
        	if (cBoard[row][col] == 0) {
                cBoard[row][col] = turn;
                break;
            }
        }
    }
    
    
    /**
    TODO:Add description
    */
    static void unDropPieceC(int col) {
    	for (int row = 0; row < ROWS; row++) {
    		
        	if (cBoard[row][col] == 1 || cBoard[row][col] == 2) {
                cBoard[row][col] = 0;
                break;
            }
        }
    }
    
    static boolean checkIfFullC(int col) {  	
    	return (cBoard[0][col] == 1 || cBoard[0][col]==2); 	
    }


   /**
    * Checks the current state of the game board to see if either player has won.
    * @param M the number of consecutive pieces needed to win
    * @return true if either player has won, false otherwise
    */
    static boolean checkForWinC(int M) {
    	
    	// Check for horizontal win
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - M + 1; col++) {
                int count = 0;
                for (int i = 0; i < M; i++) {
                    if (cBoard[row][col + i] == turn) {
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
                    if (cBoard[row + i][col] == turn) {
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
                     if (cBoard[row + i][col + i] == turn) {
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
                    if (cBoard[row - i][col + i] == turn) {
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
