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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AdminDeleteCompanyController implements Initializable {

	CompanyManager companyManager = new CompanyManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Button acceptButton;

	@FXML
	private Label verificationLabel;

	@FXML
	private Button backButton;

	@FXML
	private ComboBox<String> companyIdComboBox;

	@FXML
	private Text companyNameText;

	@FXML
	private Text contactNumberText;

	@FXML
	private Button logOutButton;

	@FXML
	private Text mailText;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		companyIdComboBox.setItems(companyManager.getCompaniesId());
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
	public void getCompanyInfo() {
		if (companyIdComboBox.getValue() != null) {
			companyNameText.setText(companyManager.getCompanyName(companyIdComboBox.getValue()));
			mailText.setText(companyManager.getMail(companyIdComboBox.getValue()));
			contactNumberText.setText(companyManager.getContactNumber(companyIdComboBox.getValue()));
		}
	}

	@FXML
	public void deleteCompany(ActionEvent event) {
		if (companyIdComboBox.getValue() != null) {
			companyManager.deleteCompany(companyIdComboBox.getValue());
			companyIdComboBox.setItems(companyManager.getCompaniesId());
			companyNameText.setText(null);
			mailText.setText(null);
			contactNumberText.setText(null);
			verificationLabel.setVisible(false);
		} else {
			verificationLabel.setText("Tüm alanların doldurulması zorunludur!");
			verificationLabel.setVisible(true);
		}
	}
}