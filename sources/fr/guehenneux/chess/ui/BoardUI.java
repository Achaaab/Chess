package fr.guehenneux.chess.ui;

import javafx.application.Platform;
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

		Font font = new Font(SQUARE_FONT_NAME, squareSize * 0.75);

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {

				square = new Label();

				if ((x + y) % 2 == 0) {
					square.setBackground(DARK_SQUARE_BACKGROUND);
				} else {
					square.setBackground(WHITE_SQUARE_BACKGROUND);
				}

				square.setFont(font);
				square.setAlignment(Pos.CENTER);
				square.setPrefSize(squareSize, squareSize);

				squares[x][y] = square;
				add(square, x, 7 - y);
			}
		}
	}

	/**
	 * 
	 */
	public void update() {
		Platform.runLater(() -> updatePlatform());
	}

	/**
	 * 
	 */
	private void updatePlatform() {

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