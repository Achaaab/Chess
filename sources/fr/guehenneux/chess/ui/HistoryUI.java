package fr.guehenneux.chess.ui;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.chess.Chess;
import fr.guehenneux.chess.move.MovePair;

/**
 * @author Jonathan Guéhenneux
 */
public class HistoryUI extends TableView<MovePair> {

	private static final String FONT_NAME = "Arial Unicode MS";
	private static final double FONT_SIZE_INCHES = 0.18;

	/**
	 * @return
	 */
	private static ObservableValue<Number> getNumber(CellDataFeatures<MovePair, Number> movePair) {

		int number = movePair.getValue().getNumber();
		return new SimpleIntegerProperty(number);
	}

	/**
	 * @return
	 */
	private static ObservableValue<String> getWhiteMove(CellDataFeatures<MovePair, String> movePair) {

		Move whiteMove = movePair.getValue().getWhiteMove();
		return new SimpleStringProperty(whiteMove.toString() + " (" + whiteMove.getValue() + ")");
	}

	/**
	 * @return
	 */
	private static ObservableValue<String> getBlackMove(CellDataFeatures<MovePair, String> movePair) {

		Move blackMove = movePair.getValue().getBlackMove();

		if (blackMove == null) {
			return null;
		} else {
			return new SimpleStringProperty(blackMove.toString());
		}
	}

	private Chess chess;

	private TableColumn<MovePair, Number> pairNumbers;
	private TableColumn<MovePair, String> whiteMoves;
	private TableColumn<MovePair, String> blackMoves;

	/**
	 * @param chess
	 * @param dpi
	 */
	public HistoryUI(Chess chess, double dpi) {

		this.chess = chess;

		Font font = Font.font(FONT_NAME, FONT_SIZE_INCHES * dpi);
		PairNumberCell.setDefaultFont(font);
		MoveCell.setDefaultFont(font);

		pairNumbers = new TableColumn<>();
		whiteMoves = new TableColumn<>("White moves");
		blackMoves = new TableColumn<>("Black moves");

		pairNumbers.setPrefWidth(0.5 * dpi);
		whiteMoves.setPrefWidth(1.2 * dpi);
		blackMoves.setPrefWidth(1.2 * dpi);

		pairNumbers.setCellFactory(PairNumberCell::new);
		whiteMoves.setCellFactory(MoveCell::new);
		blackMoves.setCellFactory(MoveCell::new);

		pairNumbers.setCellValueFactory(HistoryUI::getNumber);
		whiteMoves.setCellValueFactory(HistoryUI::getWhiteMove);
		blackMoves.setCellValueFactory(HistoryUI::getBlackMove);

		getColumns().add(pairNumbers);
		getColumns().add(whiteMoves);
		getColumns().add(blackMoves);
	}

	/**
	 * 
	 */
	public void update() {

		List<MovePair> movePairs = chess.getMovePairs();
		getItems().setAll(movePairs);
	}
}