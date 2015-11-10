package fr.guehenneux.chess.piece;

import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public interface Piece {

	// Below are unicode characters for chess pieces, they are quite usefull for a cheap user interface.

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

	// Below are the piece values.

	double KING_VALUE = 1000;
	double QUEEN_VALUE = 9;
	double ROOK_VALUE = 5;
	double BISHOP_VALUE = 3;
	double KNIGHT_VALUE = 3;
	double PAWN_VALUE = 1;

	/**
	 * @return whether the piece can be captured en passant
	 */
	boolean isEnPassant();

	/**
	 * @return
	 */
	Chess getChess();

	/**
	 * @return
	 */
	ChessPlayer getPlayer();

	/**
	 * @return
	 */
	Color getColor();

	/**
	 * @return
	 */
	double getValue();

	/**
	 * @return
	 */
	int getX();

	/**
	 * @return
	 */
	int getY();

	/**
	 * @param x
	 * @param y
	 */
	void setPosition(int x, int y);

	/**
	 * @return the number of times the piece has been moved
	 */
	int getMoveCount();

	/**
	 * increment the number of times the piece has been moved
	 */
	void incrementMoveCount();

	/**
	 * decrement the number of times the piece has been moved
	 */
	void decrementMoveCount();

	/**
	 * @return
	 */
	List<Move> getMoves();

	/**
	 * @return
	 */
	char getUnicodeCharacter();
}