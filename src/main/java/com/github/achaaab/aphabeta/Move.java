package com.github.achaaab.aphabeta;

/**
 * @author Jonathan Guéhenneux
 */
public interface Move extends Comparable<Move> {

	/**
	 * play the move
	 */
	void play();

	/**
	 * cancel the move
	 */
	void cancel();

	/**
	 * @return move value
	 */
	double getValue();

	/**
	 * @param value move value
	 */
	void setValue(double value);
}
