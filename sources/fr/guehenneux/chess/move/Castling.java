package fr.guehenneux.chess.move;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.King;
import fr.guehenneux.chess.piece.Rook;

/**
 * @author Jonathan Guéhenneux
 */
public class Castling extends ChessMove {

	private Rook rook;

	private int rookDepartureX;
	private int rookDestinationX;

	private boolean castlingShort;

	/**
	 * @param chess
	 * @param king
	 * @param destinationX
	 * @param destinationY
	 * @param rook
	 * @param castlingShort
	 */
	public Castling(Chess chess, King king, int destinationX, int destinationY, Rook rook) {

		super(chess, king, destinationX, destinationY);

		this.rook = rook;

		rookDepartureX = rook.getX();

		if (destinationX == 2) {

			rookDestinationX = 3;
			castlingShort = false;

		} else {

			rookDestinationX = 5;
			castlingShort = true;
		}
	}

	@Override
	public void play() {

		chess.setPiece(departureX, departureY, null);
		chess.setPiece(rookDepartureX, departureY, null);

		chess.setPiece(destinationX, destinationY, piece);
		chess.setPiece(rookDestinationX, destinationY, rook);

		piece.incrementMoveCount();
		rook.incrementMoveCount();

		super.play();
	}

	@Override
	public void cancel() {

		chess.setPiece(departureX, departureY, piece);
		chess.setPiece(rookDepartureX, departureY, rook);

		chess.setPiece(destinationX, destinationY, null);
		chess.setPiece(rookDestinationX, destinationY, null);

		piece.decrementMoveCount();
		rook.decrementMoveCount();

		super.cancel();
	}

	@Override
	public String toString() {
		return castlingShort ? "0-0" : "0-0-0";
	}
}