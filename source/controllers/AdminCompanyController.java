package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import business.CompanyManager;
import entities.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminCompanyController implements Initializable {

	CompanyManager companyManager = new CompanyManager();

	SceneController sceneController = new SceneController();

	@FXML
	private TableView<Company> companiesTableView = new TableView<Company>();

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
		companiesTableView.setPlaceholder(new Label("Hiç bayi bulunmamakta!"));
		companyIdTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyId"));
		companyNameTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
		mailTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("mail"));
		contactNumberTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("contactNumber"));
		passwordTableColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("password"));
		companiesTableView.setItems(companyManager.getCompanies());
	}

	@FXML
	public void logOut(ActionEvent event) {
		sceneController.getAdminScenes(event, "Login");
	}

	@FXML
	public void backToMenu(ActionEvent event) {
		sceneController.getAdminScenes(event, "Admin");
	}
}