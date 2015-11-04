package fr.guehenneux.chess.piece;

import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;

/**
 * @author Jonathan Guéhenneux
 */
public interface Piece {

	char WHITE_KING = '\u2654';
	char WHITE_QUEEN = '\u2655';
	char WHITE_ROOK = '\u2656';
	char WHITE_BISHOP = '\u2657';
	char WHITE_KNIGHT = '\u2658';
	char WHITE_PAWN = '\u2659';

	char BLACK_KING = '\u265A';
	char BLACK_QUEEN = '\u265B';
	char BLACK_ROOK = '\u265C';
	char BLACK_BISHOP = '\u265D';
	char BLACK_KNIGHT = '\u265E';
	char BLACK_PAWN = '\u265F';

	/**
	 * @return
	 */
	Chess getChess();

	/**
	 * @return
	 */
	Color getColor();

	/**
	 * @return
	 */
	int getX();

	/**
	 * @param x
	 */
	void setX(int x);

	/**
	 * @return
	 */
	int getY();

	/**
	 * @param y
	 */
	void setY(int y);

	/**
	 * @return
	 */
	List<Move> getMoves();

	/**
	 * @return
	 */
	char getUnicodeCharacter();
}