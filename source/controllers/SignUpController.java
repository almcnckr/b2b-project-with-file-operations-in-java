package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import business.ApplicationManager;
import entities.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SignUpController implements Initializable {

	ApplicationManager applicationManager = new ApplicationManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Label verificationLabel;

	@FXML
	private TextField companyNameTextField;

	@FXML
	private TextField contactNumberTextField;

	@FXML
	private Button loginScreenButton;

	@FXML
	private TextField mailTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button signUpButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		verificationLabel.setAlignment(Pos.CENTER);
		verificationLabel.setTextFill(Color.RED);
		verificationLabel.setVisible(false);
	}

	@FXML
	public void signUpAction(ActionEvent event) {
		if (!companyNameTextField.getText().isBlank() && !mailTextField.getText().isBlank()
				&& !contactNumberTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
			if (!companyNameTextField.getText().contains("/") && !mailTextField.getText().contains("/")
					&& !contactNumberTextField.getText().contains("/") && !passwordTextField.getText().contains("/")) {
				if (contactNumberTextField.getText().matches("[0-9]*")
						&& mailTextField.getText().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
								+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
					applicationManager.addApplication(new Company(companyNameTextField.getText(),
							mailTextField.getText(), contactNumberTextField.getText(), passwordTextField.getText()));
					companyNameTextField.setText(null);
					mailTextField.setText(null);
					contactNumberTextField.setText(null);
					passwordTextField.setText(null);
					verificationLabel.setVisible(false);
				} else {
					verificationLabel.setText("Hatalı bilgi formatı!");
					verificationLabel.setVisible(true);
				}
			} else {
				verificationLabel.setText("Alanlarda / karakteri bulunamaz!");
				verificationLabel.setVisible(true);
			}
		} else {
			verificationLabel.setText("Tüm alanların doldurulması zorunludur!");
			verificationLabel.setVisible(true);
		}
	}

	@FXML
	public void loginAction(ActionEvent event) {
		sceneController.getUserScenes(event, "Login");
	}
}