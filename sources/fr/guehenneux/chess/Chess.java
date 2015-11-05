package fr.guehenneux.chess;

import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.alphabeta.TwoPlayersZeroSumGame;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayer;
import fr.guehenneux.chess.player.ChessPlayerAI;

/**
 * @author Jonathan Guéhenneux
 */
public class Chess extends TwoPlayersZeroSumGame {

	private Piece[][] board;

	private ChessPlayer white;
	private ChessPlayer black;

	private ChessUI ui;

	/**
	 * 
	 */
	public Chess() {

		board = new Piece[8][8];

		player0 = white = new ChessPlayerAI(this, Color.WHITE);
		player1 = black = new ChessPlayerAI(this, Color.BLACK);

		currentPlayer = white;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece getPiece(int x, int y) {
		return board[x][y];
	}

	/**
	 * @param x
	 * @param y
	 * @param piece
	 */
	public void setPiece(int x, int y, Piece piece) {

		board[x][y] = piece;

		if (piece != null) {
			piece.setPosition(x, y);
		}
	}

	/**
	 * @param ui
	 */
	public void setUI(ChessUI ui) {
		this.ui = ui;
	}

	@Override
	public double getHeuristicValue(Player player) {

		double heuristicValue;

		if (player == white) {
			heuristicValue = white.getValue() - black.getValue();
		} else {
			heuristicValue = black.getValue() - white.getValue();
		}

		return heuristicValue;
	}

	@Override
	public double getWinningMoveValue() {
		return 2000;
	}

	@Override
	public Player getWinner() {

		Player winner;

		if (!white.isKingAlive()) {
			winner = black;
		} else if (!black.isKingAlive()) {
			winner = white;
		} else {
			winner = null;
		}

		return winner;
	}

	@Override
	public boolean isDraw() {
		return false;
	}

	@Override
	public void updateUI() {

		if (ui != null) {
			ui.update();
		}
	}
}