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

	private ChessPlayer capturingPlayer;
	private ChessPlayer capturedPlayer;

	private Piece capturingPiece;
	private Piece capturedPiece;

	private int savedX;
	private int savedY;

	/**
	 * @param capturingPiece
	 * @param capturedPiece
	 */
	public Capture(Piece capturingPiece, Piece capturedPiece) {

		this.capturingPiece = capturingPiece;
		this.capturedPiece = capturedPiece;

		chess = capturingPiece.getChess();

		capturingPlayer = capturingPiece.getPlayer();
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

		capturedPlayer.removePiece(capturedPiece);

		chess.nextPlayer();
	}

	@Override
	public void cancel() {

		int x = capturedPiece.getX();
		int y = capturedPiece.getY();

		chess.setPiece(savedX, savedY, capturingPiece);
		chess.setPiece(x, y, capturedPiece);

		capturedPlayer.addPiece(capturedPiece);

		chess.previousPlayer();
	}
}