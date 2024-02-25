package com.github.achaaab.chess.player;

import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.Color;
import com.github.achaaab.chess.move.ChessMove;
import com.github.achaaab.chess.piece.Piece;
import com.github.achaaab.chess.ui.SquareSelectionHandler;

import java.util.List;

import static java.lang.Thread.currentThread;

/**
 * @author Jonathan Guéhenneux
 */
public class ChessPlayerUi extends ChessPlayer implements SquareSelectionHandler {

	private boolean listening;

	private int x;
	private int y;

	private int departureX;
	private int departureY;
	private int destinationX;
	private int destinationY;

	/**
	 * @param chess
	 * @param color
	 */
	public ChessPlayerUi(Chess chess, Color color) {

		super(chess, color);

		listening = false;
	}

	@Override
	public synchronized ChessMove getMove() {

		if (!listening) {
			listen();
		}

		ChessMove move = null;

		Piece piece;
		List<ChessMove> moves = null;
		boolean departureOK;
		boolean destinationOK;

		do {

			try {
				wait();
			} catch (InterruptedException interruptedException) {
				currentThread().interrupt();
			}

			piece = chess.getPiece(x, y);

			if (piece == null || piece.getColor() != color) {

				departureOK = false;

			} else {

				moves = piece.getMoves();
				removeIllegalMoves(moves);
				departureOK = !moves.isEmpty();
			}

		} while (!departureOK);

		departureX = x;
		departureY = y;

		chess.updateUi();

		do {

			try {
				wait();
			} catch (InterruptedException interruptedException) {
				currentThread().interrupt();
			}

			var moveIterator = moves.iterator();

			destinationOK = false;

			while (moveIterator.hasNext() && !destinationOK) {

				move = moveIterator.next();
				destinationOK = x == move.getDestinationX() && y == move.getDestinationY();
			}

		} while (!destinationOK);

		return move;
	}

	/**
	 * 
	 */
	private void listen() {

		chess.addSelectionHandler(this);
		listening = true;
	}

	/**
	 * 
	 */
	private void getDeparture() {

	}

	/**
	 * 
	 */
	private void getDestination() {

	}

	@Override
	public synchronized void handle(int x, int y) {

		this.x = x;
		this.y = y;

		notify();
	}
}
