/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.geometry.Point2D;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class DragableNode extends AnchorPane{
	public String id;
	public boolean Temperature;
	public boolean Windspeed;
	public boolean Humidity;
	public boolean Vibration;
	public boolean Pressure;
	@FXML public Circle circle;
  
	public DragableNode() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/fxml/DragableNode.fxml")
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
    public void initialize() { }
    public void relocateToPoint(Point2D p){
        Point2D localCoordinates = getParent().sceneToLocal(p);
        relocate( (int) (localCoordinates.getX() - (getBoundsInLocal().getWidth()/2)), (int) (localCoordinates.getY() - (getBoundsInLocal().getHeight()/2)));
    }
  }
