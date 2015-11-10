package fr.guehenneux.chess.move;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class CapturePromotion extends ChessMove {

	private ChessPlayer capturedPlayer;
	private Piece capturedPiece;
	private Piece replacementPiece;

	/**
	 * @param chess
	 * @param pawn
	 * @param destinationX
	 * @param destinationY
	 * @param replacementPiece
	 */
	public CapturePromotion(Chess chess, Pawn pawn, int destinationX, int destinationY, Piece replacementPiece) {

		super(chess, pawn, destinationX, destinationY);

		this.replacementPiece = replacementPiece;

		capturedPiece = chess.getPiece(destinationX, destinationY);
		capturedPlayer = capturedPiece.getPlayer();
	}

	@Override
	public void play() {

		chess.setPiece(departureX, departureY, null);
		chess.setPiece(destinationX, destinationY, replacementPiece);
		piece.incrementMoveCount();

		capturedPlayer.removePiece(capturedPiece);
		player.removePiece(piece);
		player.addPiece(replacementPiece);

		super.play();
	}

	@Override
	public void cancel() {

		chess.setPiece(departureX, departureY, piece);
		chess.setPiece(destinationX, destinationY, capturedPiece);
		piece.decrementMoveCount();

		capturedPlayer.addPiece(capturedPiece);
		player.addPiece(piece);
		player.removePiece(replacementPiece);

		super.cancel();
	}

	@Override
	public String toString() {
		return piece + getDepartureSquare() + 'x' + getDestinationSquare() + replacementPiece;
	}
}