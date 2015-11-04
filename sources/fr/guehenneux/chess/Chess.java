package fr.guehenneux.chess;

import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.alphabeta.TwoPlayersZeroSumGame;
import fr.guehenneux.chess.piece.Color;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayerAI;

/**
 * @author Jonathan Guéhenneux
 */
public class Chess extends TwoPlayersZeroSumGame {

	private Piece[][] board;
	private ChessUI ui;

	/**
	 * 
	 */
	public Chess() {

		board = new Piece[8][8];

		player0 = new ChessPlayerAI(this, Color.WHITE);
		player1 = new ChessPlayerAI(this, Color.BLACK);

		currentPlayer = player0;
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
	}

	/**
	 * @param ui
	 */
	public void setUI(ChessUI ui) {
		this.ui = ui;
	}

	@Override
	public double getHeuristicValue(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWinningMoveValue() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDraw() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateUI() {

		if (ui != null) {
			ui.update();
		}
	}
}