package com.github.achaaab.aphabeta;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.util.Collections.sort;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public class PrincipalVariationSearch<M extends Move> implements DecisionAlgorithm<M> {

	private static final Random RANDOM = new Random();

	private final TwoPlayersZeroSumGame<M> game;
	private final int depth;
	private final boolean deterministic;

	/**
	 * @param game
	 * @param depth
	 * @param deterministic
	 */
	public PrincipalVariationSearch(TwoPlayersZeroSumGame<M> game, int depth, boolean deterministic) {

		this.game = game;
		this.depth = depth;
		this.deterministic = deterministic;
	}

	@Override
	public M getBestMove() {

		var currentPlayer = game.getCurrentPlayer();
		var moves = currentPlayer.getMoves();
		sortMoves(moves);
		var bestMoves = new LinkedList<M>();

		double moveValue;
		var alpha = NEGATIVE_INFINITY;
		var beta = POSITIVE_INFINITY;

		for (var move : moves) {

			move.play();
			moveValue = -getMoveValue(depth - 1, -beta, -alpha);
			move.cancel();

			if (moveValue > alpha) {

				bestMoves.clear();
				bestMoves.add(move);
				alpha = moveValue;

			} else if (moveValue == alpha && !deterministic) {

				bestMoves.add(move);
			}
		}

		int bestMoveCount = bestMoves.size();
		int bestMoveIndex = RANDOM.nextInt(bestMoveCount);
		return bestMoves.get(bestMoveIndex);
	}

	/**
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private double getMoveValue(int depth, double alpha, double beta) {

		var currentPlayer = game.getCurrentPlayer();
		var winner = game.getWinner();

		if (winner == currentPlayer) {

			alpha = game.getWinningMoveValue();

		} else if (game.isDraw()) {

			alpha = 0;

		} else if (depth == 0) {

			alpha = game.getHeuristicValue(currentPlayer);

		} else {

			var moves = currentPlayer.getMoves();
			sortMoves(moves);

			double moveValue;
			var firstMove = true;

			for (var move : moves) {

				move.play();

				if (firstMove) {

					moveValue = -getMoveValue(depth - 1, -beta, -alpha);
					firstMove = false;

				} else {

					moveValue = -getMoveValue(depth - 1, -alpha - 1, -alpha);

					if (moveValue > alpha && moveValue < beta) {
						moveValue = -getMoveValue(depth - 1, -beta, -alpha);
					}
				}

				move.cancel();

				if (moveValue > alpha) {
					alpha = moveValue;
				}

				if (alpha >= beta) {
					break;
				}
			}
		}

		return alpha;
	}

	/**
	 * @param moves
	 */
	private void sortMoves(List<M> moves) {

		double moveValue;

		for (var move : moves) {

			move.play();

			moveValue = -getMoveValue(0, 0, 0);
			move.setValue(moveValue);

			move.cancel();
		}

		sort(moves);
	}
}
