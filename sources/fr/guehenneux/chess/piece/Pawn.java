package fr.guehenneux.chess.piece;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.move.Advance;

/**
 * @author Jonathan Guéhenneux
 */
public class Pawn extends AbstractPiece {

	/**
	 * @param chess
	 * @param color
	 * @param x
	 * @param y
	 */
	public Pawn(Chess chess, Color color, int x, int y) {
		super(chess, color, x, y, color == Color.WHITE ? WHITE_PAWN : BLACK_PAWN);
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		switch (color) {

		case WHITE:

			if (y == 1 && chess.getPiece(x, 2) == null && chess.getPiece(x, 3) == null) {
				moves.add(new Advance(this, x, 3));
			}

			if (y < 6 && chess.getPiece(x, y + 1) == null) {
				moves.add(new Advance(this, x, y + 1));
			}

			break;

		case BLACK:

			if (y == 6 && chess.getPiece(x, 5) == null && chess.getPiece(x, 4) == null) {
				moves.add(new Advance(this, x, 4));
			}

			if (y > 1 && chess.getPiece(x, y - 1) == null) {
				moves.add(new Advance(this, x, y - 1));
			}

			break;
		}

		return moves;
	}
}