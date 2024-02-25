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
public class Bishop extends AbstractPiece {

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param x
	 * @param y
	 */
	public Bishop(Chess chess, ChessPlayer player, Color color, int x, int y) {
		super(chess, player, color, BISHOP_VALUE, x, y, color == WHITE ? WHITE_BISHOP : BLACK_BISHOP);
	}

	/**
	 * @param chess
	 * @param player
	 * @param color
	 */
	public Bishop(Chess chess, ChessPlayer player, Color color) {
		super(chess, player, color, BISHOP_VALUE, color == WHITE ? WHITE_BISHOP : BLACK_BISHOP);
	}

	@Override
	public List<ChessMove> getMoves() {

		var moves = new ArrayList<ChessMove>();

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
