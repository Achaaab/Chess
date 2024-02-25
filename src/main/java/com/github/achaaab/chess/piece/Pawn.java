package com.github.achaaab.chess.piece;

import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.Color;
import com.github.achaaab.chess.move.Advance;
import com.github.achaaab.chess.move.Capture;
import com.github.achaaab.chess.move.CapturePromotion;
import com.github.achaaab.chess.move.ChessMove;
import com.github.achaaab.chess.move.EnPassant;
import com.github.achaaab.chess.move.Promotion;
import com.github.achaaab.chess.player.ChessPlayer;

import java.util.ArrayList;
import java.util.List;

import static com.github.achaaab.chess.Color.WHITE;

/**
 * @author Jonathan Guéhenneux
 */
public class Pawn extends AbstractPiece {

	private final Queen promotionQueen;
	private final Rook promotionRook;
	private final Bishop promotionBishop;
	private final Knigth promotionKnight;

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Pawn(Chess chess, ChessPlayer player, Color color, int x, int y) {

		super(chess, player, color, PAWN_VALUE, x, y, color == WHITE ? WHITE_PAWN : BLACK_PAWN);

		promotionQueen = new Queen(chess, player, color);
		promotionRook = new Rook(chess, player, color);
		promotionBishop = new Bishop(chess, player, color);
		promotionKnight = new Knigth(chess, player, color);
	}

	/**
	 * 
	 */
	public boolean isEnPassant() {

		boolean enPassant;

		var lastMove = chess.getLastMove();

		if (lastMove instanceof Advance advance) {

			if (advance.getPiece() == this) {

				enPassant = switch (color) {
					case WHITE -> advance.getDepartureY() == 1 && advance.getDestinationY() == 3;
					case BLACK -> advance.getDepartureY() == 6 && advance.getDestinationY() == 4;
				};

			} else {

				enPassant = false;
			}

		} else {

			enPassant = false;
		}

		return enPassant;
	}

	@Override
	public List<ChessMove> getMoves() {

		Piece piece;

		var moves = new ArrayList<ChessMove>();

		switch (color) {

		case WHITE:

			if (y == 1 && chess.getPiece(x, 2) == null && chess.getPiece(x, 3) == null) {
				moves.add(new Advance(chess, this, x, 3));
			}

			if (y < 6) {

				if (chess.getPiece(x, y + 1) == null) {
					moves.add(new Advance(chess, this, x, y + 1));
				}

				if (x > 0) {

					piece = chess.getPiece(x - 1, y + 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(chess, this, x - 1, y + 1));
					}
				}

				if (x < 7) {

					piece = chess.getPiece(x + 1, y + 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(chess, this, x + 1, y + 1));
					}
				}

				if (y == 4) {

					// check en passant

					if (x > 0) {

						piece = chess.getPiece(x - 1, y);

						if (piece != null && piece.isEnPassant()) {
							moves.add(new EnPassant(chess, this, x - 1, 5));
						}
					}

					if (x < 7) {

						piece = chess.getPiece(x + 1, y);

						if (piece != null && piece.isEnPassant()) {
							moves.add(new EnPassant(chess, this, x + 1, 5));
						}
					}
				}

			} else {

				// check promotions

				piece = chess.getPiece(x, 7);

				if (piece == null) {

					moves.add(new Promotion(chess, this, x, 7, promotionQueen));
					moves.add(new Promotion(chess, this, x, 7, promotionRook));
					moves.add(new Promotion(chess, this, x, 7, promotionBishop));
					moves.add(new Promotion(chess, this, x, 7, promotionKnight));
				}

				if (x > 0) {

					piece = chess.getPiece(x - 1, 7);

					if (piece != null && piece.getColor() != color) {

						moves.add(new CapturePromotion(chess, this, x - 1, 7, promotionQueen));
						moves.add(new CapturePromotion(chess, this, x - 1, 7, promotionRook));
						moves.add(new CapturePromotion(chess, this, x - 1, 7, promotionBishop));
						moves.add(new CapturePromotion(chess, this, x - 1, 7, promotionKnight));
					}
				}

				if (x < 7) {

					piece = chess.getPiece(x + 1, 7);

					if (piece != null && piece.getColor() != color) {

						moves.add(new CapturePromotion(chess, this, x + 1, 7, promotionQueen));
						moves.add(new CapturePromotion(chess, this, x + 1, 7, promotionRook));
						moves.add(new CapturePromotion(chess, this, x + 1, 7, promotionBishop));
						moves.add(new CapturePromotion(chess, this, x + 1, 7, promotionKnight));
					}
				}
			}

			break;

		case BLACK:

			if (y == 6 && chess.getPiece(x, 5) == null && chess.getPiece(x, 4) == null) {
				moves.add(new Advance(chess, this, x, 4));
			}

			if (y > 1) {

				if (chess.getPiece(x, y - 1) == null) {
					moves.add(new Advance(chess, this, x, y - 1));
				}

				if (x > 0) {

					piece = chess.getPiece(x - 1, y - 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(chess, this, x - 1, y - 1));
					}
				}

				if (x < 7) {

					piece = chess.getPiece(x + 1, y - 1);

					if (piece != null && piece.getColor() != color) {
						moves.add(new Capture(chess, this, x + 1, y - 1));
					}
				}

				if (y == 3) {

					// check en passant

					if (x > 0) {

						piece = chess.getPiece(x - 1, y);

						if (piece != null && piece.isEnPassant()) {
							moves.add(new EnPassant(chess, this, x - 1, 2));
						}
					}

					if (x < 7) {

						piece = chess.getPiece(x + 1, y);

						if (piece != null && piece.isEnPassant()) {
							moves.add(new EnPassant(chess, this, x + 1, 2));
						}
					}
				}

			} else {

				// check promotions

				piece = chess.getPiece(x, 0);

				if (piece == null) {

					moves.add(new Promotion(chess, this, x, 0, promotionQueen));
					moves.add(new Promotion(chess, this, x, 0, promotionRook));
					moves.add(new Promotion(chess, this, x, 0, promotionBishop));
					moves.add(new Promotion(chess, this, x, 0, promotionKnight));
				}

				if (x > 0) {

					piece = chess.getPiece(x - 1, 0);

					if (piece != null && piece.getColor() != color) {

						moves.add(new CapturePromotion(chess, this, x - 1, 0, promotionQueen));
						moves.add(new CapturePromotion(chess, this, x - 1, 0, promotionRook));
						moves.add(new CapturePromotion(chess, this, x - 1, 0, promotionBishop));
						moves.add(new CapturePromotion(chess, this, x - 1, 0, promotionKnight));
					}
				}

				if (x < 7) {

					piece = chess.getPiece(x + 1, 0);

					if (piece != null && piece.getColor() != color) {

						moves.add(new CapturePromotion(chess, this, x + 1, 0, promotionQueen));
						moves.add(new CapturePromotion(chess, this, x + 1, 0, promotionRook));
						moves.add(new CapturePromotion(chess, this, x + 1, 0, promotionBishop));
						moves.add(new CapturePromotion(chess, this, x + 1, 0, promotionKnight));
					}
				}
			}

			break;
		}

		return moves;
	}
}
