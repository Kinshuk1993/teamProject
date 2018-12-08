package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
public class javafx extends Application {

	Stage window;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		BorderPane borderPane = new BorderPane();
		try {
			Scene scene = new Scene(borderPane, 640, 480);
			// Setting the title to Stage.
			window.setTitle("Project");
			window.setOnCloseRequest(e -> {
				e.consume();
				closeProgram();
			});
			// Adding the scene to Stage
			window.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			// Displaying the contents of the stage
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		borderPane.setCenter(new Layout());
	}

	private void closeProgram() {
		Stage newWindow = new Stage();
		BorderPane newPane = new BorderPane();
		Scene newScene = new Scene(newPane, 200, 100);
		newWindow.setTitle("Warning");
		newWindow.setScene(newScene);
		newWindow.show();
		ConfirmBox confirmbox = new ConfirmBox();
		newPane.setCenter(confirmbox);
		Boolean answer = ConfirmBox.display();
		if (answer)
			window.close();
	}
}