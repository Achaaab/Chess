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
public class Rook extends AbstractPiece {

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Rook(Chess chess, ChessPlayer player, Color color, int x, int y) {
		super(chess, player, color, ROOK_VALUE, x, y, color == Color.WHITE ? WHITE_ROOK : BLACK_ROOK);
	}

	/**
	 * @param chess
	 * @param player
	 * @param color
	 */
	public Rook(Chess chess, ChessPlayer player, Color color) {
		super(chess, player, color, ROOK_VALUE, color == Color.WHITE ? WHITE_ROOK : BLACK_ROOK);
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

		while (++destinationX < 8 && emptySquare) {

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

		while (--destinationX > -1 && emptySquare) {

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

		while (++destinationY < 8 && emptySquare) {

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

		while (--destinationY > -1 && emptySquare) {

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