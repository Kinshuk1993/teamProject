package application;

import java.io.IOException;
import controller.Controller;
import controller.Area;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
public class areaSetting  extends BorderPane {
	String name;
	@FXML BorderPane root_pane;
	@FXML Button saveButton;
	@FXML TextField areaName;
	public areaSetting() {
	      FXMLLoader fxmlLoader = new FXMLLoader(
	          getClass().getResource("/fxml/areaSetting.fxml")
	      );
	      fxmlLoader.setRoot(this);
	      fxmlLoader.setController(this);     

	      try {	    	  	    	  
	          fxmlLoader.load();
	      } catch (IOException exception) {
	          throw new RuntimeException(exception);
	      }
	  }
	  
	  @FXML	  
	  public void initialize() {		  
	  }
}
