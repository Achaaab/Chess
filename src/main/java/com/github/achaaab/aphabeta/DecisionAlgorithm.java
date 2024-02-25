package com.github.achaaab.aphabeta;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public interface DecisionAlgorithm<M extends Move> {

	/**
	 * @return best found move
	 */
	M getBestMove();
}
