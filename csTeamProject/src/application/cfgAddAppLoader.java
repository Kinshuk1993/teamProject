/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Class to handle the opening of the dialog box for creating application - FXML Loader
 * 
 */

package application;

import java.io.IOException;
import java.net.URL;
import controller.Apps;
import controller.Controller;
import controller.Node;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;



public class cfgAddAppLoader extends AnchorPane {
	
	static Label Applist;
	private Apps TempStoreApp;
	
    @FXML
     AnchorPane AddApptoNode_basePane;

    @FXML
	 ChoiceBox<String> AppChoicebox;

    @FXML
     Button addAppbtn;

    @FXML
     Button Finishbtn;

    @FXML
     Label AppsCurLabel;

    @FXML
     Text introText;

    @FXML
     Text noAppAlert;

    @FXML
     Button CnlBtn;

    @FXML
     Button AlertOkBtn;
    
    

	public cfgAddAppLoader(Label appList2) throws Exception {
		
		Applist = appList2;
		FXMLLoader fxmlLoader = new FXMLLoader(
		   getClass().getResource("fxml/cfgAddApp.fxml")
		   );
		URL location = getClass().getResource("fxml/cfgAddApp.fxml");
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setLocation(location);
		System.out.println("test 1");
		try {
			System.out.println("test 2");
			fxmlLoader.load();
			System.out.println("test 2.5");
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
    

//	public cfgAddAppLoader(Label appList2) throws Exception {
//		
//		Applist = appList2;
//		
//		try {
//			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cfgAddApp.fxml"));
//			Parent root1 = (Parent) fxmlLoader.load();
//			Stage stage = new Stage();
//			stage.setScene(new Scene(root1));  
//			stage.show();
//		}catch(Exception e) {
//			System.out.println("cannot load the file");
//		}
		
//		Stage cfgAddApp_Stage = new Stage();
//		cfgAddApp_Stage.setTitle("Add Application for Node");
//		FXMLLoader fxmlLoader = new FXMLLoader();
//		fxmlLoader.getClass().getResource("/fxml/cfgAddApp.fxml"); 
//		fxmlLoader.setLocation(getClass().getResource("/fxml/cfgAddApp.fxml"));
//		System.out.println("test 1");
//		try {
////			fxmlLoader.load();
//			Parent cfgAddApp_Parent = (Parent) fxmlLoader.load();
//			System.out.println("test 2");
//			cfgAddApp_Stage.setScene(new Scene(cfgAddApp_Parent));
//		} catch (IOException exception) {
//			throw new RuntimeException(exception);
//		}
//		
//		cfgAddApp_Stage.show();
		
		
//	try {	
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cfgAddApp.fxml"));
//		System.out.println("test 1");
//		Parent cfgAddApp_Parent = (Parent) fxmlLoader.load();
//		System.out.println("test 2");
//		Stage cfgAddApp_Stage = new Stage();
//		cfgAddApp_Stage.setTitle("Add Application for Node");
//		cfgAddApp_Stage.setScene(new Scene(cfgAddApp_Parent));
//		cfgAddApp_Stage.show();
//	}catch(Exception e) {
//		System.out.println("cannot load");
//	}
//		
		
		
		//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cfgAddApp.fxml"));
//		cfgAddApp_Stage.getIcons().add(new Image(getClass().getResource("/resources/create-app-icon.png").toExternalForm()));
		// block using of any other window of current application
//		cfgAddApp_Stage.initModality(Modality.APPLICATION_MODAL);
		// remove minimize and maximize button
//		cfgAddApp_Stage.resizableProperty().setValue(Boolean.FALSE);
		
		
//		URL location = getClass().getResource("/fxml/Layout.fxml");
//		fxmlLoader.setRoot(this);
//		fxmlLoader.setLocation(location);
		
	
	
	@FXML
	private void initialize() {
		System.out.println("Test 3 into initialize");
		
		if(Controller.getNodes() == null || Controller.getNodes().size() == 0 || Controller.getApps().size() == 0) { 
			
			System.out.println("Test 4 into //if no nodes or applications created, Alert!");
			
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
	
	
	public static void updateNodeAppsList(String NodeID) {
		
		for (Node nodeEach : Controller.getNodes())  {
			if(nodeEach.getId() == NodeID) {
				 //SetNodeAppList(nodeEach.getApplicationsID());
			}
		}
	}
	

	public void closeDialog() {
		Stage stage = (Stage) Finishbtn.getScene().getWindow();
		stage.close();
	}
	

	

}
