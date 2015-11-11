package fr.guehenneux.chess.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.move.ChessMove;
import fr.guehenneux.chess.piece.Piece;

/**
 * @author Jonathan Guéhenneux
 */
public class SquareUI extends Label implements EventHandler<MouseEvent> {

	public static final double SQUARE_SIZE_INCHES = 0.9;
	private static final String FONT_NAME = "Arial Unicode MS";
	private static final double FONT_SIZE_INCHES = 0.62;
	private static final double BORDER_RATIO = 0.05;

	private static final Paint DARK_PAINT = Color.web("0xD18B47");
	private static final Paint WHITE_PAINT = Color.web("0xFFCE9E");
	private static final Paint DEPARTURE_PAINT = Color.BLUE;
	private static final Paint DESTINATION_PAINT = Color.GREEN;

	private static final String EMPTY = " ";

	private Chess chess;
	private int x;
	private int y;
	private boolean dark;

	private Background defaultBackground;
	private Background departureBackground;
	private Background destinationBackground;

	private List<SquareSelectionHandler> selectionHandlers;

	/**
	 * @param chess
	 * @param x
	 * @param y
	 * @param dpi
	 */
	public SquareUI(Chess chess, int x, int y, double dpi) {

		this.chess = chess;
		this.x = x;
		this.y = y;

		dark = (x + y) % 2 == 0;

		double squareSize = SQUARE_SIZE_INCHES * dpi;
		double fontSize = FONT_SIZE_INCHES * dpi;

		setPrefSize(squareSize, squareSize);
		double borderWidth = squareSize * BORDER_RATIO;
		Font font = Font.font(FONT_NAME, fontSize);
		setFont(font);

		setAlignment(Pos.CENTER);

		Paint paint = dark ? DARK_PAINT : WHITE_PAINT;

		setCursor(dark ? Cursor.HAND : Cursor.DEFAULT);

		Insets selectionInsets = new Insets(borderWidth);

		BackgroundFill fill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill selectionFill = new BackgroundFill(paint, CornerRadii.EMPTY, selectionInsets);
		BackgroundFill departureFill = new BackgroundFill(DEPARTURE_PAINT, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill destinationFill = new BackgroundFill(DESTINATION_PAINT, CornerRadii.EMPTY, Insets.EMPTY);

		defaultBackground = new Background(fill);
		departureBackground = new Background(departureFill, selectionFill);
		destinationBackground = new Background(destinationFill, selectionFill);

		setBackground(getBackground(null));

		selectionHandlers = new ArrayList<>();
		setOnMousePressed(this);
	}

	/**
	 * 
	 */
	public void update() {

		Piece piece = chess.getPiece(x, y);

		if (piece != null) {
			setText(Character.toString(piece.getUnicodeCharacter()));
		} else {
			setText(EMPTY);
		}

		ChessMove lastMove = (ChessMove) chess.getLastMove();

		setBackground(getBackground(lastMove));
	}

	/**
	 * @param lastMove
	 * @return
	 */
	private Background getBackground(ChessMove lastMove) {

		Background background;

		if (lastMove == null) {

			background = defaultBackground;

		} else {

			int departureX = lastMove.getDepartureX();
			int departureY = lastMove.getDepartureY();
			int destinationX = lastMove.getDestinationX();
			int destinationY = lastMove.getDestinationY();

			if (x == departureX && y == departureY) {
				background = departureBackground;
			} else if (x == destinationX && y == destinationY) {
				background = destinationBackground;
			} else {
				background = defaultBackground;
			}
		}

		return background;
	}

	@Override
	public void handle(MouseEvent mouseEvent) {

		for (SquareSelectionHandler selectionHandler : selectionHandlers) {
			selectionHandler.handle(x, y);
		}
	}

	/**
	 * @param selectionHandler
	 */
	public void addSelectionHandler(SquareSelectionHandler selectionHandler) {
		selectionHandlers.add(selectionHandler);
	}
}