package fr.guehenneux.chess.move;

import fr.guehenneux.alphabeta.AbstractMove;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.King;
import fr.guehenneux.chess.piece.Rook;

/**
 * @author Jonathan Guéhenneux
 */
public class Castling extends AbstractMove {

	private Chess chess;
	private King king;
	private Rook rook;

	private int savedKingX;
	private int savedRookX;

	private int kingX;
	private int rookX;

	private int y;

	/**
	 * @param king
	 * @param rook
	 */
	public Castling(King king, Rook rook) {

		this.king = king;
		this.rook = rook;

		chess = king.getChess();
		y = king.getY();
	}

	@Override
	public void play() {

		kingX = king.getX();
		rookX = rook.getX();

		savedKingX = kingX;
		savedRookX = rookX;

		if (kingX < rookX) {

			kingX += 2;
			rookX = kingX - 1;

		} else {

			kingX -= 2;
			rookX = kingX + 1;
		}

		chess.setPiece(savedKingX, y, null);
		chess.setPiece(savedRookX, y, null);

		chess.setPiece(kingX, y, king);
		chess.setPiece(rookX, y, rook);

		king.incrementMoveCount();
		rook.incrementMoveCount();

		chess.nextPlayer();
	}

	@Override
	public void cancel() {

		chess.setPiece(kingX, y, null);
		chess.setPiece(rookX, y, null);

		chess.setPiece(savedKingX, y, king);
		chess.setPiece(savedRookX, y, rook);

		king.decrementMoveCount();
		rook.decrementMoveCount();

		chess.previousPlayer();
	}
}