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

	/**
	 * @param chess
	 * @param king
	 * @param destinationX
	 * @param destinationY
	 * @param rook
	 */
	public Castling(Chess chess, King king, int destinationX, int destinationY, Rook rook) {

		super(chess, king, destinationX, destinationY);

		this.rook = rook;

		rookDepartureX = rook.getX();

		if (departureX < rookDepartureX) {
			rookDestinationX = destinationX - 1;
		} else {
			rookDestinationX = destinationX + 1;
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
}