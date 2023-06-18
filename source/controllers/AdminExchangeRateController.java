package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.ExchangeRate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AdminExchangeRateController implements Initializable {

	SceneController sceneController = new SceneController();

	@FXML
	private Button acceptButton;

	@FXML
	private Button backButton;

	@FXML
	private Button logOutButton;

	@FXML
	private TextField newExchangeRateTextField;

	@FXML
	private Text oldExchangeRateText;

	@FXML
	private Label verificationLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		oldExchangeRateText.setText(ExchangeRate.getExchangeRate());
		verificationLabel.setAlignment(Pos.CENTER);
		verificationLabel.setTextFill(Color.RED);
	}

	@FXML
	public void logOut(ActionEvent event) {
		sceneController.getAdminScenes(event, "Login");
	}

	@FXML
	public void backToMenu(ActionEvent event) {
		sceneController.getAdminScenes(event, "Admin");
	}

	@FXML
	public void updateRate(ActionEvent event) {
		if (!newExchangeRateTextField.getText().isBlank()) {
			if (newExchangeRateTextField.getText().matches("[0-9[.]]*") && newExchangeRateTextField.getText()
					.lastIndexOf(".") == newExchangeRateTextField.getText().indexOf(".")
					&& !newExchangeRateTextField.getText().endsWith(".")) {
				ExchangeRate.setExchangeRate(newExchangeRateTextField.getText());
				oldExchangeRateText.setText(ExchangeRate.getExchangeRate());
				verificationLabel.setVisible(false);
			} else {
				verificationLabel.setText("Alan sadece sayılardan oluşabilir!");
				verificationLabel.setVisible(true);
			}
		} else {
			verificationLabel.setText("Tüm alanların doldurulması zorunludur!");
			verificationLabel.setVisible(true);
		}
	}
}