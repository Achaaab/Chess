package com.github.achaaab.chess.player;

import com.github.achaaab.aphabeta.AlphaBeta;
import com.github.achaaab.aphabeta.DecisionAlgorithm;
import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.Color;
import com.github.achaaab.chess.move.ChessMove;

/**
 * @author Jonathan Guéhenneux
 */
public class ChessPlayerAi extends ChessPlayer {

	private final DecisionAlgorithm<ChessMove> decisionAlgorithm;

	/**
	 * @param chess
	 * @param color
	 */
	public ChessPlayerAi(Chess chess, Color color) {

		super(chess, color);

		decisionAlgorithm = new AlphaBeta<>(chess, 6, false);
	}

	@Override
	public ChessMove getMove() {
		return decisionAlgorithm.getBestMove();
	}
}
