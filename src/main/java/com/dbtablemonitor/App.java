package com.dbtablemonitor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label l = new Label("");
		Scene s = new Scene(new StackPane(l), 640, 480);
		primaryStage.setScene(s);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch();
    }

}
