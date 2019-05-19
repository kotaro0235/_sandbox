package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class AppController12 implements Initializable {
	@FXML Label label1;
	@FXML Slider slider1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		slider1.valueProperty().addListener((ObservableValue<? extends Number> observ, Number oldVal, Number newVal)->{
			double oldnum = oldVal.doubleValue();
			double newnum = newVal.doubleValue();
			label1.setText(oldnum + "→" + newnum);
		});
		
	}
}
