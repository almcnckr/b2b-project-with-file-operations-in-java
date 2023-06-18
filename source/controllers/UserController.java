package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.CompanyManager;
import entities.ExchangeRate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserController implements Initializable {

	CompanyManager companyManager = new CompanyManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Button cartButton;

	@FXML
	private Button logOutButton;

	@FXML
	private Label loggedCompanyLabel;

	@FXML
	private Button productsButton;

	@FXML
	private Button ordersButton;

	@FXML
	private Label exchangeRateLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loggedCompanyLabel.setAlignment(Pos.CENTER);
		loggedCompanyLabel.setText(companyManager.getLoggedCompany().companyName);
		exchangeRateLabel.setAlignment(Pos.CENTER);
		exchangeRateLabel.setText("1$ = " + ExchangeRate.getExchangeRate());
	}

	@FXML
	public void getCart(ActionEvent event) {
		sceneController.getUserScenes(event, "UserCart");
	}

	@FXML
	public void getProducts(ActionEvent event) {
		sceneController.getUserScenes(event, "UserProducts");
	}

	@FXML
	public void logOut(ActionEvent event) {
		sceneController.getUserScenes(event, "Login");
	}

	@FXML
	public void getOrders(ActionEvent event) {
		sceneController.getUserScenes(event, "UserOrder");
	}
}