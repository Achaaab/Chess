package fr.guehenneux.chess.move;

import fr.guehenneux.alphabeta.AbstractMove;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.player.ChessPlayer;

/**
 * @author Jonathan Guéhenneux
 */
public class Capture extends AbstractMove {

	private Chess chess;

	private ChessPlayer capturedPlayer;

	private Piece capturingPiece;
	private Piece capturedPiece;

	private int savedX;
	private int savedY;

	/**
	 * @param chess
	 * @param capturingPiece
	 * @param capturedPiece
	 */
	public Capture(Chess chess, Piece capturingPiece, Piece capturedPiece) {

		super(chess);

		this.chess = chess;
		this.capturingPiece = capturingPiece;
		this.capturedPiece = capturedPiece;

		capturedPlayer = capturedPiece.getPlayer();
	}

	@Override
	public void play() {

		savedX = capturingPiece.getX();
		savedY = capturingPiece.getY();

		int x = capturedPiece.getX();
		int y = capturedPiece.getY();

		chess.setPiece(savedX, savedY, null);
		chess.setPiece(x, y, capturingPiece);
		capturingPiece.incrementMoveCount();

		capturedPlayer.removePiece(capturedPiece);

		super.play();
	}

	@Override
	public void cancel() {

		int x = capturedPiece.getX();
		int y = capturedPiece.getY();

		chess.setPiece(savedX, savedY, capturingPiece);
		chess.setPiece(x, y, capturedPiece);
		capturingPiece.decrementMoveCount();

		capturedPlayer.addPiece(capturedPiece);

		super.cancel();
	}
}