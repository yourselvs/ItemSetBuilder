package me.yourselvs.gui.scenes;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import me.yourselvs.gui.GUI;
import me.yourselvs.gui.GUIMeta;
import me.yourselvs.itemdata.ItemData;
import net.rithms.riot.api.endpoints.static_data.dto.ItemTree;

public class ItemSelectHeaderSceneBuilder {
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
		List<ItemTree> trees = ItemData.getInstance().getItemList().getTree();
		
		GridPane grid = new GridPane();
		
		Button backButton = new Button("Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GUI.getStage().setScene(StartScreenSceneBuilder.getScene());
			}
		});
		backButton.setPrefHeight(GUIMeta.getBackHeight());
		backButton.setPrefWidth(GUIMeta.getBackWidth());
		grid.add(backButton, 0, 0);
		
		int tracker = 2;
		for(ItemTree tree : trees) {
			Button button = new Button(tree.getHeader());
			button.setOnAction(new HeaderButtonHandler(tree));
			grid.add(button, 0, tracker);
			tracker++;
		}
		
		return grid;
	}

	private static class HeaderButtonHandler implements EventHandler<ActionEvent> {
		
		private ItemTree header;
		
		public HeaderButtonHandler(ItemTree header) {
			this.header = header;
		}
		
		@Override
		public void handle(ActionEvent arg0) {
			GUI.getStage().setScene(ItemSelectTagSceneBuilder.getScene(header));
		}
	}
}
