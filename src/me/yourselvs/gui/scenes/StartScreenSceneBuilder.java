package me.yourselvs.gui.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import me.yourselvs.gui.GUI;
import me.yourselvs.gui.GUIMeta;
import me.yourselvs.itemset.ItemSetManager;

public class StartScreenSceneBuilder {
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
		GridPane grid = new GridPane();
		
		Button backButton = new Button("Exit");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GUI.getStage().close();
			}
		});
		backButton.setPrefHeight(GUIMeta.getBackHeight());
		backButton.setPrefWidth(GUIMeta.getBackWidth());
		grid.add(backButton, 0, 0);
		
		Text text = new Text("Riot games install path: ");
		grid.add(text, 0, 1);
		
		TextField field = new TextField();
		field.setPrefWidth(500);
		grid.add(field, 1, 1);
		
		Button submit = new Button("Submit and view Item Sets");
		submit.setOnAction(new SubmitButtonHandler(field));
		grid.add(submit, 2, 1);
		
		Button items = new Button("View items");
		items.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GUI.getStage().setScene(ItemSelectHeaderSceneBuilder.getScene());
			}
			
		});
		grid.add(items, 0, 2);
		
		return grid;
	}
	
	private static class SubmitButtonHandler implements EventHandler<ActionEvent> {
		
		private TextField field;
		
		public SubmitButtonHandler(TextField field) {
			this.field = field;
		}
		
		@Override
		public void handle(ActionEvent arg0) {
			ItemSetManager manager = new ItemSetManager();
			manager.setPath(field.getText());
			
			try {
				manager.readItemSets();
				GUI.getStage().setScene(ItemSetChampionSceneBuilder.getScene());
			} catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
