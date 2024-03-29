package com.github.achaaab.chess.player;

import com.github.achaaab.aphabeta.Player;
import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.Color;
import com.github.achaaab.chess.move.ChessMove;
import com.github.achaaab.chess.piece.Bishop;
import com.github.achaaab.chess.piece.King;
import com.github.achaaab.chess.piece.Knigth;
import com.github.achaaab.chess.piece.Pawn;
import com.github.achaaab.chess.piece.Piece;
import com.github.achaaab.chess.piece.Queen;
import com.github.achaaab.chess.piece.Rook;

import java.util.ArrayList;
import java.util.List;

import static com.github.achaaab.chess.Color.WHITE;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class ChessPlayer implements Player<ChessMove> {

	protected Chess chess;
	protected Color color;
	protected List<Piece> pieces;

	private double value;
	private King king;
	private List<ChessMove> moves;

	/**
	 * @param chess
	 * @param color
	 */
	public ChessPlayer(Chess chess, Color color) {

		this.chess = chess;
		this.color = color;

		pieces = new ArrayList<>(32);

		if (color == WHITE) {

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

		} else {

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

		for (var piece : pieces) {
			moves.addAll(piece.getMoves());
		}

		removeIllegalMoves(moves);
	}

	/**
	 * @param moves
	 * @return
	 */
	public void removeIllegalMoves(List<ChessMove> moves) {

		var moveIterator = moves.iterator();
		ChessMove move;

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
	public List<ChessMove> getMoves() {
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