package com.github.achaaab.aphabeta;

import java.util.List;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public interface Player<M extends Move> {

	/**
	 * @return possible moves
	 */
	List<M> getMoves();

	/**
	 * @return chosen move
	 */
	M getMove();
}
