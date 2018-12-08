/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.DataFormat;
import javafx.util.Pair;
public class DragableContainer implements Serializable {

	private static final long serialVersionUID = -1458406119115196098L;
	public static final DataFormat AddNode = new DataFormat("application.DragableNode.add");
	public static final DataFormat AddArea = new DataFormat("application.DraggableArea.add");
	private final List<Pair<String, Object>> mDataPairs = new ArrayList<Pair<String, Object>>();

	public void addData(String key, Object value) {
		mDataPairs.add(new Pair<String, Object>(key, value));
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(String key) {
		for (Pair<String, Object> data : mDataPairs) {
			if (data.getKey().equals(key))
				return (T) data.getValue();
		}
		return null;
	}

	public List<Pair<String, Object>> getData() {
		return mDataPairs;
	}
}
