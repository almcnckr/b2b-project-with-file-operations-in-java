package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import business.ProductManager;
import entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AdminAddProductController implements Initializable {
	
	ProductManager productManager = new ProductManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Button acceptButton;

	@FXML
	private Label verificationLabel;

	@FXML
	private TextField categoryTextField;

	@FXML
	private Button backButton;

	@FXML
	private Button imageButton;

	@FXML
	private Button logOutButton;

	@FXML
	private Text imagePathText;

	@FXML
	private TextField productNameTextField;

	@FXML
	private TextField stockAmountTextField;

	@FXML
	private TextField unitPriceTextField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
	public void fileChooser(ActionEvent event) {
		FileChooser fChooser = new FileChooser();
		fChooser.getExtensionFilters().add(
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.jpe", "*.jfif", "*.bmp", "*.dib"));
		File file = fChooser.showOpenDialog(null);
		if (file != null) {
			String path = file.getAbsolutePath();
			imagePathText.setText(path);
			imagePathText.setVisible(true);
		}
	}

	@FXML
	public void addProduct(ActionEvent event) {
		if ((!productNameTextField.getText().isBlank() && !stockAmountTextField.getText().isBlank()
				&& !unitPriceTextField.getText().isBlank() && !imagePathText.getText().isBlank()
				&& !categoryTextField.getText().isBlank())) {
			if (!productNameTextField.getText().contains("/") && !stockAmountTextField.getText().contains("/")
					&& !unitPriceTextField.getText().contains("/") && !categoryTextField.getText().contains("/")) {
				if (stockAmountTextField.getText().matches("[0-9]*")
						&& unitPriceTextField.getText().matches("[0-9]*")) {
					productManager.addProduct(new Product(categoryTextField.getText(), productNameTextField.getText(),
							stockAmountTextField.getText(), unitPriceTextField.getText(), imagePathText.getText()));
					productNameTextField.setText(null);
					categoryTextField.setText(null);
					stockAmountTextField.setText(null);
					unitPriceTextField.setText(null);
					imagePathText.setText(null);
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