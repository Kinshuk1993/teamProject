/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.geometry.Bounds;
public class DragableNode extends AnchorPane {
	public String id;
//	public String typeId; 
//	public String Mac;    //Zhang
//	public String ipv6; 
//	public boolean Temperature;
//	public boolean Windspeed;
//	public boolean Humidity;
//	public boolean Vibration;
//	public boolean Pressure;
	@FXML
	public Circle circle;
	@FXML
	public Label label;
	@SuppressWarnings("unused")
	private EventHandler<MouseDragEvent> mNodePressed = null;

	public DragableNode() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DragableNode.fxml"));
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
		MousePressedHandler();
	}

	public void relocateToPoint(Point2D p) {
		Point2D localCoordinates = getParent().sceneToLocal(p);
		relocate((int) (localCoordinates.getX() - (getBoundsInLocal().getWidth() / 2)),
				(int) (localCoordinates.getY() - (getBoundsInLocal().getHeight() / 2)));
	}

	private void MousePressedHandler() {
		mNodePressed = new EventHandler<MouseDragEvent>() {

			@Override
			public void handle(MouseDragEvent event) {
			}
		};
	}

	/**
	 * Function to handle the click event of the node
	 */
	public void nodeClicked() {
		// handle exception
		try {
			// load the link Node FXML passing the current id as parameter
			new LinkNodeLoader(id);
		} catch (Exception e) { // handle any error/exception
			// print the error / exception in console
			e.printStackTrace();
		}
	}
	
	 //get node position area //ZHANG
	public int NodeAreaXmin() {
		Bounds boundsInScene = circle.localToScene(circle.getBoundsInLocal());
	    int x = (int) boundsInScene.getMinX();
	    return x;       
	}	
	public int NodeAreaXmax() {
		Bounds boundsInScene = circle.localToScene(circle.getBoundsInLocal());
	    int x = (int) boundsInScene.getMaxX();
	    return x;       
	}
	public int NodeAreaYmin() {
		Bounds boundsInScene = circle.localToScene(circle.getBoundsInLocal());
	    int y = (int) boundsInScene.getMinY();
	    return y;
	}
	public int NodeAreaYmax() {
		Bounds boundsInScene = circle.localToScene(circle.getBoundsInLocal());
	    int y = (int) boundsInScene.getMaxY();
	    return y;
	}
	
	
	
}
