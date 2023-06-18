package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.ProductManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AdminDeleteProductController implements Initializable {

	ProductManager productManager = new ProductManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Button acceptButton;

	@FXML
	private Button backButton;

	@FXML
	private Button logOutButton;

	@FXML
	private ComboBox<String> productIdComboBox;

	@FXML
	private Text productNameText;

	@FXML
	private Label verificationLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productIdComboBox.setItems(productManager.getProductsId());
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
	public void getProductName(ActionEvent event) {
		if (productIdComboBox.getValue() != null) {
			productNameText.setText(productManager.getProductName(productIdComboBox.getValue()));
		}
		verificationLabel.setVisible(false);
	}

	@FXML
	public void deleteProduct(ActionEvent event) {
		if (productIdComboBox.getValue() != null) {
			productManager.deleteProduct(productIdComboBox.getValue());
			productIdComboBox.setItems(productManager.getProductsId());
			productNameText.setText(null);
		} else {
			verificationLabel.setText("Tüm alanların doldurulması zorunludur!");
			verificationLabel.setVisible(true);
		}
	}
}