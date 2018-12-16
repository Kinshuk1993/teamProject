/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Class to handle the functionalities and the working of the
 * about section of the application
 */
/**
 * package declaration
 */
package application;
import java.io.BufferedReader;
import java.io.FileReader;
//import packages
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
public class AboutHelpController {

	@FXML
	VBox vboxHelpText;

	/**
	 * initializer to load the contents for about application help dialog
	 */
	@FXML
	private void initialize() {
		// variable to store each line from the helper file
		String line = "";
		// try-catch to do file handling exceptions
		try {
			// read the file
			FileReader fileReader = new FileReader(AboutHelpController.class.getResource("/application/help.txt").getPath());
			// create a buffer reader for reading each line
			BufferedReader buffer = new BufferedReader(fileReader);
			// read till end of file
			while ((line = buffer.readLine()) != null) {
				// create a label
				TextField eachAppNameText = new TextField(line);
				// set the padding for each label
				eachAppNameText.setPadding(new Insets(5, 0, 0, 11));
				// set font size, weight,
				// background color and opacity: INLINE
				eachAppNameText.setStyle(
						"-fx-font-size: 16px; -fx-font-weight: bold; -fx-opacity: 1; -fx-background-color: transparent;");
				// make the text field disabled
				eachAppNameText.setDisable(true);
				// add the label to the vbox pane
				vboxHelpText.getChildren().add(eachAppNameText);
			}
			// close the file reader
			buffer.close();
		} catch (Exception e) { // catch block to handle the exception
			// print the error as is
			e.printStackTrace();
		}
	}
}
