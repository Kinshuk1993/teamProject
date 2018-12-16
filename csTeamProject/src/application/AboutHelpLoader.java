/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Class to handle the loading of the about section of the application
 * 
 */
/**
 * package declaration
 */
package application;
//import packages
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AboutHelpLoader {

	public AboutHelpLoader() throws Exception {
		// load the FXML file
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AboutHelp.fxml"));
		// create a parent root for the new view
		Parent applicationHelpParent = (Parent) fxmlLoader.load();
		// create a new stage for create application
		Stage applicationHelp = new Stage();
		applicationHelp.getIcons()
				.add(new Image(getClass().getResource("/resources/create-app-icon.png").toExternalForm()));
		// block using of any other window of current application
		applicationHelp.initModality(Modality.APPLICATION_MODAL);
		// remove minimize and maximize button
		applicationHelp.resizableProperty().setValue(Boolean.FALSE);
		// set the title of the stage
		applicationHelp.setTitle("About Application");
		// set the scene of the window
		applicationHelp.setScene(new Scene(applicationHelpParent));
		// make visible the current stage
		applicationHelp.show();
	}
}
