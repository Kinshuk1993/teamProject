/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Controller class to handle the operations of the linking a node to other nodes
 * 
 */

/**
 * package declaration
 */
package application;
//import packages
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import controller.Controller;
import controller.Node;
import javafx.fxml.FXML;

public class LinkNodeController {
	
	//FXML fields for buttons, drop down list, and dynamic label in node link pop-up
	@FXML
	ChoiceBox<String> nodeListForLink;
	@FXML
	Button addNodeToLinkListButton, createNodeLink, cancelButtonForLink;
	@FXML
	Label dynamicNodeLinkLabel;
	//list to store all nodes to which the current node is to be linked
	private ArrayList<String> listofNodesToLinkCurrentNode = new ArrayList<String>();
	
	/**
	 * initializer to load the contents for create node link dialogue
	 */
	@FXML
	private void initialize(){
		//add a default value to the drop down list
		nodeListForLink.getItems().add("Select");
		//if only one node present till now, do not populate the node list and disable all buttons and fields
		if (Controller.getNodes()==null || Controller.getNodes().size()==1) {
			//disable the drop down, the add node to link list button, and the create link button
			nodeListForLink.setDisable(true);
			addNodeToLinkListButton.setDisable(true);
			createNodeLink.setDisable(true);
			//change the label accordingly
			dynamicNodeLinkLabel.setText("Add more node(s) to link current node to.");
			dynamicNodeLinkLabel.setStyle("-fx-font-weight: bold");
			//wrap the text of label
			dynamicNodeLinkLabel.setWrapText(true);
			//action on the cancel button
			cancelButtonForLink.setOnAction(cancelEvent -> {
				//close the current dialog box
				closeDialog();
			});
		} else {
			//populate the drop down list with all available nodes
			for (Node eachNode : Controller.getNodes()) {
				//add the node id trimmed of the first and last character as the node id format is {a}
				//do not add the current node to the list as it can't be linked to itself
				if (!LinkNodeLoader.currentNodeID.equals(eachNode.getId())) {
					//to the drop down item
					nodeListForLink.getItems().add(eachNode.getId().substring(1, eachNode.getId().length()-1));
				}
			}
			//select the first item to display in the drop down by default
			nodeListForLink.getSelectionModel().selectFirst();
			//when add node to app button is clicked listener
			addNodeToLinkListButton.setOnAction(addNodeEvent -> {
				//add nodes to list only if selected value is not "Select" and is not already present in the list
				if (nodeListForLink.getValue().equalsIgnoreCase("select") || (listofNodesToLinkCurrentNode.contains(nodeListForLink.getValue()))) {}
				else {
					//add selected value to list
					listofNodesToLinkCurrentNode.add(nodeListForLink.getValue());
				}
				//show user the selected nodes only if the list is not empty
				if (!listofNodesToLinkCurrentNode.isEmpty()) {
					//set the label to the node list
					dynamicNodeLinkLabel.setText(listofNodesToLinkCurrentNode.toString().substring(1, listofNodesToLinkCurrentNode.toString().length()-1));
				}
			});
			//action on the cancel button
			cancelButtonForLink.setOnAction(cancelEvent -> {
				//close the current dialog box
				closeDialog();
			});
			//listener for creating the link
			createNodeLink.setOnAction(createNodeLink -> {
				//check if the node list to attach to application is empty or not
				if (!listofNodesToLinkCurrentNode.isEmpty()) {
					//iterate through each selected node and create a link between them
					for (String eachSelectedNode: listofNodesToLinkCurrentNode) {
						//create the link for each node
						Controller.addNewLink(LinkNodeLoader.currentNodeID, "{"+eachSelectedNode+"}");
					}
				}
				//close the current dialog box
				closeDialog();
			});
		}
	}
	
	/**
	 * Function to close the current dialog
	 */
	public void closeDialog() {
		//get current window
		Stage stage = (Stage) cancelButtonForLink.getScene().getWindow();
		//close the current window
		stage.close();
	}
}
