package me.yourselvs.gui;

import javafx.application.*;
import javafx.stage.Stage;
import me.yourselvs.gui.scenes.LoadingScreenSceneBuilder;
import me.yourselvs.gui.scenes.StartScreenSceneBuilder;
import me.yourselvs.itemdata.ItemData;

public class GUI extends Application {
	public static void run(String[] args) {
		Application.launch(args);
	}
	
	private static Stage stage;
	private static GUI gui;
	
	public static Stage getStage() {
		return stage;
	}
	
	public static GUI getGUI() {
		return gui;
	}
	
	@Override
	public void start(Stage stage) throws Exception {		
		stage.setTitle("yourselvs' Item Set Builder");
		stage.setScene(LoadingScreenSceneBuilder.getScene());
		stage.setResizable(false);
		stage.show();
		
		ItemData.init();

		stage.setScene(StartScreenSceneBuilder.getScene());
		
		GUI.stage = stage;
	}
}
