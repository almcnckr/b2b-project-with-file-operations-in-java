package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.CompanyManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AdminUpdateCompanyController implements Initializable {

	CompanyManager companyManager = new CompanyManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Button acceptButton;

	@FXML
	private Button backButton;

	@FXML
	private ComboBox<String> companyIdComboBox;

	@FXML
	private TextField companyNameTextField;

	@FXML
	private TextField contactNumberTextField;

	@FXML
	private Button logOutButton;

	@FXML
	private TextField mailTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Label verificationLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		companyIdComboBox.setItems(companyManager.getCompaniesId());
		verificationLabel.setAlignment(Pos.CENTER);
		verificationLabel.setTextFill(Color.RED);
		verificationLabel.setVisible(false);
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
	public void getCompanyInfo() {
		if (companyIdComboBox.getValue() != null) {
			companyNameTextField.setText(companyManager.getCompanyName(companyIdComboBox.getValue()));
			mailTextField.setText(companyManager.getMail(companyIdComboBox.getValue()));
			contactNumberTextField.setText(companyManager.getContactNumber(companyIdComboBox.getValue()));
			passwordTextField.setText(companyManager.getPassword(companyIdComboBox.getValue()));
			verificationLabel.setVisible(false);
		}
	}

	@FXML
	public void updateCompany() {
		if ((companyIdComboBox != null && !companyNameTextField.getText().isBlank()
				&& !mailTextField.getText().isBlank() && !contactNumberTextField.getText().isBlank()
				&& !passwordTextField.getText().isBlank())) {
			if (!companyNameTextField.getText().contains("/") && !mailTextField.getText().contains("/")
					&& !contactNumberTextField.getText().contains("/") && !passwordTextField.getText().contains("/")) {
				if (contactNumberTextField.getText().matches("[0-9]*")
						&& mailTextField.getText().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
								+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
					companyManager.updateCompany(companyIdComboBox.getValue(), companyNameTextField.getText(),
							mailTextField.getText(), contactNumberTextField.getText(), passwordTextField.getText());
					companyIdComboBox.setValue(null);
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
}