package fr.guehenneux.chess.player;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Color;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class ChessPlayer implements Player {

	private List<Piece> pieces;

	/**
	 * @param chess
	 * @param color
	 */
	public ChessPlayer(Chess chess, Color color) {

		pieces = new ArrayList<>();

		switch (color) {

		case WHITE:

			pieces.add(new Pawn(chess, color, 0, 1));
			pieces.add(new Pawn(chess, color, 1, 1));
			pieces.add(new Pawn(chess, color, 2, 1));
			pieces.add(new Pawn(chess, color, 3, 1));
			pieces.add(new Pawn(chess, color, 4, 1));
			pieces.add(new Pawn(chess, color, 5, 1));
			pieces.add(new Pawn(chess, color, 6, 1));
			pieces.add(new Pawn(chess, color, 7, 1));
			break;

		case BLACK:

			pieces.add(new Pawn(chess, color, 0, 6));
			pieces.add(new Pawn(chess, color, 1, 6));
			pieces.add(new Pawn(chess, color, 2, 6));
			pieces.add(new Pawn(chess, color, 3, 6));
			pieces.add(new Pawn(chess, color, 4, 6));
			pieces.add(new Pawn(chess, color, 5, 6));
			pieces.add(new Pawn(chess, color, 6, 6));
			pieces.add(new Pawn(chess, color, 7, 6));
			break;
		}
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		for (Piece piece : pieces) {
			moves.addAll(piece.getMoves());
		}

		return moves;
	}
}