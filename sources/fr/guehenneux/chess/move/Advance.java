package fr.guehenneux.chess.move;

import fr.guehenneux.alphabeta.AbstractMove;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Gu�henneux
 */
public class Advance extends AbstractMove {

	private Chess chess;
	private Piece piece;

	private int savedX;
	private int savedY;

	private int x;
	private int y;

	/**
	 * @param chess
	 * @param piece
	 * @param x
	 * @param y
	 */
	public Advance(Chess chess, Piece piece, int x, int y) {

		super(chess);

		this.chess = chess;
		this.piece = piece;
		this.x = x;
		this.y = y;
	}

	@Override
	public void play() {

		savedX = piece.getX();
		savedY = piece.getY();

		chess.setPiece(savedX, savedY, null);
		chess.setPiece(x, y, piece);
		piece.incrementMoveCount();

		super.play();
	}

	@Override
	public void cancel() {

		chess.setPiece(x, y, null);
		chess.setPiece(savedX, savedY, piece);
		piece.decrementMoveCount();

		super.cancel();
	}
}