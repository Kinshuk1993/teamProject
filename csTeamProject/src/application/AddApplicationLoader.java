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
import java.util.Random;
import controller.Apps;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AddApplicationLoader {

	static VBox appLoaderVBox;

	public AddApplicationLoader(VBox vBoxForAreaNames) throws Exception {
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
		// check before closing the program - COMMENTING OUT AS THIS CLOSES THE WHOLE
		// APPLICATION
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
		// counter variable
		int i = 0;
		// clear contents before writing again
		appLoaderVBox.getChildren().clear();
		// loop through all available applications
		for (Apps eachApp : Controller.getApps()) {
			// get the label for the string ready
			String temp = ++i + ". " + eachApp.getName();
			// create a label
			Label eachAppNameLabel = new Label(temp);
			// set the padding for each label
			eachAppNameLabel.setPadding(new Insets(5, 0, 0, 11));
			// set a random color to the
			eachAppNameLabel.setTextFill(AddApplicationLoader.generateRandomColor());
			// set the font of label to Arial with size 25
			eachAppNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
			// wrap the text in case the application name is long
			eachAppNameLabel.setWrapText(true);
			// add the label to the vertical box
			appLoaderVBox.getChildren().add(eachAppNameLabel);
		}
	}

	/**
	 * Function to get random colors every time for every application
	 */
	public static Paint generateRandomColor() {
		Random randomNum = new Random();
		int r = randomNum.nextInt(255);
		int g = randomNum.nextInt(255);
		int b = randomNum.nextInt(255);
		return Color.rgb(r, g, b);
	}
}
