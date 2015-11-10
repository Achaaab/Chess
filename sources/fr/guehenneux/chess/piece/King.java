package fr.guehenneux.chess.piece;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.move.Advance;
import fr.guehenneux.chess.move.Capture;
import fr.guehenneux.chess.move.Castling;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
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

	/**
	 * @return whether the king is check
	 */
	public boolean isCheck() {
		return chess.isSquareAttacked(x, y, color);
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		Piece piece;

		if (x > 0) {

			if (y > 0) {

				piece = chess.getPiece(x - 1, y - 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 1, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x - 1, y - 1));
				}

			}

			if (y < 7) {

				piece = chess.getPiece(x - 1, y + 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x - 1, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x - 1, y + 1));
				}
			}

			piece = chess.getPiece(x - 1, y);

			if (piece == null) {
				moves.add(new Advance(chess, this, x - 1, y));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, x - 1, y));
			}
		}

		if (x < 7) {

			if (y > 0) {

				piece = chess.getPiece(x + 1, y - 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 1, y - 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x + 1, y - 1));
				}

			}

			if (y < 7) {

				piece = chess.getPiece(x + 1, y + 1);

				if (piece == null) {
					moves.add(new Advance(chess, this, x + 1, y + 1));
				} else if (piece.getColor() != color) {
					moves.add(new Capture(chess, this, x + 1, y + 1));
				}
			}

			piece = chess.getPiece(x + 1, y);

			if (piece == null) {
				moves.add(new Advance(chess, this, x + 1, y));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, x + 1, y));
			}
		}

		if (y > 0) {

			piece = chess.getPiece(x, y - 1);

			if (piece == null) {
				moves.add(new Advance(chess, this, x, y - 1));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, x, y - 1));
			}

		}

		if (y < 7) {

			piece = chess.getPiece(x, y + 1);

			if (piece == null) {
				moves.add(new Advance(chess, this, x, y + 1));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, x, y + 1));
			}
		}

		// castlings

		if (moveCount == 0) {

			Rook rook;

			if (chess.getPiece(5, y) == null && chess.getPiece(6, y) == null) {

				piece = chess.getPiece(7, y);

				if (piece != null && piece.getMoveCount() == 0 && piece.getColor() == color && piece instanceof Rook
						&& !chess.isSquareAttacked(5, y, color) && !chess.isSquareAttacked(6, y, color)) {

					rook = (Rook) piece;
					moves.add(new Castling(chess, this, 6, y, rook));
				}
			}

			if (chess.getPiece(3, y) == null && chess.getPiece(2, y) == null && chess.getPiece(1, y) == null
					&& !chess.isSquareAttacked(3, y, color) && !chess.isSquareAttacked(2, y, color)
					&& !chess.isSquareAttacked(1, y, color)) {

				piece = chess.getPiece(0, y);

				if (piece != null && piece.getMoveCount() == 0 && piece.getColor() == color && piece instanceof Rook) {

					rook = (Rook) piece;
					moves.add(new Castling(chess, this, 2, y, rook));
				}
			}
		}

		return moves;
	}
}