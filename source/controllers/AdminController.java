package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AdminController implements Initializable {

	SceneController sceneController = new SceneController();

	@FXML
	private Button addCompanyButton;

	@FXML
	private Button allCompaniesButton;

	@FXML
	private Button allProductsButton;

	@FXML
	private Button addProductButton;

	@FXML
	private Button ordersButton;

	@FXML
	private Button deleteCompanyButton;

	@FXML
	private Button deleteProductButton;

	@FXML
	private Button logOutButton;

	@FXML
	private Button updateCompanyButton;

	@FXML
	private Button updateExchangeRate;

	@FXML
	private Button updateProductButton;

	@FXML
	private Button applicationsButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	public void logOut(ActionEvent event) {
		sceneController.getAdminScenes(event, "Login");
	}

	@FXML
	public void getCompanies(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminCompany");
	}

	@FXML
	public void getAddCompany(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminAddCompany");
	}

	@FXML
	public void getUpdateCompany(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminUpdateCompany");
	}

	@FXML
	public void getDeleteCompany(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminDeleteCompany");
	}

	@FXML
	public void getProducts(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminProduct");
	}

	@FXML
	public void getAddProduct(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminAddProduct");
	}

	@FXML
	public void getUpdateProduct(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminUpdateProduct");
	}

	@FXML
	public void getDeleteProduct(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminDeleteProduct");
	}

	@FXML
	public void getUpdateExchangeRate(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminExchangeRate");
	}

	@FXML
	public void getApplications(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminApplication");
	}

	@FXML
	public void getOrders(ActionEvent event) {
		sceneController.getAdminScenes(event, "AdminOrder");
	}
}