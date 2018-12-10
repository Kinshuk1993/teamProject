package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent; 
 

public class nodeProperty extends BorderPane  {
	
	Boolean wind_speed_value;
	Boolean temperature_value;
	Boolean humidity_value;
	Boolean vibration_value;
	Boolean pressure_value;
	
	
	@FXML BorderPane root_pane;	
	@FXML TextField nodeName;    
    @FXML Button saveButton;    
    @FXML CheckBox windSpeed;    
    @FXML CheckBox temperature;    
    @FXML CheckBox humidity;    
    @FXML CheckBox vibration;    
    @FXML CheckBox pressure;    
//    @FXML CheckBox app1;    
//    @FXML CheckBox app2;
    
    
	 public nodeProperty() {
	      FXMLLoader fxmlLoader = new FXMLLoader(
	          getClass().getResource("/fxml/nodeProperty.fxml")
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
//	  public void getvalue(DragableNode nodeDropped) {
//		  nodeDropped.Windspeed = wind_speed_value ;
//		  nodeDropped.Temperature = temperature_value ;
//		  nodeDropped.Humidity = humidity_value ;
//		  nodeDropped.Vibration = vibration_value ;
//		  nodeDropped.Pressure = pressure_value ;
//		  
//	  }	  
	  @FXML	  
	  private void handleButton1Action(ActionEvent event) {	      
	      if(windSpeed.isSelected()) {
			  wind_speed_value = true;
		  }
		  else wind_speed_value = false;
		  
		  
		  if(temperature.isSelected()) {
			  temperature_value = true;
		  }
		  else temperature_value = false;
		  
		  
		  if(humidity.isSelected()) {
			  humidity_value = true;
		  }
		  else humidity_value = false;
		  
		  if(vibration.isSelected()) {
			  vibration_value = true;
		  }
		  else vibration_value = false;
		  
		  
		  if(pressure.isSelected()) {
			  pressure_value = true;
		  }
		  else pressure_value = false;
       
	  }	  	  	  
}
