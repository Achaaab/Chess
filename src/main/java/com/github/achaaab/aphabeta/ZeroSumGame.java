package com.github.achaaab.aphabeta;

import java.util.Stack;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public abstract class ZeroSumGame<M extends Move> implements Game<M>, Runnable {

	protected Stack<M> moves;

	/**
	 * 
	 */
	public ZeroSumGame() {
		moves = new Stack<>();
	}

	@Override
	public void run() {

		Player<M> player;
		Move move;

		while (getWinner() == null && !isDraw()) {

			player = getCurrentPlayer();
			move = player.getMove();
			move.play();

			updateUi();
		}

		System.out.println(getWinner());
	}

	/**
	 * 
	 */
	public abstract void updateUi();

	@Override
	public M getLastMove() {

		M lastMove;

		if (moves.isEmpty()) {
			lastMove = null;
		} else {
			lastMove = moves.peek();
		}

		return lastMove;
	}

	@Override
	public Stack<M> getMoves() {
		return moves;
	}

	@Override
	public void addMove(M move) {
		moves.push(move);
	}

	@Override
	public void removeMove() {
		moves.pop();
	}
}
