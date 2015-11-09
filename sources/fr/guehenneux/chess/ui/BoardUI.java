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
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Guéhenneux
 */
public class BoardUI extends GridPane {

	private static final double SQUARE_SIZE_INCHES = 1.0;
	private static final String SQUARE_FONT_NAME = "Arial Unicode MS";
	private static final double SQUARE_FONT_SIZE_RATIO = 0.75;
	private static final String LABEL_FONT_NAME = "Arial Unicode MS";
	private static final double LABEL_FONT_SIZE_RATIO = 0.4;

	private static final Paint DARK_SQUARE_COLOR = Color.web("0xD18B47");
	private static final Paint WHITE_SQUARE_COLOR = Color.web("0xFFCE9E");

	private static final Background DARK_SQUARE_BACKGROUND = new Background(new BackgroundFill(DARK_SQUARE_COLOR,
			CornerRadii.EMPTY, Insets.EMPTY));

	private static final Background WHITE_SQUARE_BACKGROUND = new Background(new BackgroundFill(WHITE_SQUARE_COLOR,
			CornerRadii.EMPTY, Insets.EMPTY));

	private Chess model;

	private Label[][] squares;

	/**
	 * @param model
	 * @param dpi
	 */
	public BoardUI(Chess model, double dpi) {

		this.model = model;

		squares = new Label[8][8];

		Label square;

		double squareSize = SQUARE_SIZE_INCHES * dpi;

		Font squareFont = new Font(SQUARE_FONT_NAME, squareSize * SQUARE_FONT_SIZE_RATIO);
		Font labelFont = new Font(LABEL_FONT_NAME, squareSize * LABEL_FONT_SIZE_RATIO);

		Label fileLabel;

		for (int file = 0; file < 8; file++) {

			fileLabel = new Label(Character.toString((char) ('a' + file)));
			fileLabel.setFont(labelFont);
			fileLabel.setAlignment(Pos.BOTTOM_CENTER);
			fileLabel.setPrefSize(squareSize, squareSize);

			add(fileLabel, file + 1, 0);

			fileLabel = new Label(Character.toString((char) ('a' + file)));
			fileLabel.setFont(labelFont);
			fileLabel.setAlignment(Pos.TOP_CENTER);
			fileLabel.setPrefSize(squareSize, squareSize);

			add(fileLabel, file + 1, 9);
		}

		Label rankLabel;

		for (int rank = 0; rank < 8; rank++) {

			rankLabel = new Label(Character.toString((char) ('1' + rank)));
			rankLabel.setFont(labelFont);
			rankLabel.setAlignment(Pos.CENTER);
			rankLabel.setPrefSize(squareSize, squareSize);

			add(rankLabel, 0, 8 - rank);

			rankLabel = new Label(Character.toString((char) ('1' + rank)));
			rankLabel.setFont(labelFont);
			rankLabel.setAlignment(Pos.CENTER);
			rankLabel.setPrefSize(squareSize, squareSize);

			add(rankLabel, 9, 8 - rank);
		}

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {

				square = new Label();

				if ((x + y) % 2 == 0) {
					square.setBackground(DARK_SQUARE_BACKGROUND);
				} else {
					square.setBackground(WHITE_SQUARE_BACKGROUND);
				}

				square.setFont(squareFont);
				square.setAlignment(Pos.CENTER);
				square.setPrefSize(squareSize, squareSize);

				squares[x][y] = square;
				add(square, x + 1, 8 - y);
			}
		}
	}

	/**
	 * 
	 */
	public void update() {

		Label square;
		Piece piece;

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {

				square = squares[x][y];
				piece = model.getPiece(x, y);

				if (piece != null) {
					square.setText(Character.toString(piece.getUnicodeCharacter()));
				} else {
					square.setText("");
				}
			}
		}
	}
}