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
public class Knigth extends AbstractPiece {

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Knigth(Chess chess, ChessPlayer player, Color color, int x, int y) {
		super(chess, player, color, KNIGHT_VALUE, x, y, color == Color.WHITE ? WHITE_KNIGHT : BLACK_KNIGHT);
	}

	/**
	 * @param chess
	 * @param player
	 * @param color
	 */
	public Knigth(Chess chess, ChessPlayer player, Color color) {
		super(chess, player, color, KNIGHT_VALUE, color == Color.WHITE ? WHITE_KNIGHT : BLACK_KNIGHT);
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		Piece piece;

		if (x > 1) {

			if (y > 0) {

				piece = chess.getPiece(x - 2, y - 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 2, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}

			if (y < 7) {

				piece = chess.getPiece(x - 2, y + 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 2, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}
		}

		if (x < 6) {

			if (y > 0) {

				piece = chess.getPiece(x + 2, y - 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 2, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}

			if (y < 7) {

				piece = chess.getPiece(x + 2, y + 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 2, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}
		}

		if (y > 1) {

			if (x > 0) {

				piece = chess.getPiece(x - 1, y - 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 1, y - 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}

			if (x < 7) {

				piece = chess.getPiece(x + 1, y - 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 1, y - 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}
		}

		if (y < 6) {

			if (x > 0) {

				piece = chess.getPiece(x - 1, y + 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 1, y + 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}

			if (x < 7) {

				piece = chess.getPiece(x + 1, y + 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 1, y + 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, piece));
				}
			}
		}

		return moves;
	}
}