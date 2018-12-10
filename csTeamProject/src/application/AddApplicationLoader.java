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

//import packages
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddApplicationLoader {
	
	public AddApplicationLoader() throws Exception {
		//test code to create a list of nodes and a bigraph finally
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
}
