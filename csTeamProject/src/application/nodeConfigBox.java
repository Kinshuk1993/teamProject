package application;
/*
 * this file describe the function of the add button in the left pane of the layout
 * to add a application to the node
 */
import java.io.IOException;
import controller.Apps;
import controller.Controller;
import controller.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
public class nodeConfigBox extends AnchorPane {

	static Label Applist;
	private Apps TempStoreApp;
	String TempStoreInfo; // store the App user chose
	static String appsCurLabel;
	@FXML
	AnchorPane root_pane;
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
	Text noAppAlert, noNodeClickedAlert;
	@FXML
	Button CnlBtn;
	@FXML
	Button AlertOkBtn;

	public nodeConfigBox(Label appList2) {
		Applist = appList2;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/nodeConfigBox.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@FXML
	private void initialize() {
		if (Controller.getNodes() == null || Controller.getNodes().size() == 0 || Controller.getApps().size() == 0) {
			// if no nodes or applications created, Alert!
			AppChoicebox.setDisable(true);
			addAppbtn.setDisable(true);
			CnlBtn.setDisable(true);
			Finishbtn.setDisable(true);
			CnlBtn.setVisible(false);
			Finishbtn.setVisible(false);
			noNodeClickedAlert.setVisible(false);
			noAppAlert.setVisible(true);
			AlertOkBtn.setVisible(true);
			AlertOkBtn.setDisable(false);
			AlertOkBtn.setOnAction(clickEvent -> {
				closeDialog();
			});
		} else if (Layout.getCurrentNodeId() == "Label" || Layout.getCurrentNodeId() == "") {
			// if user didn't choose (click) a node
			AppChoicebox.setDisable(true);
			addAppbtn.setDisable(true);
			CnlBtn.setDisable(true);
			Finishbtn.setDisable(true);
			CnlBtn.setVisible(false);
			Finishbtn.setVisible(false);
			noAppAlert.setVisible(false);
			noNodeClickedAlert.setVisible(true);
			AlertOkBtn.setVisible(true);
			AlertOkBtn.setDisable(false);
			AlertOkBtn.setOnAction(clickEvent -> {
				closeDialog();
			});
		} else {
			// if already has node & application created, show and workable
			AppChoicebox.setDisable(false);
			addAppbtn.setDisable(false);
			CnlBtn.setDisable(false);
			Finishbtn.setDisable(true);
			AppChoicebox.setVisible(true);
			addAppbtn.setVisible(true);
			CnlBtn.setVisible(true);
			Finishbtn.setVisible(true);
			introText.setVisible(true);
			AppsCurLabel.setVisible(true);
			AlertOkBtn.setDisable(true);
			noAppAlert.setVisible(false);
			AlertOkBtn.setVisible(false);
			noNodeClickedAlert.setVisible(false);
			// set choice items of AppChoicebox (applications which the node currently does
			// not have)
			// set AppsCurLabel of applications which node currently has
			AppChoicebox.getItems().add("Select");
			for (Node nodeEach : Controller.getNodes()) {
				if (nodeEach.getId() == Layout.getCurrentNodeId()) { // get value from another stage
					AppsCurLabel.setText(nodeEach.getApplicationsID());
				}
			}
			// add adn delete need to show all the applications
			for (Apps appEach : Controller.getApps()) { // all applications
				AppChoicebox.getItems().add(appEach.getId());
			}
			AppChoicebox.getSelectionModel().selectFirst();
			addAppbtn.setOnAction(clickEvent -> {
				TempStoreInfo = AppChoicebox.getValue(); // get the chosen app
				String TempStoreID = TempStoreInfo;
				for (Node nodeEach : Controller.getNodes()) {
					if (nodeEach.getId() == Layout.getCurrentNodeId()) { // get the node
						AppsCurLabel.setText(nodeEach.getApplicationsID());
						// // node's applications
						// if the application is in applist of node ,remove
						int mark = 0;
						for (Apps eachApp : nodeEach.getApps()) {
							if (eachApp.getId() == TempStoreID) {
								TempStoreApp = eachApp;
								nodeEach.removeApp(TempStoreApp);
								AppsCurLabel.setText(nodeEach.getApplicationsID());
								appsCurLabel = "";
								appsCurLabel = AppsCurLabel.getText();
								Finishbtn.setDisable(false);
								mark = 1;
							} // if
						} // for
						for (Apps AppEACH : Controller.getApps()) {
							if (AppEACH.getId() == TempStoreID && mark == 0) {
								TempStoreApp = AppEACH;
								nodeEach.addApp(TempStoreApp);
								AppsCurLabel.setText(nodeEach.getApplicationsID());
								appsCurLabel = "";
								appsCurLabel = AppsCurLabel.getText();
								Finishbtn.setDisable(false);
							}
						}
					} // if
				} // for
			});
			CnlBtn.setOnAction(clickEvent -> {
				closeDialog();
			});
			Finishbtn.setOnAction(clickEvent -> {
				updateNodeAppsList();
				closeDialog();
			});
		}
	}

	@FXML
	public static void updateNodeAppsList() {
		Applist.setText("");
		Applist.setText(appsCurLabel);
		// update the app list
		AddApplicationLoader.updateApplicationList();
	}

	public void closeDialog() {
		Stage stage = (Stage) Finishbtn.getScene().getWindow();
		stage.close();
	}
}
