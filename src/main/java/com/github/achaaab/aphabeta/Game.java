package com.github.achaaab.aphabeta;

import java.util.Stack;

/**
 * @author Jonathan Guéhenneux
 */
public interface Game<M extends Move> {

	/**
	 * @param player
	 * @return the heuristic value for the specified player
	 */
	double getHeuristicValue(Player<M> player);

	/**
	 * @return the winning move value
	 */
	double getWinningMoveValue();

	/**
	 * @return the current player
	 */
	Player<M> getCurrentPlayer();

	/**
	 * 
	 */
	void nextPlayer();

	/**
	 * 
	 */
	void previousPlayer();

	/**
	 * @return the winner if any or {@code null} if none (game not finished or draw)
	 */
	Player<M> getWinner();

	/**
	 * @return whether the game is draw
	 */
	boolean isDraw();

	/**
	 * @param move
	 */
	void addMove(M move);

	/**
	 *
	 */
	void removeMove();

	/**
	 * @return the last move if any, {@code null} if none
	 */
	M getLastMove();

	/**
	 * @return the played moves
	 */
	Stack<M> getMoves();

	/**
	 * update the user interface
	 */
	void updateUi();
}
