package fr.guehenneux.chess.piece;

import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class AbstractPiece implements Piece {

	protected Chess chess;
	protected ChessPlayer player;
	protected Color color;
	protected double value;
	protected int x;
	protected int y;
	protected char unicodeCharacter;

	/**
	 * @param chess
	 * @param player
	 * @param color
	 * @param value
	 * @param x
	 * @param y
	 * @param unicodeCharacter
	 */
	public AbstractPiece(Chess chess, ChessPlayer player, Color color, double value, int x, int y, char unicodeCharacter) {

		this.chess = chess;
		this.player = player;
		this.color = color;
		this.value = value;
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
	public ChessPlayer getPlayer() {
		return player;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setPosition(int x, int y) {

		this.x = x;
		this.y = y;
	}

	@Override
	public char getUnicodeCharacter() {
		return unicodeCharacter;
	}

	@Override
	public String toString() {
		return "" + unicodeCharacter;
	}
}