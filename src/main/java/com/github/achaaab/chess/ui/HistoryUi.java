package com.github.achaaab.chess.ui;

import com.github.achaaab.chess.Chess;
import com.github.achaaab.chess.move.MovePair;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

/**
 * @author Jonathan Guéhenneux
 */
public class HistoryUi extends TableView<MovePair> {

	private static final String FONT_NAME = "Arial Unicode MS";
	private static final double FONT_SIZE_INCHES = 0.18;

	/**
	 * @param movePair
	 * @return
	 */
	private static ObservableValue<Number> getNumber(CellDataFeatures<MovePair, Number> movePair) {

		var number = movePair.getValue().number();
		return new SimpleIntegerProperty(number);
	}

	/**
	 * @param movePair
	 * @return
	 */
	private static ObservableValue<String> getWhiteMove(CellDataFeatures<MovePair, String> movePair) {

		var whiteMove = movePair.getValue().whiteMove();
		return new SimpleStringProperty(whiteMove.toString() + " (" + whiteMove.getValue() + ")");
	}

	/**
	 * @param movePair
	 * @return
	 */
	private static ObservableValue<String> getBlackMove(CellDataFeatures<MovePair, String> movePair) {

		var blackMove = movePair.getValue().blackMove();

		return blackMove == null ?
				null :
				new SimpleStringProperty(blackMove.toString());
	}

	private final Chess chess;

	/**
	 * @param chess
	 * @param dpi
	 */
	public HistoryUi(Chess chess, double dpi) {

		this.chess = chess;

		var font = Font.font(FONT_NAME, FONT_SIZE_INCHES * dpi);
		PairNumberCell.setDefaultFont(font);
		MoveCell.setDefaultFont(font);

		var pairNumbers = new TableColumn<MovePair, Number>();
		var whiteMoves = new TableColumn<MovePair, String>("White moves");
		var blackMoves = new TableColumn<MovePair, String>("Black moves");

		pairNumbers.setPrefWidth(0.5 * dpi);
		whiteMoves.setPrefWidth(1.5 * dpi);
		blackMoves.setPrefWidth(1.5 * dpi);

		pairNumbers.setCellFactory(PairNumberCell::new);
		whiteMoves.setCellFactory(MoveCell::new);
		blackMoves.setCellFactory(MoveCell::new);

		pairNumbers.setCellValueFactory(HistoryUi::getNumber);
		whiteMoves.setCellValueFactory(HistoryUi::getWhiteMove);
		blackMoves.setCellValueFactory(HistoryUi::getBlackMove);

		getColumns().add(pairNumbers);
		getColumns().add(whiteMoves);
		getColumns().add(blackMoves);
	}

	/**
	 * 
	 */
	public void update() {

		var movePairs = chess.getMovePairs();
		getItems().setAll(movePairs);
	}
}
