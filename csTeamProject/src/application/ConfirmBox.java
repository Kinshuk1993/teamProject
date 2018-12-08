package application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
public class ConfirmBox extends BorderPane {

	public static boolean answer;
	@FXML
	BorderPane root_pane;
	@FXML
	Label label;
	@FXML
	Button yesButton;
	@FXML
	Button noButton;

	public ConfirmBox() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfirmBox.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@FXML
	private void initialize() {
		yesButton.setOnAction(e -> {
			answer = true;
			Platform.exit();
		});
		noButton.setOnAction(e -> {
			answer = false;
			Stage stage = (Stage) yesButton.getScene().getWindow();
			stage.close();
			//          Platform.exit();
		});
	}

	public static boolean display() {
		return answer;
	}
}