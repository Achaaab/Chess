package fr.guehenneux.chess;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.alphabeta.TwoPlayersZeroSumGame;
import fr.guehenneux.chess.move.MovePair;
import fr.guehenneux.chess.piece.Bishop;
import fr.guehenneux.chess.piece.King;
import fr.guehenneux.chess.piece.Knigth;
import fr.guehenneux.chess.piece.Pawn;
import fr.guehenneux.chess.piece.Piece;
import fr.guehenneux.chess.piece.Queen;
import fr.guehenneux.chess.piece.Rook;
import fr.guehenneux.chess.player.ChessPlayer;
import fr.guehenneux.chess.player.ChessPlayerAI;
import fr.guehenneux.chess.player.ChessPlayerUI;
import fr.guehenneux.chess.ui.ChessUI;
import fr.guehenneux.chess.ui.SquareSelectionHandler;

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

		player0 = white = new ChessPlayerUI(this, Color.WHITE);
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
	 * @return
	 */
	public List<MovePair> getMovePairs() {

		List<MovePair> movePairs = new ArrayList<>();

		int moveCount = moves.size();

		MovePair movePair;
		int pairNumber;
		Move whiteMove;
		Move blackMove;

		int moveIndex = 0;

		while (moveIndex < moveCount) {

			pairNumber = (moveIndex >> 1) + 1;

			whiteMove = moves.get(moveIndex++);

			if (moveIndex < moveCount) {
				blackMove = moves.get(moveIndex++);
			} else {
				blackMove = null;
			}

			movePair = new MovePair(pairNumber, whiteMove, blackMove);
			movePairs.add(movePair);
		}

		return movePairs;
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

	/**
	 * @param x
	 * @param y
	 * @param color
	 * @return whether the specified square is attacked by a piece of different color
	 */
	public boolean isSquareAttacked(int kingX, int kingY, Color color) {

		Piece piece;

		// check pawn attacks

		switch (color) {

		case WHITE:

			if (kingY < 6) {

				if (kingX < 7) {

					piece = getPiece(kingX + 1, kingY + 1);

					if (piece != null && piece.getColor() != color && piece instanceof Pawn) {
						return true;
					}
				}

				if (kingX > 0) {

					piece = getPiece(kingX - 1, kingY + 1);

					if (piece != null && piece.getColor() != color && piece instanceof Pawn) {
						return true;
					}
				}
			}

			break;

		case BLACK:

			if (kingY > 1) {

				if (kingX < 7) {

					piece = getPiece(kingX + 1, kingY - 1);

					if (piece != null && piece.getColor() != color && piece instanceof Pawn) {
						return true;
					}
				}

				if (kingX > 0) {

					piece = getPiece(kingX - 1, kingY - 1);

					if (piece != null && piece.getColor() != color && piece instanceof Pawn) {
						return true;
					}
				}
			}

			break;
		}

		// check knight attacks

		if (kingX > 1) {

			if (kingY > 0) {

				piece = getPiece(kingX - 2, kingY - 1);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}

			if (kingY < 7) {

				piece = getPiece(kingX - 2, kingY + 1);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}
		}

		if (kingX < 6) {

			if (kingY > 0) {

				piece = getPiece(kingX + 2, kingY - 1);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}

			if (kingY < 7) {

				piece = getPiece(kingX + 2, kingY + 1);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}
		}

		if (kingY > 1) {

			if (kingX > 0) {

				piece = getPiece(kingX - 1, kingY - 2);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}

			if (kingX < 7) {

				piece = getPiece(kingX + 1, kingY - 2);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}
		}

		if (kingY < 6) {

			if (kingX > 0) {

				piece = getPiece(kingX - 1, kingY + 2);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}

			if (kingX < 7) {

				piece = getPiece(kingX + 1, kingY + 2);

				if (piece != null && piece.getColor() != color && piece instanceof Knigth) {
					return true;
				}
			}
		}

		// check king attacks

		if (kingX > 0) {

			if (kingY > 0) {

				piece = getPiece(kingX - 1, kingY - 1);

				if (piece != null && piece.getColor() != color && piece instanceof King) {
					return true;
				}
			}

			if (kingY < 7) {

				piece = getPiece(kingX - 1, kingY + 1);

				if (piece != null && piece.getColor() != color && piece instanceof King) {
					return true;
				}
			}

			piece = getPiece(kingX - 1, kingY);

			if (piece != null && piece.getColor() != color && piece instanceof King) {
				return true;
			}
		}

		if (kingX < 7) {

			if (kingY > 0) {

				piece = getPiece(kingX + 1, kingY - 1);

				if (piece != null && piece.getColor() != color && piece instanceof King) {
					return true;
				}
			}

			if (kingY < 7) {

				piece = getPiece(kingX + 1, kingY + 1);

				if (piece != null && piece.getColor() != color && piece instanceof King) {
					return true;
				}
			}

			piece = getPiece(kingX + 1, kingY);

			if (piece != null && piece.getColor() != color && piece instanceof King) {
				return true;
			}
		}

		if (kingY > 0) {

			piece = getPiece(kingX, kingY - 1);

			if (piece != null && piece.getColor() != color && piece instanceof King) {
				return true;
			}
		}

		if (kingY < 7) {

			piece = getPiece(kingX, kingY + 1);

			if (piece != null && piece.getColor() != color && piece instanceof King) {
				return true;
			}
		}

		int checkX;
		int checkY;
		boolean emptySquare;

		// check bishop / queen attacks

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (++checkX < 8 && ++checkY < 8 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Bishop || piece instanceof Queen)) {
				return true;
			}
		}

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (++checkX < 8 && --checkY > -1 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Bishop || piece instanceof Queen)) {
				return true;
			}
		}

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (--checkX > -1 && --checkY > -1 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Bishop || piece instanceof Queen)) {
				return true;
			}
		}

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (--checkX > -1 && ++checkY < 8 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Bishop || piece instanceof Queen)) {
				return true;
			}
		}

		// check rook / queen attacks

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (++checkX < 8 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Rook || piece instanceof Queen)) {
				return true;
			}
		}

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (--checkY > -1 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Rook || piece instanceof Queen)) {
				return true;
			}
		}

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (--checkX > -1 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Rook || piece instanceof Queen)) {
				return true;
			}
		}

		checkX = kingX;
		checkY = kingY;
		emptySquare = true;

		while (++checkY < 8 && emptySquare) {

			piece = getPiece(checkX, checkY);

			emptySquare = piece == null;

			if (!emptySquare && piece.getColor() != color && (piece instanceof Rook || piece instanceof Queen)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Player getWinner() {

		Player winner;

		if (currentPlayer == white) {

			white.computeMoves();

			if (white.isCheckMate()) {
				winner = black;
			} else {
				winner = null;
			}

		} else {

			black.computeMoves();

			if (black.isCheckMate()) {
				winner = white;
			} else {
				winner = null;
			}
		}

		return winner;
	}

	@Override
	public boolean isDraw() {
		return currentPlayer.getMoves().isEmpty();
	}

	/**
	 * @param ui
	 */
	public void setUI(ChessUI ui) {

		this.ui = ui;

		updateUI();
	}

	/**
	 * @param selectionHandler
	 */
	public void addSelectionHandler(SquareSelectionHandler selectionHandler) {

		if (ui != null) {
			ui.addSelectionHandler(selectionHandler);
		}
	}

	@Override
	public void updateUI() {

		if (ui != null) {
			ui.update();
		}
	}
}