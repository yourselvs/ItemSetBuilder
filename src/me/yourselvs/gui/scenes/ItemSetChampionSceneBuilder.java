package me.yourselvs.gui.scenes;

import java.util.HashMap;
import java.util.Map;

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
import me.yourselvs.itemset.ItemSet;
import me.yourselvs.itemset.ItemSetBuilder;
import me.yourselvs.itemset.ItemSetManager;

public class ItemSetChampionSceneBuilder {
	private static Node node;
	private static Scene scene;
	
	private static Map<String, GridPane> itemSetGrids = new HashMap<String, GridPane>();
	private static Map<Integer, TextArea> itemSets = new HashMap<Integer, TextArea>();
	
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
	
	public static Node buildNode() {
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
		
		GridPane championGrid = new GridPane();
		ScrollPane championScroll = new ScrollPane();
		ScrollPane setListScroll = new ScrollPane();
		ScrollPane itemSetScroll = new ScrollPane();
		
		int tracker = 0;
		for(String champion : ItemSetManager.getItemSets().keySet()) {
			Button button = new Button(champion);
			button.setOnAction(new ChampionButtonHandler(champion, setListScroll, itemSetScroll));
			championGrid.add(button, 0, tracker);
			tracker++;
		}
		
		
		championScroll.setContent(championGrid);
		championScroll.setPrefHeight(GUIMeta.getHeight() - GUIMeta.getBackHeight());
		championScroll.setPrefWidth(GUIMeta.getWidth() / 4);
		grid.add(championScroll, 0, 1);
		
		setListScroll.setPrefHeight(GUIMeta.getHeight() - GUIMeta.getBackHeight());
		setListScroll.setPrefWidth(GUIMeta.getWidth() / 4);
		grid.add(setListScroll, 1, 1);
		
		itemSetScroll.setPrefHeight(GUIMeta.getHeight() - GUIMeta.getBackHeight());
		itemSetScroll.setPrefWidth(GUIMeta.getWidth() / 2);
		grid.add(itemSetScroll, 2, 1);
		
		return grid;
	}
	
	private static class ChampionButtonHandler implements EventHandler<ActionEvent> {
		
		private String champion;
		private ScrollPane scroll;
		private ScrollPane itemSetScroll;
		
		public ChampionButtonHandler(String champion, ScrollPane scroll, ScrollPane itemSetScroll) {
			this.champion = champion;
			this.scroll = scroll;
			this.itemSetScroll = itemSetScroll;
		}
		
		@Override
		public void handle(ActionEvent event) {
			if(!itemSetGrids.containsKey(champion)) {
				GridPane grid = new GridPane();
				GridPane itemSetGrid = new GridPane();
				
				int tracker = 0;
				for(ItemSet set : ItemSetManager.getItemSets().get(champion)) {
					Button button = new Button(set.getTitle());
					button.setOnAction(new ItemSetButtonHandler(set, itemSetScroll));
					itemSetGrid.add(button, 0, tracker);
					tracker++;
				}
				
				Button button = new Button("New");
				button.setPrefHeight(GUIMeta.getBackHeight());
				button.setPrefWidth(GUIMeta.getBackWidth());
				button.setOnAction(new NewSetButtonHandler());
				
				grid.add(button, 0, 0);
				grid.add(itemSetGrid, 0, 1);
				
				itemSetGrids.put(champion, grid);
			}
			
			scroll.setContent(itemSetGrids.get(champion));
		}
		
	}
	
	private static class ItemSetButtonHandler implements EventHandler<ActionEvent> {

		private ItemSet itemSet;
		private ScrollPane scroll;
		
		public ItemSetButtonHandler(ItemSet itemSet, ScrollPane scroll) {
			this.itemSet = itemSet;
			this.scroll = scroll;
		}
		@Override
		public void handle(ActionEvent event) {
			if(!itemSets.containsKey(itemSet.hashCode())) {
				TextArea text = new TextArea();
				
				text.setText(ItemSetBuilder.buildItemSetMeta(itemSet));
				
				text.setPrefWidth(GUIMeta.getWidth() / 2 - 2);
				text.setPrefHeight(GUIMeta.getHeight() - GUIMeta.getBackHeight() - 2);
				
				itemSets.put(itemSet.hashCode(), text);
			}
			
			scroll.setContent(itemSets.get(itemSet.hashCode()));
		}
		
	}
	
	private static class NewSetButtonHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			
		}
	}
}
