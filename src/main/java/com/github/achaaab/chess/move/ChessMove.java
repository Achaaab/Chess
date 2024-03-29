package com.github.achaaab.chess.move;

import com.github.achaaab.aphabeta.AbstractMove;
import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.piece.Piece;
import com.github.achaaab.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class ChessMove extends AbstractMove {

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private static String getSquareName(int x, int y) {

		char file = (char) ('a' + x);
		char rank = (char) ('1' + y);

		return file + Character.toString(rank);
	}

	protected Chess chess;
	protected ChessPlayer player;
	protected Piece piece;
	protected int departureX;
	protected int departureY;
	protected int destinationX;
	protected int destinationY;

	/**
	 * @param chess
	 * @param piece
	 * @param destinationX
	 * @param destinationY
	 */
	public ChessMove(Chess chess, Piece piece, int destinationX, int destinationY) {

		super(chess);

		this.chess = chess;
		this.piece = piece;
		this.destinationX = destinationX;
		this.destinationY = destinationY;

		player = piece.getPlayer();
		departureX = piece.getX();
		departureY = piece.getY();
	}

	/**
	 * @return
	 */
	protected String getDepartureSquare() {
		return getSquareName(departureX, departureY);
	}

	/**
	 * @return
	 */
	protected String getDestinationSquare() {
		return getSquareName(destinationX, destinationY);
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @return the departureX
	 */
	public int getDepartureX() {
		return departureX;
	}

	/**
	 * @return the departureY
	 */
	public int getDepartureY() {
		return departureY;
	}

	/**
	 * @return the destinationX
	 */
	public int getDestinationX() {
		return destinationX;
	}

	/**
	 * @return the destinationY
	 */
	public int getDestinationY() {
		return destinationY;
	}
}
