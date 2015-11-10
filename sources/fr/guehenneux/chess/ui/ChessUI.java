package fr.guehenneux.chess.ui;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import fr.guehenneux.chess.Chess;

/**
 * @author Jonathan Guéhenneux
 */
public class ChessUI extends GridPane {

	private BoardUI boardUI;
	private HistoryUI historyUI;

	/**
	 * @param chess
	 */
	public ChessUI(Chess chess) {

		Screen screen = Screen.getPrimary();
		double dpi = screen.getDpi();

		boardUI = new BoardUI(chess, dpi);
		historyUI = new HistoryUI(chess, dpi);

		add(boardUI, 0, 0);
		add(historyUI, 1, 0);
	}

	/**
	 * 
	 */
	public void update() {

		Platform.runLater(() -> {

			boardUI.update();
			historyUI.update();
		});
	}

	/**
	 * @param selectionHandler
	 */
	public void addSelectionHandler(SquareSelectionHandler selectionHandler) {
		Platform.runLater(() -> boardUI.addSelectionHandler(selectionHandler));
	}
}