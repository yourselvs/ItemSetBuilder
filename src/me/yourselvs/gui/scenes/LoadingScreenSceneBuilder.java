package me.yourselvs.gui.scenes;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import me.yourselvs.gui.GUIMeta;

public class LoadingScreenSceneBuilder {
	private static Node node;
	private static Scene scene;
	
	public static Node getNode() {
		if(node == null)
			node = buildNode();
		
		return node;
	}
	
	public static Scene getScene() {
		if(scene == null) {
			scene = new Scene((Parent) getNode(), GUIMeta.getWidth(), GUIMeta.getHeight());
		}
		
		return scene;
	}
	
	private static Node buildNode() {
		FlowPane pane = new FlowPane();
		pane.getChildren().add(new Text("Loading yourselvs' Item Set Builder..."));
		return pane;
	}
}
