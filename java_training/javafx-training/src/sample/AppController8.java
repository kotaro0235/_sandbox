package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AppController8 implements Initializable {
	@FXML Label label1;
	@FXML TextField field1;
	@FXML Button btn1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn1.setOnAction((ActionEvent) -> {
			String str = field1.getText();
			str = "あなたは「" + str + "」と書いた。";
			label1.setText(str);
		});
		
	}
}
