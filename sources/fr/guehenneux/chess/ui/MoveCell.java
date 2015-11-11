package fr.guehenneux.chess.ui;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Font;
import fr.guehenneux.chess.move.MovePair;

/**
 * @author Jonathan Guéhenneux
 */
public class MoveCell extends TableCell<MovePair, String> {

	private static Font defaultFont;

	/**
	 * @param defaultFont
	 */
	public static void setDefaultFont(Font defaultFont) {
		MoveCell.defaultFont = defaultFont;
	}

	/**
	 * @param column
	 */
	public MoveCell(TableColumn<MovePair, String> column) {
		setFont(defaultFont);
	}

	@Override
	protected void updateItem(String move, boolean empty) {

		super.updateItem(move, empty);

		if (empty || move == null) {
			setText("");
		} else {
			setText(move);
		}
	}
}