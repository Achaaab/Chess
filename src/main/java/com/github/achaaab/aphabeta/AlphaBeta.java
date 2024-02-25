package com.github.achaaab.aphabeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.max;
import static java.util.Collections.sort;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public class AlphaBeta<M extends Move> implements DecisionAlgorithm<M> {

	private static final Random RANDOM = new Random();

	private final TwoPlayersZeroSumGame<M> game;
	private final int maximumDepth;
	private final boolean deterministic;

	private List<M> bestMoves;

	/**
	 * @param game
	 * @param maximumDepth
	 * @param deterministic
	 */
	public AlphaBeta(TwoPlayersZeroSumGame<M> game, int maximumDepth, boolean deterministic) {

		this.game = game;
		this.maximumDepth = maximumDepth;
		this.deterministic = deterministic;
	}

	@Override
	public M getBestMove() {

		bestMoves = new ArrayList<>();
		alphaBeta(0, NEGATIVE_INFINITY, POSITIVE_INFINITY);

		M bestMove;

		if (deterministic) {

			bestMove = bestMoves.getFirst();

		} else {

			var bestMoveCount = bestMoves.size();
			var bestMoveIndex = RANDOM.nextInt(bestMoveCount);
			bestMove = bestMoves.get(bestMoveIndex);
		}

		return bestMove;
	}

	/**
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private double alphaBeta(int depth, double alpha, double beta) {

		double alphaBeta;

		var currentPlayer = game.getCurrentPlayer();
		var winner = game.getWinner();

		if (winner == currentPlayer) {

			alphaBeta = game.getWinningMoveValue() - depth;

		} else if (winner != null) {

			alphaBeta = depth - game.getWinningMoveValue();

		} else if (game.isDraw()) {

			alphaBeta = 0;

		} else if (depth == maximumDepth) {

			alphaBeta = game.getHeuristicValue(currentPlayer);

		} else {

			var moves = currentPlayer.getMoves();
			sortMoves(moves);

			alphaBeta = NEGATIVE_INFINITY;

			for (var move : moves) {

				move.play();
				var value = -alphaBeta(depth + 1, -beta, -alpha);
				move.cancel();

				if (depth == 0) {

					if (value > alphaBeta) {

						bestMoves.clear();
						bestMoves.add(move);

					} else if (value == alphaBeta && deterministic) {

						bestMoves.add(move);
					}
				}

				if (value > alphaBeta) {
					alphaBeta = value;
				}

				if (alphaBeta >= beta) {
					return alphaBeta;
				}

				alpha = max(alpha, alphaBeta);
			}
		}

		return alphaBeta;
	}

	/**
	 * @param moves
	 */
	private void sortMoves(List<M> moves) {

		double moveValue;

		for (var move : moves) {

			move.play();

			moveValue = -alphaBeta(maximumDepth, 0, 0);
			move.setValue(moveValue);

			move.cancel();
		}

		sort(moves);
	}
}
