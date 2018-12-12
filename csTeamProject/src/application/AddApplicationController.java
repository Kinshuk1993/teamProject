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
import java.util.ArrayList;
import controller.Controller;
import controller.Node;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class AddApplicationController {

	// FXML instances for the IDs created in FXML file
	@FXML
	TextField appName;
	@FXML
	private ChoiceBox<String> nodeList;
	@FXML
	Button addNodeToAppButton, createAppButton, cancelCreateAppButton;
	@FXML
	Label dynamicNodeListLabel;
	private ArrayList<String> listofNodesToAddToApp = new ArrayList<String>();

	/**
	 * initializer to load the contents for create application dialogue
	 */
	@FXML
	private void initialize() {
		// using boolean binding to bind the text field value containing the app name
		// and create app button
		// to enable/disable the button
		BooleanBinding bindNameTextFieldToButton = new BooleanBinding() {

			{
				// create object binding the text field property
				super.bind(appName.textProperty());
			}

			@Override
			protected boolean computeValue() {
				// custom return value to return is the test field is empty or not
				return (appName.getText().isEmpty());
			}
		};
		// bind the create app button's disable property to the above object
		createAppButton.disableProperty().bind(bindNameTextFieldToButton);
		// add a default value to the drop down list
		nodeList.getItems().add("Select");
		// if no nodes are present in the scene
		// if no nodes present till now, do not populate the node list
		if (Controller.getNodes() == null || Controller.getNodes().size() == 0) {
			// disable the drop down and the add node to app button
			nodeList.setDisable(true);
			addNodeToAppButton.setDisable(true);
			// change the label accordingly
			dynamicNodeListLabel.setText("No nodes are available, but you may create an App");
			// wrap the text of label
			dynamicNodeListLabel.setWrapText(true);
			// create app without any node in it
			createAppButton.setOnAction(clickEvent -> {
				Controller.newApp(appName.getText());
				// update the application list in the application tab with the new application
				// created
				AddApplicationLoader.updateApplicationList();
				// close the current dialog box
				closeDialog();
			});
			// action on the cancel button
			cancelCreateAppButton.setOnAction(cancelEvent -> {
				// close the current dialog box
				closeDialog();
			});
		} else {
			// populate the drop down list with all available nodes
			for (Node eachNode : Controller.getNodes()) {
				// add the node id trimmed of the first and last character as the node id format
				// is {a}
				// to the drop down item
				nodeList.getItems().add(eachNode.getId().substring(1, eachNode.getId().length() - 1));
			}
			// select the first item to display in the drop down by default
			nodeList.getSelectionModel().selectFirst();
			// when add node to app button is clicked listener
			addNodeToAppButton.setOnAction(addNodeEvent -> {
				// add nodes to list only if selected value is not "Select" and is not already
				// present in the list
				if (nodeList.getValue().equalsIgnoreCase("select")
						|| (listofNodesToAddToApp.contains(nodeList.getValue()))) {
				} else {
					// add selected value to list
					listofNodesToAddToApp.add(nodeList.getValue());
				}
				// show user the selected nodes only if the list is not empty
				if (!listofNodesToAddToApp.isEmpty()) {
					// set the label to the node list
					dynamicNodeListLabel.setText(listofNodesToAddToApp.toString().substring(1,
							listofNodesToAddToApp.toString().length() - 1));
				}
			});
			// listener for create app with nodes present
			createAppButton.setOnAction(clickEvent -> {
				// create the application with application name given by the user
				String appId = Controller.newApp(appName.getText());
				// check if the node list to attach to application is empty or not
				if (!listofNodesToAddToApp.isEmpty()) {
					// iterate through each selected node and add to the area
					for (String eachSelectedNode : listofNodesToAddToApp) {
						// add the node to the application
						Controller.addAppToNode(appId, "{" + eachSelectedNode + "}");
					}
				}
				// update the application list in the application tab with the new application
				// created
				AddApplicationLoader.updateApplicationList();
				// close the current dialog box
				closeDialog();
			});
			// action on the cancel button
			cancelCreateAppButton.setOnAction(cancelEvent -> {
				// close the current dialog box
				closeDialog();
			});
		}
	}

	/**
	 * Function to close the current dialog
	 */
	public void closeDialog() {
		// get current window
		Stage stage = (Stage) createAppButton.getScene().getWindow();
		// close the current window
		stage.close();
	}
}
