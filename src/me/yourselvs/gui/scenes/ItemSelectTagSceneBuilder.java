package me.yourselvs.gui.scenes;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import me.yourselvs.gui.GUI;
import me.yourselvs.gui.GUIMeta;
import net.rithms.riot.api.endpoints.static_data.dto.ItemTree;

public class ItemSelectTagSceneBuilder {
	private static Map<String, Node> nodeMap = new HashMap<String, Node>();
	private static Map<String, Scene> sceneMap = new HashMap<String, Scene>();
	private static Scene lastScene;
	
	public static Node getNode(ItemTree tree) {
		if(!nodeMap.containsKey(tree.getHeader())) {
			nodeMap.put(tree.getHeader(), buildNode(tree));
		}
		
		return nodeMap.get(tree.getHeader());
	}
	
	public static Scene getScene(ItemTree tree) {
		if(!sceneMap.containsKey(tree.getHeader())) {
			sceneMap.put(tree.getHeader(), new Scene((Parent) getNode(tree), GUIMeta.getWidth(), GUIMeta.getHeight()));
		}
		
		lastScene = sceneMap.get(tree.getHeader());
		return lastScene;
	}
	
	public static Scene getLastScene() {
		return lastScene;
	}
	
	private static Node buildNode(ItemTree tree) {
		GridPane grid = new GridPane();
		
		Button backButton = new Button("Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GUI.getStage().setScene(ItemSelectHeaderSceneBuilder.getScene());
			}
		});
		backButton.setPrefHeight(GUIMeta.getButtonHeight());
		backButton.setPrefWidth(GUIMeta.getButtonWidth());
		grid.add(backButton, 0, 0);
		
		int tracker = 1;
		for(String tag : tree.getTags()) {
			if(tag.indexOf("SORTINDEX") != -1)
				continue;
			Button button = new Button(tag);
			button.setOnAction(new TagButtonHandler(tag));
			grid.add(button, 0, tracker);
			tracker++;
		}
		
		return grid;
	}
	
	private static class TagButtonHandler implements EventHandler<ActionEvent> {
		
		private String tag;
		
		public TagButtonHandler(String tag) {
			this.tag = tag;
		}

		@Override
		public void handle(ActionEvent event) {
			GUI.getStage().setScene(ItemSelectItemSceneBuilder.getScene(tag));
		}
	}
}
