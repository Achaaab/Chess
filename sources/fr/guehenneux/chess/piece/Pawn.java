package fr.guehenneux.chess.piece;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.move.Advance;
import fr.guehenneux.chess.move.Capture;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class Pawn extends AbstractPiece {

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Pawn(Chess chess, ChessPlayer player, Color color, int x, int y) {
		super(chess, player, color, PAWN_VALUE, x, y, color == Color.WHITE ? WHITE_PAWN : BLACK_PAWN);
	}

	@Override
	public List<Move> getMoves() {

		Piece piece;

		List<Move> moves = new ArrayList<>();

		switch (color) {

		case WHITE:

			if (y == 1 && chess.getPiece(x, 2) == null && chess.getPiece(x, 3) == null) {
				moves.add(new Advance(this, x, 3));
			}

			if (y < 6) {

				if (chess.getPiece(x, y + 1) == null) {
					moves.add(new Advance(this, x, y + 1));
				}

				if (x > 0) {

					piece = chess.getPiece(x - 1, y + 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(this, piece));
					}
				}

				if (x < 7) {

					piece = chess.getPiece(x + 1, y + 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(this, piece));
					}
				}
			}

			break;

		case BLACK:

			if (y == 6 && chess.getPiece(x, 5) == null && chess.getPiece(x, 4) == null) {
				moves.add(new Advance(this, x, 4));
			}

			if (y > 1) {

				if (chess.getPiece(x, y - 1) == null) {
					moves.add(new Advance(this, x, y - 1));
				}

				if (x > 0) {

					piece = chess.getPiece(x - 1, y - 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(this, piece));
					}
				}

				if (x < 7) {

					piece = chess.getPiece(x + 1, y - 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(this, piece));
					}
				}
			}

			break;
		}

		return moves;
	}
}