package fr.guehenneux.chess.player;

import java.util.Iterator;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.move.ChessMove;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.ui.SquareSelectionHandler;

/**
 * @author Jonathan Guéhenneux
 */
public class ChessPlayerUI extends ChessPlayer implements SquareSelectionHandler {

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
	public ChessPlayerUI(Chess chess, Color color) {

		super(chess, color);

		listening = false;
	}

	@Override
	public synchronized Move getMove() {

		if (!listening) {
			listen();
		}

		ChessMove move = null;

		Piece piece;
		List<Move> moves = null;
		boolean departureOK;
		boolean destinationOK;

		do {

			try {
				wait();
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
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

		System.out.println(departureX + " - " + departureY);

		do {

			try {
				wait();
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}

			Iterator<Move> moveIterator = moves.iterator();

			destinationOK = false;

			while (moveIterator.hasNext() && !destinationOK) {

				move = (ChessMove) moveIterator.next();
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