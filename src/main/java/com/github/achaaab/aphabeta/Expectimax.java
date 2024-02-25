package com.github.achaaab.aphabeta;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Double.NEGATIVE_INFINITY;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public class Expectimax<M extends Move> implements DecisionAlgorithm<M> {

	private static final Random RANDOM = new Random();

	private final TwoPlayersZeroSumGame<M> game;
	private final int maximumDepth;
	private final boolean deterministic;

	/**
	 * @param game
	 * @param maximumDepth
	 * @param deterministic
	 */
	public Expectimax(TwoPlayersZeroSumGame<M> game, int maximumDepth, boolean deterministic) {

		this.game = game;
		this.maximumDepth = maximumDepth;
		this.deterministic = deterministic;
	}

	@Override
	public M getBestMove() {

		var currentPlayer = game.getCurrentPlayer();
		var moves = currentPlayer.getMoves();
		var bestMoves = new LinkedList<M>();

		double moveValue;
		var minimax = NEGATIVE_INFINITY;

		for (var move : moves) {

			move.play();
			moveValue = -getMoveValue(1);
			move.cancel();

			if (moveValue > minimax) {

				bestMoves.clear();
				bestMoves.add(move);
				minimax = moveValue;

			} else if (moveValue == minimax && !deterministic) {

				bestMoves.add(move);
			}
		}

		int bestMoveCount = bestMoves.size();
		int bestMoveIndex = RANDOM.nextInt(bestMoveCount);
		return bestMoves.get(bestMoveIndex);
	}

	/**
	 * @param depth
	 * @return
	 */
	private double getMoveValue(int depth) {

		double expectimax;

		var currentPlayer = game.getCurrentPlayer();
		var winner = game.getWinner();

		if (winner == currentPlayer) {

			expectimax = game.getWinningMoveValue() - depth;

		} else if (game.isDraw()) {

			expectimax = 0;

		} else if (depth == maximumDepth) {

			expectimax = game.getHeuristicValue(currentPlayer);

		} else {

			var moves = currentPlayer.getMoves();
			double moveValue;

			if (depth % 2 == 0) {

				expectimax = NEGATIVE_INFINITY;

				for (var move : moves) {

					move.play();
					moveValue = -getMoveValue(depth + 1);
					move.cancel();

					if (moveValue > expectimax) {
						expectimax = moveValue;
					}
				}

			} else {

				expectimax = 0;

				for (var move : moves) {

					move.play();
					moveValue = -getMoveValue(depth + 1);
					move.cancel();

					expectimax += moveValue;
				}

				expectimax /= moves.size();
			}
		}

		return expectimax;
	}
}
