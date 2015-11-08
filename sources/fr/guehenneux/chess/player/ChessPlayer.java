package fr.guehenneux.chess.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private boolean kingAlive;
	private double value;
	private King king;

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
	 * @param piece
	 */
	public void addPiece(Piece piece) {

		if (piece instanceof King) {
			kingAlive = true;
		}

		pieces.add(piece);

		value += piece.getValue();
	}

	/**
	 * @param piece
	 */
	public void removePiece(Piece piece) {

		if (piece instanceof King) {
			kingAlive = false;
		}

		pieces.remove(piece);

		value -= piece.getValue();
	}

	/**
	 * @return
	 */
	public Set<Piece> getDefendingPieces() {

		Set<Piece> defendingPieces = new HashSet<>();

		int x = king.getX();
		int y = king.getY();

		int checkX;
		int checkY;
		boolean threatened;
		Piece piece;
		Piece allyPiece = null;
		int allyPieceCount;

		// check threats from the east

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (++checkX < 8 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Rook || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		if (threatened && allyPieceCount == 1) {
			defendingPieces.add(allyPiece);
		}

		// check threats from the south-east

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (++checkX < 8 && --checkY > -1 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Bishop || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		// check threats from the south

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (--checkY > -1 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Rook || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		if (threatened && allyPieceCount == 1) {
			defendingPieces.add(allyPiece);
		}

		// check threats from the south-west

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (--checkX > -1 && --checkY > -1 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Bishop || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		if (threatened && allyPieceCount == 1) {
			defendingPieces.add(allyPiece);
		}

		// check threats from the west

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (--checkX > -1 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Rook || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		if (threatened && allyPieceCount == 1) {
			defendingPieces.add(allyPiece);
		}

		// check threats from the north-west

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (--checkX > -1 && ++checkY < 8 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Bishop || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		if (threatened && allyPieceCount == 1) {
			defendingPieces.add(allyPiece);
		}

		// check threats from the north

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (++checkY < 8 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Rook || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		if (threatened && allyPieceCount == 1) {
			defendingPieces.add(allyPiece);
		}

		// check threats from the north-east

		checkX = x;
		checkY = y;
		threatened = false;
		allyPieceCount = 0;

		while (++checkX < 8 && ++checkY < 8 && !threatened && allyPieceCount < 2) {

			piece = chess.getPiece(checkX, checkY);

			if (piece != null) {

				if (piece.getColor() == color) {

					allyPieceCount++;
					allyPiece = piece;

				} else if (piece instanceof Bishop || piece instanceof Queen) {

					threatened = true;
				}
			}
		}

		if (threatened && allyPieceCount == 1) {
			defendingPieces.add(allyPiece);
		}

		return defendingPieces;
	}

	@Override
	public List<Move> getMoves() {

		Set<Piece> defendingPieces = getDefendingPieces();

		List<Move> moves = new ArrayList<>();

		for (Piece piece : pieces) {

			if (!defendingPieces.contains(piece)) {
				moves.addAll(piece.getMoves());
			}
		}

		return moves;
	}

	/**
	 * @return
	 */
	public boolean isKingAlive() {
		return kingAlive;
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