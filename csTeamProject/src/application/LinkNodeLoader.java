/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Class to handle the opening of the dialog box for Linking Nodes - FXML Loader
 * 
 */

/**
 * package declaration
 */
package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LinkNodeLoader {
	
	//to store the node id of current node clicked on
	public static String currentNodeID;
	
	public LinkNodeLoader(String id) throws Exception {
		//assign current node id to class string variable
		LinkNodeLoader.currentNodeID = id;
		//load the FXML file
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LinkNode.fxml"));
		//create a parent root for the new view
        Parent linkNodeParent = (Parent) fxmlLoader.load();
        //create a new stage for create application
        Stage linkNodeStage = new Stage();
        linkNodeStage.getIcons().add(new Image(getClass().getResource("/resources/create-app-icon.png").toExternalForm()));
        //block using of any other window of current application
        linkNodeStage.initModality(Modality.APPLICATION_MODAL);
        //remove minimize and maximize button
        linkNodeStage.resizableProperty().setValue(Boolean.FALSE);
        //set the title of the stage
        linkNodeStage.setTitle("Create Node Link");
        //set the scene of the window
        linkNodeStage.setScene(new Scene(linkNodeParent));
        //make visible the current stage
        linkNodeStage.show();
	}
}
