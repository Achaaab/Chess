package com.github.achaaab.chess;

import com.github.achaaab.aphabeta.Player;
import com.github.achaaab.aphabeta.TwoPlayersZeroSumGame;
import com.github.achaaab.chess.move.ChessMove;
import com.github.achaaab.chess.move.MovePair;
import com.github.achaaab.chess.piece.Bishop;
import com.github.achaaab.chess.piece.King;
import com.github.achaaab.chess.piece.Knigth;
import com.github.achaaab.chess.piece.Pawn;
import com.github.achaaab.chess.piece.Piece;
import com.github.achaaab.chess.piece.Queen;
import com.github.achaaab.chess.piece.Rook;
import com.github.achaaab.chess.player.ChessPlayer;
import com.github.achaaab.chess.player.ChessPlayerAi;
import com.github.achaaab.chess.player.ChessPlayerUi;
import com.github.achaaab.chess.ui.ChessUi;
import com.github.achaaab.chess.ui.SquareSelectionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.github.achaaab.chess.Color.BLACK;
import static com.github.achaaab.chess.Color.WHITE;

/**
 * @author Jonathan Guéhenneux
 */
public class Chess extends TwoPlayersZeroSumGame<ChessMove> {

	private final Piece[][] board;

	private final ChessPlayer white;
	private final ChessPlayer black;

	private ChessUi ui;

	/**
	 * 
	 */
	public Chess() {

		board = new Piece[8][8];

		player0 = white = new ChessPlayerUi(this, WHITE);
		player1 = black = new ChessPlayerAi(this, BLACK);

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

		var movePairs = new ArrayList<MovePair>();

		int moveCount = moves.size();

		MovePair movePair;
		int pairNumber;
		ChessMove whiteMove;
		ChessMove blackMove;

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
	public double getHeuristicValue(Player<ChessMove> player) {

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
	 * @param kingX
	 * @param kingY
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
	public ChessPlayer getWinner() {

		ChessPlayer winner;

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
	public void setUI(ChessUi ui) {

		this.ui = ui;

		this.updateUi();
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
	public void updateUi() {

		if (ui != null) {
			ui.update();
		}
	}
}
