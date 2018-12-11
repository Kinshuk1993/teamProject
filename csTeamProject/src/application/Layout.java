/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class Layout extends BorderPane {

	@FXML
	BorderPane border_pane;
	@FXML
	SplitPane base_pane;
	@FXML
	AnchorPane right_pane;
	@FXML
	VBox left_pane;
	@FXML
	Button clearButton;
	@FXML
	Button saveButton;
	@FXML
	Button generateButton;
	@FXML
	Button exportButton;
	@FXML
	TextArea textArea;
	@FXML 
	VBox configPane;
	@FXML
	Button createAppButton;
	@FXML
	VBox vBoxForAreaNames;
	private DragableNode mDragableNodeOver = null;
	private DraggableArea mDragableAreaOver = null;
	private EventHandler<DragEvent> mNodeDragOverRoot = null;
	private EventHandler<DragEvent> mAreaDragOverRoot = null;
	private EventHandler<DragEvent> mNodeDragDropped = null;
	private EventHandler<DragEvent> mAreaDragDropped = null;
	private EventHandler<DragEvent> mNodeDragOverRightPane = null;
	private EventHandler<DragEvent> mAreaDragOverRightPane = null;
	private ArrayList<DraggableArea> AllAreasCreated; // all areas created on the right_pane
	private ArrayList<DraggableArea> TopAreasCreated; // all top-level areas created on the right_pane
	private ArrayList<DraggableArea> InnerAreasCreated; // all inner areas created on the right_pane
	private ArrayList<DragableNode> AllNodesCreated; // all nodes created on the right_pane
	Boolean wind_speed_value;
	Boolean temperature_value;
	Boolean humidity_value;
	Boolean vibration_value;
	Boolean pressure_value;
	// Create new Scene, this will be the new Controller
	Controller newScene = new Controller("Scene");

	public Layout() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL location = getClass().getResource("/fxml/Layout.fxml");
		fxmlLoader.setRoot(this);
		fxmlLoader.setLocation(location);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@FXML
	private void initialize() {
		this.AllAreasCreated = new ArrayList<DraggableArea>();
		this.TopAreasCreated = new ArrayList<DraggableArea>();
		this.InnerAreasCreated = new ArrayList<DraggableArea>();
		this.AllNodesCreated = new ArrayList<DragableNode>();
		clearButton.setOnAction(e -> {
			textArea.setText(null);
		});
		saveButton.setOnAction(e -> {
			// to be fullfilled
		});
		exportButton.setOnAction(e -> {
			// to be fullfilled
		});
		generateButton.setOnAction(e -> {
			// to be fullfilled
		});
		// set the short hand event handler for create application button
		createAppButton.setOnAction(event -> {
			// exception handling
			try {
				// open create a new application dialog
				new AddApplicationLoader(vBoxForAreaNames);
			} catch (Exception error) {
				// print the stack trace in case of error
				error.printStackTrace();
			}
		});
		// Create operator
		AnimatedZoomOperator zoomOperator = new AnimatedZoomOperator();
		// Listen to scroll events
		right_pane.setOnScroll(new EventHandler<ScrollEvent>() {

			@Override
			public void handle(ScrollEvent event) {
				double zoomFactor = 1.5;
				if (event.getDeltaY() <= 0) {
					// zoom out
					zoomFactor = 1 / zoomFactor;
				}
				zoomOperator.zoom(right_pane, zoomFactor, event.getSceneX(), event.getSceneY());
			}
		});
		nodeConfigBox nodeCFGbox = new nodeConfigBox();
    	configPane.getChildren().addAll(nodeCFGbox);
		mDragableNodeOver = new DragableNode();
		mDragableNodeOver.id = "mDragableNodeOver";
		mDragableNodeOver.setVisible(false);
		mDragableNodeOver.setOpacity(0.65);
		getChildren().add(mDragableNodeOver);
		mDragableAreaOver = new DraggableArea();
		mDragableAreaOver.setVisible(false);
		mDragableAreaOver.setOpacity(0.65);
		getChildren().add(mDragableAreaOver);
		Label areaLabel = new Label("Area");
		Label nodeLabel = new Label("Node");
		Label linkLabel = new Label("Link");
		left_pane.getChildren().add(areaLabel);
		DraggableArea area = new DraggableArea();
		addDragDetection(area);
		left_pane.getChildren().add(area);
		left_pane.getChildren().add(nodeLabel);
		DragableNode node = new DragableNode();
		node.id = "icon";
		addDragDetection(node);
		left_pane.getChildren().add(node);
		left_pane.getChildren().add(linkLabel);
		buildDragHandlers();
		buildDragHandlers2();
	}

	// public void saveButtonClick() {
	//
	// }
	//
	// public void exportButtonClick() {
	//
	// }
	//
	// public void generateButtonClick() {
	//
	// }
	// public void clearButtonClick() {
	//
	// }
	private void buildDragHandlers() {
		mNodeDragOverRoot = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Point2D p = right_pane.sceneToLocal(event.getSceneX(), event.getSceneY());
				if (!right_pane.boundsInLocalProperty().get().contains(p)) {
					event.acceptTransferModes(TransferMode.ANY);
					mDragableNodeOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
					return;
				}
				event.consume();
			}
		};
		mNodeDragOverRightPane = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);
				mDragableNodeOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
				event.consume();
			}
		};
		mNodeDragDropped = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				DragableContainer container = (DragableContainer) event.getDragboard()
						.getContent(DragableContainer.AddNode);
				container.addData("scene_coordinates", new Point2D(event.getSceneX(), event.getSceneY()));
				ClipboardContent content = new ClipboardContent();
				content.put(DragableContainer.AddNode, container);
				event.getDragboard().setContent(content);
				event.setDropCompleted(true);
			}
		};
		this.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				right_pane.removeEventHandler(DragEvent.DRAG_OVER, mNodeDragOverRightPane);
				right_pane.removeEventHandler(DragEvent.DRAG_DROPPED, mNodeDragDropped);
				base_pane.removeEventHandler(DragEvent.DRAG_OVER, mNodeDragOverRoot);
				mDragableNodeOver.setVisible(false);
				DragableContainer container = (DragableContainer) event.getDragboard()
						.getContent(DragableContainer.AddNode);
				if (container != null) {
					System.out.println(container.getData().toString());
					if (container.getValue("scene_coordinates") != null) {
						DragableNode nodeDropped = new DragableNode();
//                        nodeDropped.setType(DragableNodeType.valueOf(container.getValue("type")));
						right_pane.getChildren().add(nodeDropped);
						Point2D cursorPoint = container.getValue("scene_coordinates");
						nodeDropped.relocateToPoint(new Point2D(cursorPoint.getX(), cursorPoint.getY()));
						boolean nodeAccept = false;
						for (int i = 0; i < AllAreasCreated.size(); i++) {
							if (nodeDropped.circle.getLayoutX()
									- nodeDropped.circle.getRadius() > AllAreasCreated.get(i).rectangle.getLayoutX()
									&& nodeDropped.circle.getLayoutX()
											+ nodeDropped.circle.getRadius() < AllAreasCreated.get(i).rectangle
													.getLayoutX() + AllAreasCreated.get(i).areaWidth()
									&& nodeDropped.circle.getLayoutY()
											- nodeDropped.circle.getRadius() > AllAreasCreated.get(i).rectangle
													.getLayoutY()
									&& nodeDropped.circle.getLayoutY() + nodeDropped.circle
											.getRadius() < AllAreasCreated.get(i).rectangle.getLayoutY()
													+ AllAreasCreated.get(i).areaHeight()) {
								nodeAccept = true;
								break;
							}
						}
						if (nodeAccept) {
							try {
								Stage nodeWindow = new Stage();
								BorderPane Pane = new BorderPane();
								Scene nodeScene = new Scene(Pane, 400, 250);
								nodeWindow.setTitle("node Settings");
								nodeWindow.setScene(nodeScene);
								nodeWindow.show();
								nodeProperty node_setting = new nodeProperty();
								Pane.setCenter(node_setting);
								node_setting.saveButton.setOnAction(e -> {
									if (node_setting.windSpeed.isSelected()) {
										wind_speed_value = true;
									} else
										wind_speed_value = false;
									if (node_setting.temperature.isSelected()) {
										temperature_value = true;
									} else
										temperature_value = false;
									if (node_setting.humidity.isSelected()) {
										humidity_value = true;
									} else
										humidity_value = false;
									if (node_setting.vibration.isSelected()) {
										vibration_value = true;
									} else
										vibration_value = false;
									if (node_setting.pressure.isSelected()) {
										pressure_value = true;
									} else
										pressure_value = false;
									System.out.println(temperature_value + " " + wind_speed_value + " " + humidity_value
											+ " " + vibration_value + " " + pressure_value);
									AllNodesCreated.add(nodeDropped);
									boolean findParent = false;
									for (int i = 0; i < InnerAreasCreated.size(); i++) {
										if (nodeDropped.circle.getLayoutX()
												- nodeDropped.circle.getRadius() > InnerAreasCreated.get(i).rectangle
														.getLayoutX()
												&& nodeDropped.circle.getLayoutX() + nodeDropped.circle
														.getRadius() < InnerAreasCreated.get(i).rectangle.getLayoutX()
																+ InnerAreasCreated.get(i).areaWidth()
												&& nodeDropped.circle.getLayoutY() - nodeDropped.circle
														.getRadius() > InnerAreasCreated.get(i).rectangle.getLayoutY()
												&& nodeDropped.circle.getLayoutY() + nodeDropped.circle
														.getRadius() < InnerAreasCreated.get(i).rectangle.getLayoutY()
																+ InnerAreasCreated.get(i).areaHeight()) {
											nodeDropped.id = newScene.addNodeToArea(InnerAreasCreated.get(i).name,
													temperature_value, wind_speed_value, humidity_value,
													vibration_value, pressure_value);
											System.out.println("node created: " + nodeDropped.id);
											findParent = true;
											System.out.println("Parent area :" + InnerAreasCreated.get(i).name);
											break;
										}
									}
									if (!findParent) {
										for (int i = 0; i < TopAreasCreated.size(); i++) {
											if (nodeDropped.circle.getLayoutX()
													- nodeDropped.circle.getRadius() > TopAreasCreated.get(i).rectangle
															.getLayoutX()
													&& nodeDropped.circle.getLayoutX() + nodeDropped.circle
															.getRadius() < TopAreasCreated.get(i).rectangle.getLayoutX()
																	+ TopAreasCreated.get(i).areaWidth()
													&& nodeDropped.circle.getLayoutY() - nodeDropped.circle
															.getRadius() > TopAreasCreated.get(i).rectangle.getLayoutY()
													&& nodeDropped.circle.getLayoutY() + nodeDropped.circle
															.getRadius() < TopAreasCreated.get(i).rectangle.getLayoutY()
																	+ TopAreasCreated.get(i).areaHeight()) {
												nodeDropped.id = newScene.addNodeToArea(TopAreasCreated.get(i).name,
														temperature_value, wind_speed_value, humidity_value,
														vibration_value, pressure_value);
												System.out.println("node created: " + nodeDropped.id);
												findParent = true;
												System.out.println("Parent area :" + TopAreasCreated.get(i).name);
												break;
											}
										}
									}
									nodeWindow.close();
								});
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Stage nodePositionErrorBox = new Stage();
							BorderPane errorPane = new BorderPane();
							Scene nodePositionErrorScene = new Scene(errorPane, 300, 100);
							nodePositionErrorBox.setTitle("Node Position Error ");
							nodePositionErrorBox.setScene(nodePositionErrorScene);
							nodePositionErrorBox.show();
							nodePositionError node_position_error = new nodePositionError();
							errorPane.setCenter(node_position_error);
							right_pane.getChildren().remove(nodeDropped);
						}
					}
				}
				right_pane.removeEventHandler(DragEvent.DRAG_OVER, mAreaDragOverRightPane);
				right_pane.removeEventHandler(DragEvent.DRAG_DROPPED, mAreaDragDropped);
				base_pane.removeEventHandler(DragEvent.DRAG_OVER, mAreaDragOverRoot);
				mDragableAreaOver.setVisible(false);
				DragableContainer2 container2 = (DragableContainer2) event.getDragboard()
						.getContent(DragableContainer2.AddArea);
				if (container2 != null) {
					System.out.println(container2.getData().toString());
					if (container2.getValue("scene_coordinates") != null) {
						DraggableArea areaDropped = new DraggableArea();
//                        areaDropped.setType(DragableNodeType.valueOf(container.getValue("type")));
						right_pane.getChildren().add(areaDropped);
						Point2D cursorPoint = container2.getValue("scene_coordinates");
						areaDropped.relocateToPoint(new Point2D(cursorPoint.getX(), cursorPoint.getY()));
						try {
							Stage areaWindow = new Stage();
							BorderPane Pane = new BorderPane();
							Scene areaScene = new Scene(Pane, 250, 100);
							areaWindow.setTitle("area Settings");
							areaWindow.setScene(areaScene);
							areaWindow.show();
							areaSetting area_setting = new areaSetting();
							Pane.setCenter(area_setting);
							DragResizeMod.makeResizable(areaDropped.rectangle, null);
							area_setting.saveButton.setOnAction(e -> {
								areaDropped.name = area_setting.areaName.getText();
								AllAreasCreated.add(areaDropped);
								boolean isInnerarea = false;
								boolean findParent = false;
//                    			System.out.println(areaDropped.getLayoutX());
//                				System.out.println(areaDropped.getLayoutY());
								for (int i = 0; i < InnerAreasCreated.size(); i++) {
									if (areaDropped.getLayoutX() > InnerAreasCreated.get(i).rectangle.getLayoutX()
											&& areaDropped.getLayoutX() + areaDropped
													.areaWidth() < InnerAreasCreated.get(i).rectangle.getLayoutX()
															+ InnerAreasCreated.get(i).areaWidth()
											&& areaDropped.getLayoutY() > InnerAreasCreated.get(i).rectangle
													.getLayoutY()
											&& areaDropped.getLayoutY() + areaDropped
													.areaHeight() < InnerAreasCreated.get(i).rectangle.getLayoutY()
															+ InnerAreasCreated.get(i).areaHeight()) {
										InnerAreasCreated.add(areaDropped);
										newScene.addInnerArea(InnerAreasCreated.get(i).name, areaDropped.name);
										System.out.println("inner area created: " + areaDropped.name);
										isInnerarea = true;
										findParent = true;
										System.out.println("Parent area :" + InnerAreasCreated.get(i).name);
										break;
									}
								}
								if (!findParent) {
									for (int i = 0; i < TopAreasCreated.size(); i++) {
										if (areaDropped.getLayoutX() > TopAreasCreated.get(i).rectangle.getLayoutX()
												&& areaDropped.getLayoutX() + areaDropped
														.areaWidth() < TopAreasCreated.get(i).rectangle.getLayoutX()
																+ TopAreasCreated.get(i).areaWidth()
												&& areaDropped.getLayoutY() > TopAreasCreated.get(i).rectangle
														.getLayoutY()
												&& areaDropped.getLayoutY() + areaDropped
														.areaHeight() < TopAreasCreated.get(i).rectangle.getLayoutY()
																+ TopAreasCreated.get(i).areaHeight()) {
											InnerAreasCreated.add(areaDropped);
											newScene.addInnerArea(AllAreasCreated.get(i).name, areaDropped.name);
											System.out.println("inner area created : " + areaDropped.name);
											isInnerarea = true;
											findParent = true;
											System.out.println("Parent area :" + TopAreasCreated.get(i).name);
											break;
										}
									}
								}
								if (!isInnerarea) {
									TopAreasCreated.add(areaDropped);
									newScene.addTopArea(areaDropped.name);
									System.out.println("top level area created: " + areaDropped.name);
								}
								areaWindow.close();
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				event.consume();
			}
		});
	}

	private void buildDragHandlers2() {
		mAreaDragOverRoot = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Point2D p = right_pane.sceneToLocal(event.getSceneX(), event.getSceneY());
				if (!right_pane.boundsInLocalProperty().get().contains(p)) {
					event.acceptTransferModes(TransferMode.ANY);
					mDragableAreaOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
					return;
				}
				event.consume();
			}
		};
		mAreaDragOverRightPane = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);
				mDragableAreaOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
				event.consume();
			}
		};
		mAreaDragDropped = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				DragableContainer2 container2 = (DragableContainer2) event.getDragboard()
						.getContent(DragableContainer2.AddArea);
				container2.addData("scene_coordinates", new Point2D(event.getSceneX(), event.getSceneY()));
				ClipboardContent content2 = new ClipboardContent();
				content2.put(DragableContainer2.AddArea, container2);
				event.getDragboard().setContent(content2);
				event.setDropCompleted(true);
			}
		};
	}

	private void addDragDetection(DragableNode dragableNode) {
		dragableNode.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				base_pane.setOnDragOver(mNodeDragOverRoot);
				right_pane.setOnDragOver(mNodeDragOverRightPane);
				right_pane.setOnDragDropped(mNodeDragDropped);
				@SuppressWarnings("unused")
				DragableNode node = (DragableNode) event.getSource();
				mDragableNodeOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
				ClipboardContent content = new ClipboardContent();
				DragableContainer container = new DragableContainer();
				content.put(DragableContainer.AddNode, container);
				mDragableNodeOver.startDragAndDrop(TransferMode.ANY).setContent(content);
				mDragableNodeOver.setVisible(true);
				mDragableNodeOver.setMouseTransparent(true);
				event.consume();
			}
		});
	}

	private void addDragDetection(DraggableArea draggableArea) {
		draggableArea.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				base_pane.setOnDragOver(mAreaDragOverRoot);
				right_pane.setOnDragOver(mAreaDragOverRightPane);
				right_pane.setOnDragDropped(mAreaDragDropped);
				// get ref to clicked node
				@SuppressWarnings("unused")
				DraggableArea area = (DraggableArea) event.getSource();
				// drag baby
//                mDragableNodeOver.setType(area.getType());
				mDragableAreaOver.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
				ClipboardContent content2 = new ClipboardContent();
				DragableContainer2 container2 = new DragableContainer2();
//                container.addData("type", mDragableNodeOver.getType().toString());
				content2.put(DragableContainer2.AddArea, container2);
				mDragableAreaOver.startDragAndDrop(TransferMode.ANY).setContent(content2);
				mDragableAreaOver.setVisible(true);
				mDragableAreaOver.setMouseTransparent(true);
				event.consume();
			}
		});
	}
}
