package application;
//import custom and existing files and packages
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
//import controller.test;
public class javafx extends Application {

	static Stage window;
	static Layout newLayout;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		newLayout = new Layout();
		javafx.window = primaryStage;
		BorderPane borderPane = new BorderPane();
		try {
			Scene scene = new Scene(borderPane, 640, 480);
			// Setting the title to Stage.
			javafx.window.setTitle("Project");
			javafx.window.setOnCloseRequest(e -> {
				e.consume();
				closeProgram();
			});
			// Adding the scene to Stage
			javafx.window.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("/fxml/app.css").toExternalForm());
			// open the application in maximized mode
			javafx.window.setMaximized(true);
			// Displaying the contents of the stage
			javafx.window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		borderPane.setCenter(newLayout);
	}

	public void closeProgram() {
		Stage newWindow = new Stage();
		BorderPane newPane = new BorderPane();
		Scene newScene = new Scene(newPane, 200, 100);
		// stop user from accessing any other dialog box other than the current one
		newWindow.initModality(Modality.APPLICATION_MODAL);
		// remove minimize and maximize button
		newWindow.resizableProperty().setValue(Boolean.FALSE);
		newWindow.getIcons().add(new Image(getClass().getResource("/resources/close-icon.png").toExternalForm()));
		newWindow.setTitle("Close Confirmation");
		newWindow.setScene(newScene);
		newWindow.show();
		ConfirmBox confirmbox = new ConfirmBox();
		newPane.setCenter(confirmbox);
		Boolean answer = ConfirmBox.display();
		if (answer) {
			window.close();
		}
	}
}
