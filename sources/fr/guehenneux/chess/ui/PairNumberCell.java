package fr.guehenneux.chess.ui;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Font;
import fr.guehenneux.chess.move.MovePair;

/**
 * @author Jonathan Guéhenneux
 */
public class PairNumberCell extends TableCell<MovePair, Number> {

	private static Font defaultFont;

	/**
	 * @param defaultFont
	 */
	public static void setDefaultFont(Font defaultFont) {
		PairNumberCell.defaultFont = defaultFont;
	}

	/**
	 * @param column
	 */
	public PairNumberCell(TableColumn<MovePair, Number> column) {
		setFont(defaultFont);
	}

	@Override
	protected void updateItem(Number pairNumber, boolean empty) {

		super.updateItem(pairNumber, empty);

		if (empty) {
			setGraphic(null);
		} else {
			setText(pairNumber.toString());
		}
	}
}