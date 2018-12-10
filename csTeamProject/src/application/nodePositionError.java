package application;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class nodePositionError extends AnchorPane {	
	public nodePositionError() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/nodePositionError.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	@FXML
	private void initialize() {}
}
