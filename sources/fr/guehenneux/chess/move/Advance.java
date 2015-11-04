package fr.guehenneux.chess.move;

import fr.guehenneux.alphabeta.AbstractMove;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Guéhenneux
 */
public class Advance extends AbstractMove {

	private Chess chess;
	private Piece piece;

	private int savedX;
	private int savedY;

	private int x;
	private int y;

	/**
	 * @param piece
	 * @param x
	 * @param y
	 */
	public Advance(Piece piece, int x, int y) {

		this.piece = piece;
		this.x = x;
		this.y = y;

		chess = piece.getChess();
	}

	@Override
	public void play() {

		savedX = piece.getX();
		savedY = piece.getY();

		chess.setPiece(savedX, savedY, null);
		chess.setPiece(x, y, piece);

		piece.setX(x);
		piece.setY(y);

		chess.nextPlayer();
	}

	@Override
	public void cancel() {

		chess.setPiece(x, y, null);
		chess.setPiece(savedX, savedY, piece);

		piece.setX(savedX);
		piece.setY(savedY);

		chess.previousPlayer();
	}
}