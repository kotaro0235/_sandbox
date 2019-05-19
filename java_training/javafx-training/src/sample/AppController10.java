package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class AppController10 implements Initializable {
	@FXML Label label1;
	@FXML ToggleGroup group1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		group1.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
			observ, Toggle oldVal, Toggle newVal) -> {
			String oldStr = (String)oldVal.getUserData();
			String newStr = (String)newVal.getUserData();
			label1.setText(oldStr + "→" + newStr);
		});
		
	}
}
