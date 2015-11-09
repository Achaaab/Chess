package fr.guehenneux.chess.move;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class Promotion extends ChessMove {

	private ChessPlayer player;
	private Color color;
	private Pawn pawn;
	private int x;
	private Piece replacementPiece;

	/**
	 * @param chess
	 * @param pawn
	 * @param replacementPiece
	 */
	public Promotion(Chess chess, Pawn pawn, Piece replacementPiece) {

		super(chess);

		this.pawn = pawn;
		this.replacementPiece = replacementPiece;

		player = pawn.getPlayer();
		color = player.getColor();
		x = pawn.getX();
	}

	@Override
	public void play() {

		switch (color) {

		case WHITE:
			chess.setPiece(x, 6, null);
			chess.setPiece(x, 7, replacementPiece);
			break;

		case BLACK:
			chess.setPiece(x, 1, null);
			chess.setPiece(x, 0, replacementPiece);
			break;
		}

		player.removePiece(pawn);
		player.addPiece(replacementPiece);
		pawn.incrementMoveCount();

		super.play();
	}

	@Override
	public void cancel() {

		switch (color) {

		case WHITE:
			chess.setPiece(x, 7, null);
			chess.setPiece(x, 6, pawn);
			break;

		case BLACK:
			chess.setPiece(x, 0, null);
			chess.setPiece(x, 1, pawn);
			break;
		}

		player.removePiece(replacementPiece);
		player.addPiece(pawn);
		pawn.decrementMoveCount();

		super.cancel();
	}
}