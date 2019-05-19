package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AppController11 implements Initializable {
	@FXML Label label1;
	@FXML ComboBox<String> combo1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combo1.getSelectionModel().selectedItemProperty().
			addListener((ObservableValue<? extends String> observ, String oldVal, String newVal) -> {
				label1.setText(oldVal+"→"+newVal);
			});
		
	}
}
