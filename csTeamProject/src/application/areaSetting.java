package application;
import java.io.IOException;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
public class areaSetting extends BorderPane {

	String name;
	@FXML
	BorderPane root_pane;
	@FXML
	Button saveButton;
	@FXML
	TextField areaName;

	public areaSetting() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/areaSetting.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@FXML
	public void initialize() {
		// using boolean binding to bind the text field value containing the area name
		// and save area name button
		// to enable/disable the button
		BooleanBinding bindAreaNameToSaveButton = new BooleanBinding() {

			{
				// create object binding the text field property
				super.bind(areaName.textProperty());
			}

			@Override
			protected boolean computeValue() {
				// custom return value to return is the test field is empty or not
				return (areaName.getText().isEmpty());
			}
		};
		// bind the save area name button's disable property to the above object
		saveButton.disableProperty().bind(bindAreaNameToSaveButton);
	}
}
