/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Controller class to handle the operations of the application creation
 * 
 */

/**
 * package declaration
 */
package application;

//import packages
import controller.Controller;
import controller.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddApplicationController {
	
	//FXML instances for the IDs created in FXML file
	@FXML
	TextField appName;
	@FXML
	private ChoiceBox<String> nodeList;
	@FXML
	Button addNodeToAppButton, createAppButton, cancelCreateAppButton;
	@FXML
	Label dynamicNodeListLabel;
	
	/**
	 * initializer to load the contents for create application dialogue
	 */
	@FXML
	private void initialize(){
		//print the each Node id
		for (Node eachNode : Controller.getNodes()) {
			//add the node id trimmed of the first and last character as the node id format is {a}
			//to the drop down item
			nodeList.getItems().add(eachNode.getId().substring(1, eachNode.getId().length()-1));
		}
		//select the first item to display in the dropdown by default
		nodeList.getSelectionModel().selectFirst();
	}
}
