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
public class Bishop extends AbstractPiece {

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Bishop(Chess chess, ChessPlayer player, Color color, int x, int y) {
		super(chess, player, color, BISHOP_VALUE, x, y, color == Color.WHITE ? WHITE_BISHOP : BLACK_BISHOP);
	}

	/**
	 * @param chess
	 * @param player
	 * @param color
	 */
	public Bishop(Chess chess, ChessPlayer player, Color color) {
		super(chess, player, color, BISHOP_VALUE, color == Color.WHITE ? WHITE_BISHOP : BLACK_BISHOP);
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		int newX;
		int newY;
		boolean emptySquare;
		Piece piece;

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