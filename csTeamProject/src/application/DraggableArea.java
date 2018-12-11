package application;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
public class DraggableArea extends Rectangle {
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
//	public int areaCoordinateX() {
//		Bounds bounds= rectangle.getBoundsInLocal();
//        int x = (int) bounds.getMinX();
//        return x;       
//	}
//	public int areaCoordinateY() {
//		Bounds bounds= rectangle.getBoundsInLocal();
//        int y = (int) bounds.getMinY();
//        return y;
//	}
	public int areaWidth() {
		Bounds bounds= rectangle.getBoundsInLocal();
		int width = (int) bounds.getWidth();
        return width;
	}
	public int areaHeight() {
		Bounds bounds= rectangle.getBoundsInLocal();
		 int height = (int) bounds.getHeight();
        return height;
	}
}