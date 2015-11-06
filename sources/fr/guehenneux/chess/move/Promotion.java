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
public class Promotion extends AbstractMove {

	private Chess chess;
	private ChessPlayer player;
	private Color color;
	private Pawn pawn;
	private int x;
	private Piece replacementPiece;

	/**
	 * @param pawn
	 * @param replacementPiece
	 */
	public Promotion(Pawn pawn, Piece replacementPiece) {

		this.pawn = pawn;
		this.replacementPiece = replacementPiece;

		chess = pawn.getChess();
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

		chess.nextPlayer();
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

		chess.previousPlayer();
	}
}