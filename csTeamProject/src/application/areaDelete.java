package application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
public class areaDelete extends AnchorPane {

	@FXML
	AnchorPane root_pane;
	@FXML
	Button yesButton;
	@FXML
	Button cancelButton;

	public areaDelete() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/areaDelete.fxml"));
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
		
		cancelButton.setOnAction(e -> {
			Stage stage = (Stage) yesButton.getScene().getWindow();
			stage.close();
		});
	}
}