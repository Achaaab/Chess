package fr.guehenneux.chess.move;

import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class MovePair {

	private int number;
	private Move whiteMove;
	private Move blackMove;

	/**
	 * @param number
	 * @param whiteMove
	 * @param blackMove
	 */
	public MovePair(int number, Move whiteMove, Move blackMove) {

		this.number = number;
		this.whiteMove = whiteMove;
		this.blackMove = blackMove;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the whiteMove
	 */
	public Move getWhiteMove() {
		return whiteMove;
	}

	/**
	 * @return the blackMove
	 */
	public Move getBlackMove() {
		return blackMove;
	}
}