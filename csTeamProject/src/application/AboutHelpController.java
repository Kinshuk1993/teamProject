/** 
 * University of Glasgow
 * MSc CS, Team Project, fall 2018
 * 
 * Class to handle the functionalities and the working of the
 * about section of the application
 */
/**
 * package declaration
 */
package application;
//import packages
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
public class AboutHelpController {

	@FXML
	VBox vboxHelpText;

	/**
	 * initializer to load the contents for about application help dialog
	 */
	@FXML
	private void initialize() {
		// variable to store each line from of helper text
		String[] line = {"Help \r\n \r\n" , 
				"1. Rules \r\n \r\n" , 
				"	a. A node must be placed within an Area \r\n \r\n" , 
				"	b. An Area must be given a Name \r\n \r\n" , 
				"	c. An Area can be placed within an Area.   \r\n \r\n" , 
				"\r\n" , 
				"2. How to create a Wireless Sensor Network \r\n \r\n" , 
				"	a. To create a Wireless Sensor Network first drag an Area from the left column to the right scene and give the area a name. \r\n \r\n" , 
				"	b. Then drag a new Area or a Node into the area. \r\n \r\n" , 
				"	c. To link Nodes together first, click on a Node, then in the drop-down menu select a Node to you want to link to and click \"Add node\". \r\n \r\n" , 
				"	d. In the bottom section, the new linked node will now show up. When you have added all new linked nodes click \"Create Link\". \r\n \r\n" , 
				"e. When you click on a node, you can change the node configuration by checking/unchecking the different properties' boxes",
				"	e. To add an Application hit the \"Create Application\" button and give it a Name. \r\n \r\n" , 
				"	f. Then if there are nodes available select the nodes you want to deploy the application to from the drop-down menu and click \"Add to app\". \r\n \r\n" , 
				"	g. After adding the App to your selected nodes then click \"Create\".   \r\n \r\n" , 
				"\r\n" , 
				"3. Export \r\n \r\n" , 
				"	a. A Bigraph algebraic expression can be generated by clicking \"Generate the Algebraic Expression\" and shows up in the textbox in the bottom of the application. \r\n \r\n" , 
				"	b. A BIG file can be exported by clicking the \"Export to BIG File\". This will create a \"model.big\" file showing your current Wireless Sensor Network. \r\n \r\n" , 
				"	c. The file will be stored in the same directory as the BigraphUI.jar program.   \r\n \r\n" , 
				"\r\n" , 
				"4. Version specific Notes \r\n \r\n" , 
				"	a. If you drag a Node to the scene it can be removed by clicking its icon, then cancel on the pop-up, and then click delete button in node configuration. \r\n \r\n" ,
				"b. If you drag an Area to the scene, it can be removed by clicking on the area name, \r\n \r\n",
				"	c. If you create a Link, it can be removed by either removing its parent area or either of the node it connects. \r\n \r\n" , 
				"	d. If you add an Application to a node it cannot be removed. \r\n \r\n"};
		//loop through each help text line
		for (int lineCounter=0; lineCounter<line.length;lineCounter++) {
			// create a label
			TextField eachAppNameText = new TextField(line[lineCounter]);
			// set the padding for each label
			eachAppNameText.setPadding(new Insets(5, 0, 0, 11));
			// set font size, weight,
			// background color and opacity: INLINE
			eachAppNameText.setStyle(
					"-fx-font-size: 16px; -fx-font-weight: bold; -fx-opacity: 1; -fx-background-color: transparent;");
			// make the text field disabled
			eachAppNameText.setDisable(true);
			// add the label to the vbox pane
			vboxHelpText.getChildren().add(eachAppNameText);
		}
	}
}
