package com.github.achaaab.chess.piece;

import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.Color;
import com.github.achaaab.chess.move.ChessMove;
import com.github.achaaab.chess.player.ChessPlayer;

import java.util.List;

/**
 * @author Jonathan Guéhenneux
 */
public interface Piece {

	// Below are unicode characters for chess pieces, they are quite useful for a cheap user interface.

	char WHITE_KING = '♔';
	char WHITE_QUEEN = '♕';
	char WHITE_ROOK = '♖';
	char WHITE_BISHOP = '♗';
	char WHITE_KNIGHT = '♘';
	char WHITE_PAWN = '♙';

	char BLACK_KING = '♚';
	char BLACK_QUEEN = '♛';
	char BLACK_ROOK = '♜';
	char BLACK_BISHOP = '♝';
	char BLACK_KNIGHT = '♞';
	char BLACK_PAWN = '♟';

	// Below are the piece values.

	double KING_VALUE = 200.0;
	double QUEEN_VALUE = 9.0;
	double ROOK_VALUE = 5.0;
	double BISHOP_VALUE = 3.3;
	double KNIGHT_VALUE = 3.2;
	double PAWN_VALUE = 1.0;

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
	List<ChessMove> getMoves();

	/**
	 * @return
	 */
	char getUnicodeCharacter();
}
