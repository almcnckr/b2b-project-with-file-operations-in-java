package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.ProductManager;
import entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class AdminProductController implements Initializable {
	
	ProductManager productManager = new ProductManager();
	
	SceneController sceneController = new SceneController();

	@FXML
	private Button backButton;

	@FXML
	private TableView<Product> productsTableView;

	@FXML
	private TableColumn<Product, ImageView> imageTableColumn;

	@FXML
	private TableColumn<Product, String> categoryTableColumn;

	@FXML
	private Button logOutButton;

	@FXML
	private TableColumn<Product, String> productIdTableColumn;

	@FXML
	private TableColumn<Product, String> productNameTableColumn;

	@FXML
	private TableColumn<Product, String> stockAmountTableColumn;

	@FXML
	private TableColumn<Product, String> unitPriceTableColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productsTableView.setPlaceholder(new Label("Hiç ürün bulunmamakta!"));
		imageTableColumn.setCellValueFactory(new PropertyValueFactory<Product, ImageView>("image"));
		categoryTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		productIdTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
		productNameTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		unitPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("unitPrice"));
		stockAmountTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("stockAmount"));
		productsTableView.setItems(productManager.getProducts());
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