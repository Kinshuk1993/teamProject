/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Class to handle the opening of the dialog box for creating application - FXML Loader
 * 
 */

package application;


import controller.Apps;
import controller.Controller;
import controller.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class cfgAddAppController {
	
	@FXML
	private ChoiceBox<String> AppChoicebox;
	@FXML
	Button addAppbtn, Finishbtn, CnlBtn, AlertOkBtn;
	@FXML
	Label AppsCurLabel;
	@FXML
	Text noAppAlert, introText;
	
	private Apps TempStoreApp;
	
	@FXML
	private void initialize() {
		System.out.println("Test 5 into initialize");
		
		if(Controller.getNodes() == null || Controller.getNodes().size() == 0 || Controller.getApps().size() == 0) { 
			
			System.out.println("Test 6 into //if no nodes or applications created, Alert!");
			
			//if no nodes or applications created, Alert!
			AppChoicebox.setDisable(true);
			addAppbtn.setDisable(true);
			CnlBtn.setDisable(true);
			Finishbtn.setDisable(true);
			AlertOkBtn.setDisable(false);
			
			AppChoicebox.setVisible(false);
			addAppbtn.setVisible(false);
			CnlBtn.setVisible(false);
			Finishbtn.setVisible(false);
			introText.setVisible(false);
			AppsCurLabel.setVisible(false);
			noAppAlert.setVisible(true);
			AlertOkBtn.setVisible(true);
			
			AlertOkBtn.setOnAction(clickEvent -> {
				closeDialog();
			});
			
		}
		else { // if already has node & application created, show and workable
			
			System.out.println("Test 7 into // if already has node & application created, show and workable");
			
			AppChoicebox.setDisable(false);
			addAppbtn.setDisable(false);
			CnlBtn.setDisable(false);
			Finishbtn.setDisable(false);
			AlertOkBtn.setDisable(true);
			
			AppChoicebox.setVisible(true);
			addAppbtn.setVisible(true);
			CnlBtn.setVisible(true);
			Finishbtn.setVisible(true);
			introText.setVisible(true);
			AppsCurLabel.setVisible(true);
			noAppAlert.setVisible(false);
			AlertOkBtn.setVisible(false);
			
			//set choice items of AppChoicebox (applications which node currently does not have) 
			//set AppsCurLabel of applications which node currently has
			AppChoicebox.getItems().add("Select");
			
			for (Node nodeEach : Controller.getNodes()) {
				 if(nodeEach.getId() == Layout.getCurrentNodeId()) {  //get value from another stage
					 AppsCurLabel.setText(nodeEach.getApplicationsID());
					 int mark = 1;
						for(Apps appEach : Controller.getApps()) {  //all applications
							for(Apps eachApp : nodeEach.getApps()) {  // node's applications
								if(appEach.getId() == eachApp.getId()) {mark = 0;}
							}
							if (mark == 1) { 
								AppChoicebox.getItems().add(appEach.getId() + ":" + appEach.getName());
							}	
						}
					 
				 }//if
			 }//for
			
			
			// select the first item to display in the drop down by default
			AppChoicebox.getSelectionModel().selectFirst();
			String TempStoreInfo = AppChoicebox.getValue();
			String TempStoreID = TempStoreInfo.substring(0, 3);
			System.out.println("TempStoreID" + TempStoreID);
			for(Apps appEach : Controller.getApps()) {
				if(appEach.getId() == TempStoreID){
					TempStoreApp = appEach;
				}
			}
			
			addAppbtn.setOnAction(clickEvent -> {
				if ( !AppChoicebox.getValue().equalsIgnoreCase("select")) {
					for (Node nodeEach : Controller.getNodes()) {
						 if(nodeEach.getId() == Layout.getCurrentNodeId()) {
							 nodeEach.addApp(TempStoreApp);
						 }
					}
					//cfgAddAppLoader.updateNodeAppsList(Layout.getCurrentNodeId());
				}
			});
			
			CnlBtn.setOnAction(clickEvent -> {
				closeDialog();
			});
			
			Finishbtn.setOnAction(clickEvent -> {
				closeDialog();
			});
			
		}//else

			
	}
	
	
	public void closeDialog() {
		Stage stage = (Stage) Finishbtn.getScene().getWindow();
		stage.close();
	}
	

}
