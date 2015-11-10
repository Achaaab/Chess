package fr.guehenneux.chess.move;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class Capture extends ChessMove {

	private Piece capturedPiece;
	private ChessPlayer capturedPlayer;

	/**
	 * @param chess
	 * @param capturingPiece
	 * @param capturedPiece
	 */
	public Capture(Chess chess, Piece capturingPiece, int destinationX, int destinationY) {

		super(chess, capturingPiece, destinationX, destinationY);

		capturedPiece = chess.getPiece(destinationX, destinationY);
		capturedPlayer = capturedPiece.getPlayer();
	}

	@Override
	public void play() {

		chess.setPiece(departureX, departureY, null);
		chess.setPiece(destinationX, destinationY, piece);

		piece.incrementMoveCount();

		capturedPlayer.removePiece(capturedPiece);

		super.play();
	}

	@Override
	public void cancel() {

		chess.setPiece(departureX, departureY, piece);
		chess.setPiece(destinationX, destinationY, capturedPiece);

		piece.decrementMoveCount();

		capturedPlayer.addPiece(capturedPiece);

		super.cancel();
	}

	@Override
	public String toString() {
		return piece + getDepartureSquare() + 'x' + getDestinationSquare();
	}
}