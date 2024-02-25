package com.github.achaaab.chess;

import com.github.achaaab.chess.ui.ChessUi;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Jonathan Guéhenneux
 */
public class Test extends Application {

	private static final Logger LOGGER = getLogger(Test.class);

	/**
	 * @param arguments none
	 */
	public static void main(String... arguments) {
		launch(arguments);
	}

	@Override
	public void start(Stage primaryStage) {

		try {

			Chess model = new Chess();
			ChessUi ui = new ChessUi(model);

			model.setUI(ui);

			Group root = new Group(ui);
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.setTitle("chess test");
			primaryStage.show();

			new Thread(model).start();

		} catch (Exception exception) {

			LOGGER.error(exception.getMessage(), exception);
			throw exception;
		}
	}

	@Override
	public void stop() {

		Platform.exit();
		System.exit(0);
	}
}
