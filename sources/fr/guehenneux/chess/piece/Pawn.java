package fr.guehenneux.chess.piece;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.move.Advance;
import fr.guehenneux.chess.move.Capture;
import fr.guehenneux.chess.move.CapturePromotion;
import fr.guehenneux.chess.move.EnPassant;
import fr.guehenneux.chess.move.Promotion;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class Pawn extends AbstractPiece {

	private Queen promotionQueen;
	private Rook promotionRook;
	private Bishop promotionBishop;
	private Knigth promotionKnight;

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Pawn(Chess chess, ChessPlayer player, Color color, int x, int y) {

		super(chess, player, color, PAWN_VALUE, x, y, color == Color.WHITE ? WHITE_PAWN : BLACK_PAWN);

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

		Move lastMove = chess.getLastMove();

		if (lastMove instanceof Advance) {

			Advance advance = (Advance) lastMove;

			if (advance.getPiece() == this) {

				switch (color) {

				case WHITE:

					enPassant = advance.getDepartureY() == 1 && advance.getDestinationY() == 3;
					break;

				case BLACK:

					enPassant = advance.getDepartureY() == 6 && advance.getDestinationY() == 4;
					break;

				default:

					enPassant = false;
					break;
				}

			} else {

				enPassant = false;
			}

		} else {

			enPassant = false;
		}

		return enPassant;
	}

	@Override
	public List<Move> getMoves() {

		Piece piece;

		List<Move> moves = new ArrayList<>();

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