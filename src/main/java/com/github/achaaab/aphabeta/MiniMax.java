package com.github.achaaab.aphabeta;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Double.NEGATIVE_INFINITY;

/**
 * @author Jonathan Guéhenneux
 * @param <M> move type
 */
public class MiniMax<M extends Move> implements DecisionAlgorithm<M> {

	private static final Random RANDOM = new Random();

	private final TwoPlayersZeroSumGame<M> game;
	private final int maximumDepth;
	private final boolean deterministic;

	/**
	 * @param game
	 * @param maximumDepth
	 * @param deterministic
	 */
	public MiniMax(TwoPlayersZeroSumGame<M> game, int maximumDepth, boolean deterministic) {

		this.game = game;
		this.maximumDepth = maximumDepth;
		this.deterministic = deterministic;
	}

	@Override
	public M getBestMove() {

		game.getWinner();

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

		var bestMoveCount = bestMoves.size();
		var bestMoveIndex = RANDOM.nextInt(bestMoveCount);
		var bestMove = bestMoves.get(bestMoveIndex);

		bestMove.setValue(minimax);

		return bestMove;
	}

	/**
	 * @param depth
	 * @return
	 */
	private double getMoveValue(int depth) {

		double minimax;

		var currentPlayer = game.getCurrentPlayer();
		var winner = game.getWinner();

		if (winner == currentPlayer) {

			minimax = game.getWinningMoveValue() - depth;

		} else if (game.isDraw()) {

			minimax = 0;

		} else if (depth == maximumDepth) {

			minimax = game.getHeuristicValue(currentPlayer);

		} else {

			var moves = currentPlayer.getMoves();

			double moveValue;
			minimax = NEGATIVE_INFINITY;

			for (var move : moves) {

				move.play();
				moveValue = -getMoveValue(depth + 1);
				move.cancel();

				if (moveValue > minimax) {
					minimax = moveValue;
				}
			}
		}

		return minimax;
	}
}
