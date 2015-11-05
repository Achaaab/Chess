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
 * @author Jonathan Gu�henneux
 */
public class King extends AbstractPiece {

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public King(Chess chess, ChessPlayer player, Color color, int x, int y) {
		super(chess, player, color, KING_VALUE, x, y, color == Color.WHITE ? WHITE_KING : BLACK_KING);
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		Piece piece;

		if (x > 0) {

			if (y > 0) {

				piece = chess.getPiece(x - 1, y - 1);

				if (piece == null) {
					moves.add(new Advance(this, x - 1, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(this, piece));
				}

			}

			if (y < 7) {

				piece = chess.getPiece(x - 1, y + 1);

				if (piece == null) {
					moves.add(new Advance(this, x - 1, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(this, piece));
				}
			}

			piece = chess.getPiece(x - 1, y);

			if (piece == null) {
				moves.add(new Advance(this, x - 1, y));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(this, piece));
			}
		}

		if (x < 7) {

			if (y > 0) {

				piece = chess.getPiece(x + 1, y - 1);

				if (piece == null) {
					moves.add(new Advance(this, x + 1, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(this, piece));
				}

			}

			if (y < 7) {

				piece = chess.getPiece(x + 1, y + 1);

				if (piece == null) {
					moves.add(new Advance(this, x + 1, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(this, piece));
				}
			}

			piece = chess.getPiece(x + 1, y);

			if (piece == null) {
				moves.add(new Advance(this, x + 1, y));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(this, piece));
			}
		}

		if (y > 0) {

			piece = chess.getPiece(x, y - 1);

			if (piece == null) {
				moves.add(new Advance(this, x, y - 1));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(this, piece));
			}

		}

		if (y < 7) {

			piece = chess.getPiece(x, y + 1);

			if (piece == null) {
				moves.add(new Advance(this, x, y + 1));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(this, piece));
			}
		}

		return moves;
	}
}