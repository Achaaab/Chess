package fr.guehenneux.chess.piece;

import fr.guehenneux.chess.Chess;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class AbstractPiece implements Piece {

	protected Chess chess;
	protected Color color;
	protected int x;
	protected int y;
	protected char unicodeCharacter;

	/**
	 * @param chess
	 * @param color
	 * @param x
	 * @param y
	 * @param unicodeCharacter
	 */
	public AbstractPiece(Chess chess, Color color, int x, int y, char unicodeCharacter) {

		this.chess = chess;
		this.color = color;
		this.x = x;
		this.y = y;
		this.unicodeCharacter = unicodeCharacter;

		chess.setPiece(x, y, this);
	}

	@Override
	public Chess getChess() {
		return chess;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public char getUnicodeCharacter() {
		return unicodeCharacter;
	}
}