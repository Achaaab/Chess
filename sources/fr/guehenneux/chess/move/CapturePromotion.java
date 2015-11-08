package fr.guehenneux.chess.move;

import fr.guehenneux.alphabeta.AbstractMove;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class CapturePromotion extends AbstractMove {

	private Chess chess;
	private ChessPlayer capturingPlayer;
	private ChessPlayer capturedPlayer;
	private Color color;
	private Pawn pawn;
	private int x;
	private Piece replacementPiece;
	private Piece capturedPiece;
	private int captureX;

	/**
	 * @param pawn
	 * @param capturePiece
	 * @param replacementPiece
	 */
	public CapturePromotion(Pawn pawn, Piece capturedPiece, Piece replacementPiece) {

		this.pawn = pawn;
		this.replacementPiece = replacementPiece;
		this.capturedPiece = capturedPiece;

		chess = pawn.getChess();
		capturingPlayer = pawn.getPlayer();
		color = capturingPlayer.getColor();
		x = pawn.getX();
		captureX = capturedPiece.getX();
		capturedPlayer = capturedPiece.getPlayer();
	}

	@Override
	public void play() {

		switch (color) {

		case WHITE:

			chess.setPiece(x, 6, null);
			chess.setPiece(captureX, 7, replacementPiece);
			break;

		case BLACK:
			chess.setPiece(x, 1, null);
			chess.setPiece(captureX, 0, replacementPiece);
			break;
		}

		capturingPlayer.removePiece(pawn);
		capturingPlayer.addPiece(replacementPiece);
		capturedPlayer.removePiece(capturedPiece);
		pawn.incrementMoveCount();

		chess.nextPlayer();
	}

	@Override
	public void cancel() {

		switch (color) {

		case WHITE:
			chess.setPiece(captureX, 7, capturedPiece);
			chess.setPiece(x, 6, pawn);
			break;

		case BLACK:
			chess.setPiece(captureX, 0, capturedPiece);
			chess.setPiece(x, 1, pawn);
			break;
		}

		capturingPlayer.removePiece(replacementPiece);
		capturingPlayer.addPiece(pawn);
		capturedPlayer.addPiece(capturedPiece);
		pawn.decrementMoveCount();

		chess.previousPlayer();
	}
}