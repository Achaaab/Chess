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
public class Queen extends AbstractPiece {

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Queen(Chess chess, ChessPlayer player, Color color, int x, int y) {
		super(chess, player, color, QUEEN_VALUE, x, y, color == Color.WHITE ? WHITE_QUEEN : BLACK_QUEEN);
	}

	/**
	 * @param chess
	 * @param player
	 * @param color
	 */
	public Queen(Chess chess, ChessPlayer player, Color color) {
		super(chess, player, color, QUEEN_VALUE, color == Color.WHITE ? WHITE_QUEEN : BLACK_QUEEN);
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		int newX;
		int newY;
		boolean emptySquare;
		Piece piece;

		newX = x + 1;
		newY = y;
		emptySquare = true;

		while (newX < 8 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newX++;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		newX = x - 1;
		newY = y;
		emptySquare = true;

		while (newX > -1 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newX--;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		newX = x;
		newY = y + 1;
		emptySquare = true;

		while (newY < 8 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newY++;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		newX = x;
		newY = y - 1;
		emptySquare = true;

		while (newY > -1 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newY--;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		newX = x + 1;
		newY = y + 1;
		emptySquare = true;

		while (newX < 8 && newY < 8 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newX++;
				newY++;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		newX = x - 1;
		newY = y - 1;
		emptySquare = true;

		while (newX > -1 && newY > -1 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newX--;
				newY--;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		newX = x + 1;
		newY = y - 1;
		emptySquare = true;

		while (newX < 8 && newY > -1 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newX++;
				newY--;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		newX = x - 1;
		newY = y + 1;
		emptySquare = true;

		while (newX > -1 && newY < 8 && emptySquare) {

			piece = chess.getPiece(newX, newY);
			emptySquare = piece == null;

			if (emptySquare) {

				moves.add(new Advance(this, newX, newY));
				newX--;
				newY++;

			} else if (piece.getColor() != color) {

				moves.add(new Capture(this, piece));
			}
		}

		return moves;
	}
}