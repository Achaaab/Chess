package fr.guehenneux.chess.move;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class EnPassant extends ChessMove {

	private Piece capturedPiece;
	private ChessPlayer capturedPlayer;

	private int captureY;

	/**
	 * @param chess
	 * @param pawn
	 * @param destinationX
	 * @param destinationY
	 */
	public EnPassant(Chess chess, Pawn pawn, int destinationX, int destinationY) {

		super(chess, pawn, destinationX, destinationY);

		if (destinationY == 5) {
			captureY = 4;
		} else {
			captureY = 3;
		}

		capturedPiece = chess.getPiece(destinationX, captureY);
		capturedPlayer = capturedPiece.getPlayer();
	}

	@Override
	public void play() {

		chess.setPiece(departureX, departureY, null);
		chess.setPiece(destinationX, destinationY, piece);
		chess.setPiece(destinationX, captureY, null);

		piece.incrementMoveCount();

		capturedPlayer.removePiece(capturedPiece);

		super.play();
	}

	@Override
	public void cancel() {

		chess.setPiece(departureX, departureY, piece);
		chess.setPiece(destinationX, destinationY, null);
		chess.setPiece(destinationX, captureY, capturedPiece);

		piece.decrementMoveCount();

		capturedPlayer.addPiece(capturedPiece);

		super.cancel();
	}

	@Override
	public String toString() {
		return piece + getDepartureSquare() + 'x' + getDestinationSquare() + " e.p.";
	}
}