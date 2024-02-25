package com.github.achaaab.chess.piece;

import java.util.ArrayList;
import java.util.List;

import com.github.achaaab.aphabeta.Move;
import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.Color;
import com.github.achaaab.chess.move.ChessMove;
import com.github.achaaab.chess.player.ChessPlayer;
import com.github.achaaab.chess.move.Advance;
import com.github.achaaab.chess.move.Capture;

import static com.github.achaaab.chess.Color.WHITE;

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
		super(chess, player, color, KNIGHT_VALUE, x, y, color == WHITE ? WHITE_KNIGHT : BLACK_KNIGHT);
	}

	/**
	 * @param chess
	 * @param player
	 * @param color
	 */
	public Knigth(Chess chess, ChessPlayer player, Color color) {
		super(chess, player, color, KNIGHT_VALUE, color == WHITE ? WHITE_KNIGHT : BLACK_KNIGHT);
	}

	@Override
	public List<ChessMove> getMoves() {

		var moves = new ArrayList<ChessMove>();

		Piece piece;

		if (x > 1) {

			if (y > 0) {

				piece = chess.getPiece(x - 2, y - 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 2, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x - 2, y - 1));
				}
			}

			if (y < 7) {

				piece = chess.getPiece(x - 2, y + 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 2, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x - 2, y + 1));
				}
			}
		}

		if (x < 6) {

			if (y > 0) {

				piece = chess.getPiece(x + 2, y - 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 2, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x + 2, y - 1));
				}
			}

			if (y < 7) {

				piece = chess.getPiece(x + 2, y + 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 2, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x + 2, y + 1));
				}
			}
		}

		if (y > 1) {

			if (x > 0) {

				piece = chess.getPiece(x - 1, y - 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 1, y - 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x - 1, y - 2));
				}
			}

			if (x < 7) {

				piece = chess.getPiece(x + 1, y - 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 1, y - 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x + 1, y - 2));
				}
			}
		}

		if (y < 6) {

			if (x > 0) {

				piece = chess.getPiece(x - 1, y + 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 1, y + 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x - 1, y + 2));
				}
			}

			if (x < 7) {

				piece = chess.getPiece(x + 1, y + 2);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 1, y + 2));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x + 1, y + 2));
				}
			}
		}

		return moves;
	}
}
