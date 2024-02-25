package com.github.achaaab.aphabeta;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public abstract class TwoPlayersZeroSumGame<M extends Move> extends ZeroSumGame<M> {

	protected Player<M> player0;
	protected Player<M> player1;

	protected Player<M> currentPlayer;

	@Override
	public Player<M> getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public void nextPlayer() {

		if (currentPlayer == player0) {
			currentPlayer = player1;
		} else {
			currentPlayer = player0;
		}
	}

	@Override
	public void previousPlayer() {
		nextPlayer();
	}
}
