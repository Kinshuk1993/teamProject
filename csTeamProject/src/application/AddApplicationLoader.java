/** 
updateApplicationList* University of Glasgow
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
import java.util.Random;
import controller.Apps;
import controller.Controller;
//import controller.Node;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AddApplicationLoader {

	static VBox appLoaderVBox;

	public AddApplicationLoader(VBox vBoxForAreaNames) throws Exception {
		// setting the vertical box to local static variable
		appLoaderVBox = vBoxForAreaNames;
		// load the FXML file
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AddApplication.fxml"));
		// create a parent root for the new view
		Parent createApplicationParent = (Parent) fxmlLoader.load();
		// create a new stage for create application
		Stage createApplicationStage = new Stage();
		createApplicationStage.getIcons()
				.add(new Image(getClass().getResource("/resources/create-app-icon.png").toExternalForm()));
		// block using of any other window of current application
		createApplicationStage.initModality(Modality.APPLICATION_MODAL);
		// remove minimize and maximize button
		createApplicationStage.resizableProperty().setValue(Boolean.FALSE);
		// set the title of the stage
		createApplicationStage.setTitle("Create Application");
		// set the scene of the window
		createApplicationStage.setScene(new Scene(createApplicationParent));
		// make visible the current stage
		createApplicationStage.show();
	}

	/**
	 * Function to print all the applications in the application
	 */
	public static void updateApplicationList() {
		String nodes = "[ ";
		// counter variable
		int i = 0;
		// clear contents before writing again only if the application list is not empty
		if (appLoaderVBox != null) {
			appLoaderVBox.getChildren().clear();
		}
		// loop through all available applications
		for (Apps eachApp : Controller.getApps()) {
			nodes = "[ ";
			// get the label for the string ready
			String eachNewAppName = ++i + ". " + eachApp.getName() + " " + eachApp.getId();
			for (controller.Node eachNode : Controller.getNodes()) {
				// if application exists in node list
				if (eachNode.getApps().contains(eachApp)) {
					//first add
					if (nodes.length() == 2) {
						// add the node name
						nodes = nodes + eachNode.getId().substring(1, eachNode.getId().length() - 1);
					} else { // all subsequent additions
						// add the node name
						nodes = nodes + ", " + eachNode.getId().substring(1, eachNode.getId().length() - 1);
					}
				}
			}
			if (nodes.length() == 2) {
				nodes = nodes + "No nodes linked";
			}
			nodes = nodes + " ]";
			eachNewAppName = eachNewAppName + " - " + nodes;
			// create a label
			Label eachAppNameText = new Label(eachNewAppName);
			// COMMENTING OUT FOR FURTHER EXPLORATION - DO NOT REMOVE
//			eachAppNameText.setOnMouseEntered(e -> {
//				String eachAppNodeList = "";
//				for (Node eachNode : Controller.getNodes()) {
//					if (eachNode.getApps().contains(eachApp)) {
//						eachAppNodeList = eachAppNodeList + ", " + eachNode.getId();
//					}
//				}
//				Tooltip tooltip = new Tooltip();
//				if (eachAppNodeList.length() == 0) {
//					tooltip.setText("No nodes linked to this application");
//				} else {
//					tooltip.setText(eachAppNodeList);
//				}
//				eachAppNameText.setTooltip(tooltip);
//			});
			// set the padding for each label
			eachAppNameText.setPadding(new Insets(5, 0, 0, 11));
			// set a random color to the application names, set font size, weight,
			// background color and opacity: INLINE
			eachAppNameText.setStyle("-fx-text-fill: " + AddApplicationLoader.generateRandomColor()
					+ "; -fx-font-size: 16px; -fx-font-weight: bold; -fx-opacity: 1; -fx-background-color: transparent;");
			// make the text field disabled
			eachAppNameText.setDisable(true);
			// add the label to the vertical box
			appLoaderVBox.getChildren().add(eachAppNameText);
		}
	}

	/**
	 * Function to set action for tool tips
	 */
	public static void actionOnMouseOver() {
		// do only if applications present
		if (AddApplicationLoader.appLoaderVBox != null) {
			// for all applications
			for (Node node : AddApplicationLoader.appLoaderVBox.getChildren()) {
				// if it is a label
				if (node instanceof Label) {
					// get the application using the ID
					Apps eachApp = Controller.findApp(((Label) node).getText().split(" ")[2]);
					// variable to store the tool tip text
					String eachAppNodeList = "";
					// loop through all nodes to check if it has the current application or not
					for (controller.Node eachNode : Controller.getNodes()) {
						// if application exists in node list
						if (eachNode.getApps().contains(eachApp)) {
							// add the node name
							eachAppNodeList = eachAppNodeList + ", " + eachNode.getId();
						}
					}
					// create tool tip
					Tooltip tooltip = new Tooltip();
					// set tool tip text
					if (eachAppNodeList.length() == 0) {
						tooltip.setText("No nodes linked to this application");
						((Label) node).setTooltip(tooltip);
					} else {
						tooltip.setText(eachAppNodeList);
						((Label) node).setTooltip(tooltip);
					}
				}
			}
		} else { // if no applications exists
			// DO NOTHING
		}
	}

	/**
	 * Function to get random colors every time for every application
	 */
	public static String generateRandomColor() {
		// generate a random number
		Random randomNum = new Random();
		// get 2 random numbers - for R, G, and B
		int r = randomNum.nextInt(255);
		int g = randomNum.nextInt(255);
		int b = randomNum.nextInt(255);
		// format the hex code of the color
		String hex = String.format("#%02x%02x%02x", r, g, b);
		// return the color hex code
		return hex;
	}
}
