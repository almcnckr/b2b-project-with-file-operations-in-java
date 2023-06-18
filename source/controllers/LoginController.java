package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.CompanyManager;
import business.LoginManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController implements Initializable {

	CompanyManager companyManager = new CompanyManager();
	
	LoginManager loginManager = new LoginManager();
	
	SceneController sceneController = new SceneController();

	@FXML
	private TextField companyIdTextField;

	@FXML
	private Button loginButton;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button signUpButton;
	
	@FXML
	private Label verificationLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		verificationLabel.setAlignment(Pos.CENTER);
		verificationLabel.setTextFill(Color.RED);
	}

	@FXML
	public void loginAction(ActionEvent event) {
		if (!companyIdTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
			if (loginManager.authenticateAdmin(companyIdTextField.getText(), passwordField.getText()) == 1) {
				sceneController.getAdminScenes(event,"Admin");
			}else if (loginManager.authenticateCompany(companyIdTextField.getText(), passwordField.getText()) == 1) {
				companyManager.setLoggedCompany(companyIdTextField.getText());
				sceneController.getUserScenes(event, "User");
			}
			else {
				verificationLabel.setText("Hatalı giriş! Lütfen bilgilerinizin doğruluğundan emin olun!");
				verificationLabel.setVisible(true);
			}
		}else {
			verificationLabel.setText("Tüm alanların doldurulması zorunludur!");
			verificationLabel.setVisible(true);
		}
	}

	@FXML
	public void signUpAction(ActionEvent event) {
		sceneController.getUserScenes(event, "SignUp");
	}
}