package fr.guehenneux.chess.move;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Guéhenneux
 */
public class Advance extends ChessMove {

	/**
	 * @param chess
	 * @param piece
	 * @param destinationX
	 * @param destinationY
	 */
	public Advance(Chess chess, Piece piece, int destinationX, int destinationY) {
		super(chess, piece, destinationX, destinationY);
	}

	@Override
	public void play() {

		departureX = piece.getX();
		departureY = piece.getY();

		chess.setPiece(departureX, departureY, null);
		chess.setPiece(destinationX, destinationY, piece);
		piece.incrementMoveCount();

		super.play();
	}

	@Override
	public void cancel() {

		chess.setPiece(destinationX, destinationY, null);
		chess.setPiece(departureX, departureY, piece);
		piece.decrementMoveCount();

		super.cancel();
	}

	@Override
	public String toString() {
		return piece + getDepartureSquare() + '-' + getDestinationSquare();
	}
}