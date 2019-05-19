package sample;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App_13 extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("app_13.fxml"));
			Scene scene = new Scene(root,300,300);
			stage.setScene(scene);
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
