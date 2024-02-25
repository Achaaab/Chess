package com.github.achaaab.chess.ui;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import com.github.achaaab.chess.Chess;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.currentThread;
import static javafx.application.Platform.isFxApplicationThread;

/**
 * @author Jonathan Guéhenneux
 */
public class ChessUi extends GridPane {

	private BoardUi boardUI;
	private HistoryUi historyUI;

	/**
	 * @param chess
	 */
	public ChessUi(Chess chess) {

		Screen screen = Screen.getPrimary();
		double dpi = screen.getDpi();

		boardUI = new BoardUi(chess, dpi);
		historyUI = new HistoryUi(chess, dpi);

		add(boardUI, 0, 0);
		add(historyUI, 1, 0);
	}

	/**
	 * 
	 */
	public void update() {

		if (isFxApplicationThread()) {

			boardUI.update();
			historyUI.update();

		} else {

			var futureTask = new FutureTask<>(() -> {
				boardUI.update();
				historyUI.update();
			}, null);

			Platform.runLater(futureTask);

			try {
				futureTask.get();
			} catch (InterruptedException interruptedException) {
				currentThread().interrupt();
			} catch (ExecutionException cause) {
				throw new RuntimeException(cause);
			}
		}
	}

	/**
	 * @param selectionHandler
	 */
	public void addSelectionHandler(SquareSelectionHandler selectionHandler) {
		Platform.runLater(() -> boardUI.addSelectionHandler(selectionHandler));
	}
}
