
package me.yourselvs.gui.scenes;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import me.yourselvs.gui.GUI;
import me.yourselvs.gui.GUIMeta;
import me.yourselvs.itemdata.ItemData;
import net.rithms.riot.api.endpoints.static_data.dto.Item;

public class ItemSelectItemSceneBuilder {
	private static Map<String, Node> nodeMap = new HashMap<String, Node>();
	private static Map<String, Scene> sceneMap = new HashMap<String, Scene>();
	
	public static Node getNode(String tag) {
		if(!nodeMap.containsKey(tag)) {
			nodeMap.put(tag, buildNode(tag));
		}
		
		return nodeMap.get(tag);
	}

	public static Scene getScene(String tag) {
		if(!sceneMap.containsKey(tag)){
			sceneMap.put(tag, new Scene((Parent) getNode(tag), GUIMeta.getWidth(), GUIMeta.getHeight()));
		}
		
		return sceneMap.get(tag);
	}
	
	private static Node buildNode(String tag) {
		GridPane grid = new GridPane();
		
		Button backButton = new Button("Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GUI.getStage().setScene(ItemSelectTagSceneBuilder.getLastScene());
			}
		});
		
		backButton.setPrefHeight(GUIMeta.getButtonHeight());
		backButton.setPrefWidth(GUIMeta.getButtonWidth());
		grid.add(backButton, 0, 0);
		
		TextArea display = new TextArea();
		display.setEditable(false);
		display.setPrefHeight(GUIMeta.getHeight() - GUIMeta.getButtonHeight());
		display.setPrefWidth(GUIMeta.getWidth() / 2);
		display.setWrapText(true);
		grid.add(display, 1, 1);
		
		Map<String, Item> itemData = ItemData.getInstance().getItemList().getData();
		Map<Integer, Item> itemTagData = new TreeMap<Integer, Item>();
		
		for(String itemName : itemData.keySet()) {
			Item item = itemData.get(itemName);
			
			if(item.getTags() == null) {
				continue;
			}
			
			for(String itemTag : item.getTags()) {
				if(itemTag.equalsIgnoreCase(tag)) {
					itemTagData.put(item.getGold().getTotal(), item);
				}
			}
		}
		
		GridPane buttonGrid = new GridPane();
		
		int tracker = 0;
		for(Integer gold : itemTagData.keySet()) {
			Button button = new Button(itemTagData.get(gold).getName());
			button.setOnAction(new ItemButtonHandler(display, itemTagData.get(gold)));
			buttonGrid.add(button, 0, tracker);
			tracker++;
		}
		
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(buttonGrid);
		scroll.setPrefHeight(GUIMeta.getHeight() - GUIMeta.getButtonHeight());
		scroll.setPrefWidth(GUIMeta.getWidth() / 2);
		grid.add(scroll, 0, 1);
		
		return grid;
	}
	
	private static class ItemButtonHandler implements EventHandler<ActionEvent> {
		
		private TextArea field;
		private Item item;
		
		public ItemButtonHandler(TextArea field, Item item) {
			this.field = field;
			this.item = item;
		}
		
		@Override
		public void handle(ActionEvent e) {
			field.setText("");
			field.appendText("Name: " + item.getName() + "\n");
			field.appendText("Id: " + item.getId() + "\n");
			field.appendText("Cost: " + item.getGold().getTotal() + "\n");
			field.appendText("Description: " + "\n" + item.getSanitizedDescription());
		}
	}
}
