package application;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
public class DraggableArea extends AnchorPane {
	public String name ;
    @FXML public Rectangle rectangle;
	//    @FXML AnchorPane area;
	public DraggableArea() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DraggableArea.fxml"));
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
	}

	public void relocateToPoint(Point2D p) {
		Point2D localCoordinates = getParent().sceneToLocal(p);
		relocate((int) (localCoordinates.getX() - (getBoundsInLocal().getWidth() / 2)),
				(int) (localCoordinates.getY() - (getBoundsInLocal().getHeight() / 2)));
	}
}