package com.github.achaaab.aphabeta;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class AbstractMove implements Move {

	protected double value;
	private final Game game;

	/**
	 * @param game
	 */
	public AbstractMove(Game game) {
		this.game = game;
	}

	@Override
	public int compareTo(Move move) {
		return Double.compare(move.getValue(), value);
	}

	@Override
	public void play() {

		game.addMove(this);
		game.nextPlayer();
	}

	@Override
	public void cancel() {

		game.removeMove();
		game.previousPlayer();
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public void setValue(double value) {
		this.value = value;
	}
}
