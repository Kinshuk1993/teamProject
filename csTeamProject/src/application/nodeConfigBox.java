package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application; 
import javafx.scene.Parent;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent; 
import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import controller.Node;

public class nodeConfigBox extends AnchorPane {
			
    @FXML AnchorPane root_pane;	
    @FXML CheckBox Sensor_windspeed;
    @FXML CheckBox Sensor_temperature;
    @FXML CheckBox Sensor_humidity;
    @FXML CheckBox Sensor_virbration;
    @FXML CheckBox Sensor_pressure;
    @FXML Text MACID;
    @FXML Text IPV6ID;

    
	Boolean ifwindspeed;
	Boolean iftemperature;
	Boolean ifhumidity;
	Boolean ifvirbration;
	Boolean ifpressure;

  
 
    public nodeConfigBox() {
    	FXMLLoader fxmlLoader = new FXMLLoader(
    			getClass().getResource("/fxml/nodeConfigBox.fxml")
    			);
    	fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);
	    
	    try {
	    	fxmlLoader.load();
	    	} 
	    catch (IOException exception) {
	    	throw new RuntimeException(exception);
	    	}
	    }
       
    @FXML
    private void initialize() { 
    	
//    	Sensor_windspeed.setOnAction(e -> {	
//    		System.out.println("WindspeedID test action!");
//    	});    	

    }
    
    @FXML
    private void SensorCheckEvent(ActionEvent event) {
    	
    	//Node node = new Node();
    	//node = ;

    	  	
    	//--record again--
    	if (Sensor_windspeed.isSelected()) {
    		ifwindspeed = true;
    		System.out.println("ActionEvent windSpeed is selected! ");
    	}else {ifwindspeed = false;}
    	if (Sensor_temperature.isSelected()) {
    		iftemperature = true;
    		System.out.println("ActionEvent temp is selected!");
    		}else {ifwindspeed = false;}
    	if (Sensor_humidity.isSelected()) {
    		ifhumidity = true;
    		System.out.println("ActionEvent humidity is selected!");
    	}else {ifhumidity = false;}	
    	if (Sensor_virbration.isSelected()) {
    		System.out.println("ActionEvent virbration is selected!");
    	}else {ifvirbration = false;}
    	if (Sensor_pressure.isSelected()) {
    		System.out.println("ActionEvent pressure is selected!");
    	}else {ifpressure = false;}
    	
//    	node.setAllConf(iftemperature, ifwindspeed, ifhumidity, ifvirbration, ifpressure);
//    	MACID.setText(node.getMac());
//    	IPV6ID.setText(node.getIP());
    	
    }


}
    
