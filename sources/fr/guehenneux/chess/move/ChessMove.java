package fr.guehenneux.chess.move;

import fr.guehenneux.alphabeta.AbstractMove;
import fr.guehenneux.chess.Chess;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class ChessMove extends AbstractMove {

	protected Chess chess;

	/**
	 * @param chess
	 */
	public ChessMove(Chess chess) {

		super(chess);
		this.chess = chess;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public String getSquareString(int x, int y) {

		char file = (char) ('a' + x);
		char rank = (char) ('1' + y);

		return Character.toString(file) + Character.toString(rank);
	}
}
