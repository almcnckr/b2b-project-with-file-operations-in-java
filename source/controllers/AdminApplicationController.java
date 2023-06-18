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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class AdminApplicationController implements Initializable {

	ApplicationManager applicationManager = new ApplicationManager();

	SceneController sceneController = new SceneController();

	@FXML
	private TableView<Company> companiesTableView = new TableView<Company>();

	@FXML
	private Button acceptButton;

	@FXML
	private Label verificationLabel;

	@FXML
	private Button rejectButton;

	@FXML
	private TableColumn<Company, String> companyIdTableColumn;

	@FXML
	private TableColumn<Company, String> companyNameTableColumn;

	@FXML
	private TableColumn<Company, String> contactNumberTableColumn;

	@FXML
	private Button logOutButton;

	@FXML
	private Button backButton;

	@FXML
	private TableColumn<Company, String> mailTableColumn;

	@FXML
	private TableColumn<Company, String> passwordTableColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		companyIdTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyId"));
		companyNameTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
		mailTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("mail"));
		contactNumberTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("contactNumber"));
		passwordTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("password"));
		companiesTableView.setItems(applicationManager.getApplications());
		verificationLabel.setVisible(false);
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
	public void rejectApplication(ActionEvent event) {
		if (companiesTableView.getSelectionModel().getSelectedItem() != null) {
			applicationManager.rejectApplication(companiesTableView.getSelectionModel().getSelectedItem().companyId);
			companiesTableView.setItems(applicationManager.getApplications());
			verificationLabel.setVisible(false);
		} else {
			verificationLabel.setText("Bu işlem için tablodan seçim yapmanız gerekmektedir!");
			verificationLabel.setVisible(true);
		}
	}

	@FXML
	public void confirmApplication(ActionEvent event) {
		if (companiesTableView.getSelectionModel().getSelectedItem() != null) {

			Company company = companiesTableView.getSelectionModel().getSelectedItem();
			applicationManager.confirmApplicaion(new Company(company.companyId, company.companyName, company.mail,
					company.contactNumber, company.password));
			companiesTableView.setItems(applicationManager.getApplications());
			verificationLabel.setVisible(false);
		} else {
			verificationLabel.setText("Bu işlem için tablodan seçim yapmanız gerekmektedir!");
			verificationLabel.setVisible(true);
		}
	}
}