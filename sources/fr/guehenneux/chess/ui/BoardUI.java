package fr.guehenneux.chess.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.move.ChessMove;
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Guéhenneux
 */
public class BoardUI extends GridPane {

	private static final double SQUARE_SIZE_INCHES = 1.0;
	private static final String SQUARE_FONT_NAME = "Arial Unicode MS";
	private static final double SQUARE_FONT_SIZE_RATIO = 0.65;
	private static final String LABEL_FONT_NAME = "Arial Unicode MS";
	private static final double LABEL_FONT_SIZE_RATIO = 0.4;
	private static final double BORDER_RATIO = 0.05;

	private static final Paint DARK_SQUARE_COLOR = Color.web("0xD18B47");
	private static final Paint WHITE_SQUARE_COLOR = Color.web("0xFFCE9E");
	private static final Paint BACKGROUND_COLOR = Color.web("0xFFF0E0");
	private static final Paint DEPARTURE_PAINT = Color.BLUE;
	private static final Paint DESTINATION_PAINT = Color.GREEN;

	private static final Background BACKGROUND = new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY,
			Insets.EMPTY));

	private Chess chess;

	private Label[][] squares;

	private Background darkSquareBackground;
	private Background darkSquareDepartureBackground;
	private Background darkSquareDestinationBackground;
	private Background whiteSquareBackground;
	private Background whiteSquareDepartureBackground;
	private Background whiteSquareDestinationBackground;

	/**
	 * @param chess
	 * @param dpi
	 */
	public BoardUI(Chess chess, double dpi) {

		this.chess = chess;

		squares = new Label[8][8];

		Label square;

		double squareSize = SQUARE_SIZE_INCHES * dpi;
		double borderWidth = squareSize * BORDER_RATIO;

		Font squareFont = new Font(SQUARE_FONT_NAME, squareSize * SQUARE_FONT_SIZE_RATIO);
		Font labelFont = new Font(LABEL_FONT_NAME, squareSize * LABEL_FONT_SIZE_RATIO);

		Insets borderInsets = new Insets(borderWidth);

		BackgroundFill darkSquareFill = new BackgroundFill(DARK_SQUARE_COLOR, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill whiteSquareFill = new BackgroundFill(WHITE_SQUARE_COLOR, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill darkSquareSelectedFill = new BackgroundFill(DARK_SQUARE_COLOR, CornerRadii.EMPTY, borderInsets);
		BackgroundFill whiteSquareSelectedFill = new BackgroundFill(WHITE_SQUARE_COLOR, CornerRadii.EMPTY, borderInsets);
		BackgroundFill departureFill = new BackgroundFill(DEPARTURE_PAINT, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill destinationFill = new BackgroundFill(DESTINATION_PAINT, CornerRadii.EMPTY, Insets.EMPTY);

		darkSquareBackground = new Background(darkSquareFill);
		darkSquareDepartureBackground = new Background(departureFill, darkSquareSelectedFill);
		darkSquareDestinationBackground = new Background(destinationFill, darkSquareSelectedFill);
		whiteSquareBackground = new Background(whiteSquareFill);
		whiteSquareDepartureBackground = new Background(departureFill, whiteSquareSelectedFill);
		whiteSquareDestinationBackground = new Background(destinationFill, whiteSquareSelectedFill);

		Label fileLabel;

		for (int file = 0; file < 8; file++) {

			fileLabel = new Label(Character.toString((char) ('a' + file)));
			fileLabel.setFont(labelFont);
			fileLabel.setAlignment(Pos.BOTTOM_CENTER);
			fileLabel.setPrefSize(squareSize, squareSize);
			fileLabel.setBackground(BACKGROUND);

			add(fileLabel, file + 1, 0);

			fileLabel = new Label(Character.toString((char) ('a' + file)));
			fileLabel.setFont(labelFont);
			fileLabel.setAlignment(Pos.TOP_CENTER);
			fileLabel.setPrefSize(squareSize, squareSize);
			fileLabel.setBackground(BACKGROUND);

			add(fileLabel, file + 1, 9);
		}

		Label rankLabel;

		for (int rank = 0; rank < 8; rank++) {

			rankLabel = new Label(Character.toString((char) ('1' + rank)));
			rankLabel.setFont(labelFont);
			rankLabel.setAlignment(Pos.CENTER);
			rankLabel.setPrefSize(squareSize, squareSize);
			rankLabel.setBackground(BACKGROUND);

			add(rankLabel, 0, 8 - rank);

			rankLabel = new Label(Character.toString((char) ('1' + rank)));
			rankLabel.setFont(labelFont);
			rankLabel.setAlignment(Pos.CENTER);
			rankLabel.setPrefSize(squareSize, squareSize);
			rankLabel.setBackground(BACKGROUND);

			add(rankLabel, 9, 8 - rank);
		}

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {

				square = new Label();

				square.setBackground(getBackground(x, y, null));
				square.setFont(squareFont);
				square.setAlignment(Pos.CENTER);
				square.setPrefSize(squareSize, squareSize);

				squares[x][y] = square;
				add(square, x + 1, 8 - y);
			}
		}

		setBackground(BACKGROUND);
	}

	/**
	 * 
	 */
	public void update() {

		Label square;
		Piece piece;
		ChessMove lastMove = (ChessMove) chess.getLastMove();

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {

				square = squares[x][y];
				piece = chess.getPiece(x, y);

				if (piece != null) {
					square.setText(Character.toString(piece.getUnicodeCharacter()));
				} else {
					square.setText("");
				}

				square.setBackground(getBackground(x, y, lastMove));
			}
		}
	}

	/**
	 * @param x
	 * @param y
	 * @param lastMove
	 * @return
	 */
	private Background getBackground(int x, int y, ChessMove lastMove) {

		Background background;

		boolean darkSquare = (x + y) % 2 == 0;

		if (lastMove == null) {

			background = darkSquare ? darkSquareBackground : whiteSquareBackground;

		} else {

			int departureX = lastMove.getDepartureX();
			int departureY = lastMove.getDepartureY();
			int destinationX = lastMove.getDestinationX();
			int destinationY = lastMove.getDestinationY();

			if (x == departureX && y == departureY) {
				background = darkSquare ? darkSquareDepartureBackground : whiteSquareDepartureBackground;
			} else if (x == destinationX && y == destinationY) {
				background = darkSquare ? darkSquareDestinationBackground : whiteSquareDestinationBackground;
			} else {
				background = darkSquare ? darkSquareBackground : whiteSquareBackground;
			}
		}

		return background;
	}
}