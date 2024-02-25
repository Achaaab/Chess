package com.github.achaaab.chess.move;

import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.piece.Pawn;
import com.github.achaaab.chess.piece.Piece;
import com.github.achaaab.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class CapturePromotion extends ChessMove {

	private final ChessPlayer capturedPlayer;
	private final Piece capturedPiece;
	private final Piece replacementPiece;

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
