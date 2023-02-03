/* Explanation

In this example, GameState is a class representing a state in the game, isOver() returns whether the game is over, 
getChildren() returns a list of all possible next game states, and heuristicValue() returns an estimated value of 
the game state, used to determine the best move. The minimax method implements the core logic of alpha-beta pruning, 
using two values, alpha and beta, to keep track of the best possible moves and eliminate branches that are not worth exploring. 

*/

public class AlphaBetaPruning {
  // Returns the minimax value of the game state using alpha-beta pruning
  public int minimax(GameState state, int depth, int alpha, int beta, boolean maximizingPlayer) {
    // If the game is over or we've reached the desired depth, return the heuristic value of the game state
    if (state.isOver() || depth == 0) {
      return state.heuristicValue();
    }
    
    // If we're maximizing player, find the maximum value
    if (maximizingPlayer) {
      int maxValue = Integer.MIN_VALUE;
      for (GameState child : state.getChildren()) {
        int childValue = minimax(child, depth - 1, alpha, beta, false);
        maxValue = Math.max(maxValue, childValue);
        alpha = Math.max(alpha, maxValue);
        if (beta <= alpha) {
          break;
        }
      }
      return maxValue;
    } 
    // If we're minimizing player, find the minimum value
    else {
      int minValue = Integer.MAX_VALUE;
      for (GameState child : state.getChildren()) {
        int childValue = minimax(child, depth - 1, alpha, beta, true);
        minValue = Math.min(minValue, childValue);
        beta = Math.min(beta, minValue);
        if (beta <= alpha) {
          break;
        }
      }
      return minValue;
    }
  }
}