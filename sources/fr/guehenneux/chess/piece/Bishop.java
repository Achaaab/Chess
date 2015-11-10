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

		int destinationX;
		int destinationY;
		boolean emptySquare;
		Piece piece;

		destinationX = x;
		destinationY = y;
		emptySquare = true;

		while (++destinationX < 8 && ++destinationY < 8 && emptySquare) {

			piece = chess.getPiece(destinationX, destinationY);
			emptySquare = piece == null;

			if (emptySquare) {
				moves.add(new Advance(chess, this, destinationX, destinationY));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, destinationX, destinationY));
			}
		}

		destinationX = x;
		destinationY = y;
		emptySquare = true;

		while (--destinationX > -1 && --destinationY > -1 && emptySquare) {

			piece = chess.getPiece(destinationX, destinationY);
			emptySquare = piece == null;

			if (emptySquare) {
				moves.add(new Advance(chess, this, destinationX, destinationY));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, destinationX, destinationY));
			}
		}

		destinationX = x;
		destinationY = y;
		emptySquare = true;

		while (++destinationX < 8 && --destinationY > -1 && emptySquare) {

			piece = chess.getPiece(destinationX, destinationY);
			emptySquare = piece == null;

			if (emptySquare) {
				moves.add(new Advance(chess, this, destinationX, destinationY));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, destinationX, destinationY));
			}
		}

		destinationX = x;
		destinationY = y;
		emptySquare = true;

		while (--destinationX > -1 && ++destinationY < 8 && emptySquare) {

			piece = chess.getPiece(destinationX, destinationY);
			emptySquare = piece == null;

			if (emptySquare) {
				moves.add(new Advance(chess, this, destinationX, destinationY));
			} else if (piece.getColor() != color) {
				moves.add(new Capture(chess, this, destinationX, destinationY));
			}
		}

		return moves;
	}
}