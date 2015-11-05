package fr.guehenneux.chess.player;

import fr.guehenneux.alphabeta.DecisionAlgorithm;
import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.PrincipalVariationSearch;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;

/**
 * @author Jonathan Guéhenneux
 */
public class ChessPlayerAI extends ChessPlayer {

	private DecisionAlgorithm decisionAlgorithm;

	/**
	 * @param chess
	 * @param color
	 */
	public ChessPlayerAI(Chess chess, Color color) {

		super(chess, color);

		decisionAlgorithm = new PrincipalVariationSearch(chess, 4);
	}

	@Override
	public Move getMove() {
		return decisionAlgorithm.getBestMove();
	}
}