package fr.guehenneux.chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author Jonathan Guéhenneux
 */
public class Test extends Application {

	/**
	 * @param arguments
	 *            none
	 */
	public static void main(String... arguments) {
		launch(arguments);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {

			Screen screen = Screen.getPrimary();
			double dpi = screen.getDpi();

			Chess model = new Chess();
			ChessUI ui = new ChessUI(model, dpi);

			model.setUI(ui);

			Group root = new Group(ui);
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.setTitle("chess test");
			primaryStage.show();

			new Thread(model).start();

		} catch (Throwable throwable) {

			throwable.printStackTrace();
		}
	}

	@Override
	public void stop() throws Exception {

		Platform.exit();
		System.exit(0);
	}
}