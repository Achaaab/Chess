package fr.guehenneux.chess.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.Color;
import fr.guehenneux.chess.piece.Bishop;
import fr.guehenneux.chess.piece.King;
import fr.guehenneux.chess.piece.Knigth;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.piece.Queen;
import fr.guehenneux.chess.piece.Rook;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class ChessPlayer implements Player {

	private Chess chess;
	private Color color;
	private List<Piece> pieces;
	private double value;
	private King king;
	private List<Move> moves;

	/**
	 * @param chess
	 * @param color
	 */
	public ChessPlayer(Chess chess, Color color) {

		this.chess = chess;
		this.color = color;

		pieces = new ArrayList<>();

		switch (color) {

		case WHITE:

			addPiece(new Pawn(chess, this, color, 0, 1));
			addPiece(new Pawn(chess, this, color, 1, 1));
			addPiece(new Pawn(chess, this, color, 2, 1));
			addPiece(new Pawn(chess, this, color, 3, 1));
			addPiece(new Pawn(chess, this, color, 4, 1));
			addPiece(new Pawn(chess, this, color, 5, 1));
			addPiece(new Pawn(chess, this, color, 6, 1));
			addPiece(new Pawn(chess, this, color, 7, 1));
			addPiece(new Rook(chess, this, color, 0, 0));
			addPiece(new Rook(chess, this, color, 7, 0));
			addPiece(new Knigth(chess, this, color, 1, 0));
			addPiece(new Knigth(chess, this, color, 6, 0));
			addPiece(new Bishop(chess, this, color, 2, 0));
			addPiece(new Bishop(chess, this, color, 5, 0));
			addPiece(king = new King(chess, this, color, 4, 0));
			addPiece(new Queen(chess, this, color, 3, 0));
			break;

		case BLACK:

			addPiece(new Pawn(chess, this, color, 0, 6));
			addPiece(new Pawn(chess, this, color, 1, 6));
			addPiece(new Pawn(chess, this, color, 2, 6));
			addPiece(new Pawn(chess, this, color, 3, 6));
			addPiece(new Pawn(chess, this, color, 4, 6));
			addPiece(new Pawn(chess, this, color, 5, 6));
			addPiece(new Pawn(chess, this, color, 6, 6));
			addPiece(new Pawn(chess, this, color, 7, 6));
			addPiece(new Rook(chess, this, color, 0, 7));
			addPiece(new Rook(chess, this, color, 7, 7));
			addPiece(new Knigth(chess, this, color, 1, 7));
			addPiece(new Knigth(chess, this, color, 6, 7));
			addPiece(new Bishop(chess, this, color, 2, 7));
			addPiece(new Bishop(chess, this, color, 5, 7));
			addPiece(king = new King(chess, this, color, 4, 7));
			addPiece(new Queen(chess, this, color, 3, 7));
			break;
		}
	}

	/**
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return
	 */
	public boolean isCheckMate() {
		return king.isCheck() && moves.isEmpty();
	}

	/**
	 * @param piece
	 */
	public void addPiece(Piece piece) {

		pieces.add(piece);

		value += piece.getValue();
	}

	/**
	 * @param piece
	 */
	public void removePiece(Piece piece) {

		pieces.remove(piece);

		value -= piece.getValue();
	}

	/**
	 * 
	 */
	public void computeMoves() {

		moves = new ArrayList<>();

		for (Piece piece : pieces) {
			moves.addAll(piece.getMoves());
		}

		// remove illegal moves

		Iterator<Move> moveIterator = moves.iterator();
		Move move;

		while (moveIterator.hasNext()) {

			move = moveIterator.next();

			move.play();

			if (chess.isSquareAttacked(king.getX(), king.getY(), color)) {
				moveIterator.remove();
			}

			move.cancel();
		}
	}

	@Override
	public List<Move> getMoves() {
		return moves;
	}

	/**
	 * @return
	 */
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return color.name();
	}
}