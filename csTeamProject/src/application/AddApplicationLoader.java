/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Class to handle the opening of the dialog box for creating application - FXML Loader
 * 
 */

/**
 * package declaration
 */
package application;

import controller.Apps;
import controller.Controller;
//import packages
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddApplicationLoader {
	
	static VBox appLoaderVBox;
	
	public AddApplicationLoader(VBox vBoxForAreaNames) throws Exception {
		appLoaderVBox = vBoxForAreaNames;
		//load the FXML file
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AddApplication.fxml"));
		//create a parent root for the new view
        Parent createApplicationParent = (Parent) fxmlLoader.load();
        //create a new stage for create application
        Stage createApplicationStage = new Stage();
        createApplicationStage.getIcons().add(new Image(getClass().getResource("/resources/create-app-icon.png").toExternalForm()));
        //block using of any other window of current application
        createApplicationStage.initModality(Modality.APPLICATION_MODAL);
        //remove minimize and maximize button
        createApplicationStage.resizableProperty().setValue(Boolean.FALSE);
        //set the title of the stage
        createApplicationStage.setTitle("Create Application");
        //set the scene of the window
        createApplicationStage.setScene(new Scene(createApplicationParent));
        //make visible the current stage
        createApplicationStage.show();
        //check before closing the program - COMMENTING OUT AS THIS CLOSES THE WHOLE APPLICATION
//        createApplicationStage.setOnCloseRequest(e -> {
//        	//take away java's control of close event
//			e.consume();
//			//manually handle the close window event
//			new javafx().closeProgram();
//		});
	}
	
	/**
	 * Function to print all the applications in the application
	 */
	public static void updateApplicationList() {
		//coutner
		int i = 0;
		//clear contents before writing again
		appLoaderVBox.getChildren().clear();
		//loop through all available applications
		for (Apps eachApp: Controller.getApps()) {
			//get the label for the string ready
			String temp = ++i + ". " + eachApp.getName();
			//create a label
			Label eachAppNameLabel = new Label(temp);
			//add the label to the vertical box
			appLoaderVBox.getChildren().add(eachAppNameLabel);
		}
	}
}
