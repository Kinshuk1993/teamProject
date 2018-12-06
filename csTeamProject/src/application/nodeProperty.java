package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application; 
 

public class nodeProperty extends BorderPane {
			
	@FXML BorderPane root_pane;
	
    @FXML Button savePropertyButton;

    @FXML Button saveAppButton;
    
	 public nodeProperty() {

	      FXMLLoader fxmlLoader = new FXMLLoader(

	          getClass().getResource("nodeProperty.fxml")

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
	  
	  private void initialize() {
		  
		  
		  savePropertyButton.setOnAction(e -> {


	      });
		  
		  saveAppButton.setOnAction(e -> {

	         
	      });
	  }
}
