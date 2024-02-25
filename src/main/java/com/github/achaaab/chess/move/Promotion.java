package com.github.achaaab.chess.move;

import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.piece.Pawn;
import com.github.achaaab.chess.piece.Piece;

/**
 * @author Jonathan Gu�henneux
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