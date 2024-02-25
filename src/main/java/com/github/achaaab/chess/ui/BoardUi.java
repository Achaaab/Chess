package com.github.achaaab.chess.ui;

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
import com.github.achaaab.chess.Chess;

import static com.github.achaaab.chess.ui.SquareUI.SQUARE_SIZE_INCHES;

/**
 * @author Jonathan Guéhenneux
 */
public class BoardUi extends GridPane {

	private static final String LABEL_FONT_NAME = "Arial Unicode MS";
	private static final double LABEL_FONT_SIZE_RATIO = 0.4;
	private static final Paint BACKGROUND_PAINT = Color.web("0xFFF0E0");

	private static final Background BACKGROUND = new Background(new BackgroundFill(
			BACKGROUND_PAINT, CornerRadii.EMPTY, Insets.EMPTY));

	private final SquareUI[][] squares;

	/**
	 * @param chess
	 * @param dpi
	 */
	public BoardUi(Chess chess, double dpi) {

		squares = new SquareUI[8][8];

		SquareUI square;

		var squareSize = SQUARE_SIZE_INCHES * dpi;
		var labelFont = new Font(LABEL_FONT_NAME, squareSize * LABEL_FONT_SIZE_RATIO);

		Label fileLabel;

		for (var file = 0; file < 8; file++) {

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

				square = new SquareUI(chess, x, y, dpi);
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

		SquareUI square;

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {

				square = squares[x][y];
				square.update();
			}
		}
	}

	/**
	 * @param selectionHandler
	 */
	public void addSelectionHandler(SquareSelectionHandler selectionHandler) {

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {

				squares[x][y].addSelectionHandler(selectionHandler);
			}
		}
	}
}
