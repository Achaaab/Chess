package fr.guehenneux.chess.move;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Guéhenneux
 */
public class Promotion extends ChessMove {

	private Piece replacementPiece;

	/**
	 * @param chess
	 * @param pawn
	 * @param destinationX
	 * @param destinationY
	 * @param replacementPiece
	 */
	public Promotion(Chess chess, Pawn pawn, int destinationX, int destinationY, Piece replacementPiece) {

		super(chess, pawn, destinationX, destinationY);

		this.replacementPiece = replacementPiece;
	}

	@Override
	public void play() {

		chess.setPiece(departureX, departureY, null);
		chess.setPiece(destinationX, destinationY, replacementPiece);

		player.removePiece(piece);
		player.addPiece(replacementPiece);

		piece.incrementMoveCount();

		super.play();
	}

	@Override
	public void cancel() {

		chess.setPiece(departureX, departureY, piece);
		chess.setPiece(destinationX, destinationY, null);

		player.addPiece(piece);
		player.removePiece(replacementPiece);

		piece.decrementMoveCount();

		super.cancel();
	}

	@Override
	public String toString() {
		return piece + getDepartureSquare() + '-' + getDestinationSquare() + replacementPiece;
	}
}