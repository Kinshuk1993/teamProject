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

	// FXML fields for buttons, drop down list, and dynamic label in node link
	// pop-up
	@FXML
	ChoiceBox<String> nodeListForLink;
	@FXML
	Button addNodeToLinkListButton, createNodeLink, cancelButtonForLink;
	@FXML
	Label dynamicNodeLinkLabel, existingLinks;
	// list to store all nodes to which the current node is to be linked
	private ArrayList<String> listofNodesToLinkCurrentNode = new ArrayList<String>();

	/**
	 * initializer to load the contents for create node link dialogue
	 */
	@FXML
	private void initialize() {
		// perform linking only if the the node in the scene is clicked on else not
		if (!LinkNodeLoader.currentNodeID.equals("icon")) {
			for (Node eachNode : Controller.getNodes()) {
				// if current node matches the node in the all node array list
				if (eachNode.getId().equals(LinkNodeLoader.currentNodeID)) {
					// if no linked nodes are available or if it is null
					if (eachNode.getNodesLinkedTo() == null || eachNode.getNodesLinkedTo().size() == 0) {
						// display message if no link available
						existingLinks.setText(
								LinkNodeLoader.currentNodeID.substring(1, LinkNodeLoader.currentNodeID.length() - 1)
										+ " is not linked to any other Node");
						// make text bold
						existingLinks.setStyle("-fx-font-weight: bold");
						// wrap the text of label
						existingLinks.setWrapText(true);
					} else { // if link exists
						// display the links of current node
						existingLinks.setText(eachNode.getNodesLinkedTo().toString().substring(1,
								eachNode.getNodesLinkedTo().toString().length() - 1));
					}
				}
			}
			// add a default value to the drop down list
			nodeListForLink.getItems().add("Select");
			// if only one node present till now, do not populate the node list and disable
			// all buttons and fields
			if (Controller.getNodes() == null || Controller.getNodes().size() == 1) {
				// disable the drop down, the add node to link list button, and the create link
				// button
				nodeListForLink.setDisable(true);
				addNodeToLinkListButton.setDisable(true);
				createNodeLink.setDisable(true);
				// change the label accordingly
				dynamicNodeLinkLabel.setText("No nodes available. Add more node(s) to link current node to.");
				dynamicNodeLinkLabel.setStyle("-fx-font-weight: bold");
				// wrap the text of label
				dynamicNodeLinkLabel.setWrapText(true);
				// action on the cancel button
				cancelButtonForLink.setOnAction(cancelEvent -> {
					// close the current dialog box
					closeDialog();
				});
			} else {
				// populate the drop down list with all available nodes
				for (Node eachNode : Controller.getNodes()) {
					// add the node id trimmed of the first and last character as the node id format
					// is {a}
					// do not add the current node to the list as it can't be linked to itself
					if (!LinkNodeLoader.currentNodeID.equals(eachNode.getId())) {
						// to the drop down item
						nodeListForLink.getItems().add(eachNode.getId().substring(1, eachNode.getId().length() - 1));
					}
				}
				// select the first item to display in the drop down by default
				nodeListForLink.getSelectionModel().selectFirst();
				// when add node to app button is clicked listener
				addNodeToLinkListButton.setOnAction(addNodeEvent -> {
					// add nodes to list only if selected value is not "Select" and is not already
					// present in the list
					if (nodeListForLink.getValue().equalsIgnoreCase("select")
							|| (listofNodesToLinkCurrentNode.contains(nodeListForLink.getValue()))) {
					} else {
						// add selected value to list
						listofNodesToLinkCurrentNode.add(nodeListForLink.getValue());
					}
					// show user the selected nodes only if the list is not empty
					if (!listofNodesToLinkCurrentNode.isEmpty()) {
						// set the label to the node list
						dynamicNodeLinkLabel.setText(listofNodesToLinkCurrentNode.toString().substring(1,
								listofNodesToLinkCurrentNode.toString().length() - 1));
					}
				});
				// action on the cancel button
				cancelButtonForLink.setOnAction(cancelEvent -> {
					// close the current dialog box
					closeDialog();
				});
				// listener for creating the link
				createNodeLink.setOnAction(createNodeLink -> {
					// variables to store the coordinates of the node
					double startX = 0.0, startY = 0.0, endX = 0.0, endY = 0.0;
					// check if the node list to attach to application is empty or not
					if (!listofNodesToLinkCurrentNode.isEmpty()) {
						// iterate through each selected node
						for (String eachSelectedNode : listofNodesToLinkCurrentNode) {
							// iterate through each of all nodes as to get the linked node list (as it's
							// accessible using an object only
							for (Node eachNode : Controller.getNodes()) {
								// if it is current node and no existing links already exists then add new link
								// else do nothing and close the dialog box
								// String for link
								String linkname = "";
								
								// for all nodes present in the application, get the current and target nodes
								// coordinates
								for (DragableNode eachDraggableNode : Layout.AllNodesCreated) {
									// for current node
									if (eachDraggableNode.id.equals(LinkNodeLoader.currentNodeID)) {
										// set start X
										startX = eachDraggableNode.xCoord;
										// set start Y
										startY = eachDraggableNode.yCoord;
									}
									// for target node
									if (eachDraggableNode.id.equals("{" + eachSelectedNode + "}")) {
										// set end X
										endX = eachDraggableNode.xCoord;
										// set end Y
										endY = eachDraggableNode.yCoord;
									}
								}
								if (LinkNodeLoader.currentNodeID.equals(eachNode.getId())
										&& !eachNode.getNodesLinkedTo().contains(eachSelectedNode)) {
									// create the link for each node
									linkname = Controller.addNewLink(LinkNodeLoader.currentNodeID, "{" + eachSelectedNode + "}");
									//add graphical link
									javafx.newLayout.addLinkNodeLine(startX, startY, endX, endY, linkname);
								}
							}
						}
					}
					// close the current dialog box
					closeDialog();
				});
			}
		} else { // if the node click is not the node in the scene, disable all buttons, labels and choice boxes
			nodeListForLink.setDisable(true);
			addNodeToLinkListButton.setDisable(true);
			createNodeLink.setDisable(true);
			cancelButtonForLink.setDisable(true);
			dynamicNodeLinkLabel.setDisable(true);
			existingLinks.setDisable(true);
		}
	}

	/**
	 * Function to close the current dialog
	 */
	public void closeDialog() {
		// get current window
		Stage stage = (Stage) cancelButtonForLink.getScene().getWindow();
		// close the current window
		stage.close();
	}
}
