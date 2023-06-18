package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.CartManager;
import business.CompanyManager;
import business.OrderManager;
import business.ProductManager;
import entities.Cart;
import entities.Company;
import entities.ExchangeRate;
import entities.Order;
import entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class UserProductsController implements Initializable {

	ProductManager productManager = new ProductManager();
	
	CompanyManager companyManager = new CompanyManager();
	
	OrderManager orderManager = new OrderManager();

	CartManager cartManager = new CartManager();

	SceneController sceneController = new SceneController();
	
	@FXML
	private Button backButton;

	@FXML
	private Button cartButton;
	
	@FXML
	private Button ordersButton;

	@FXML
	private TableView<Product> productsTableView;

	@FXML
	private TableColumn<Product, ImageView> imageTableColumn;

	@FXML
	private TableColumn<Product, String> categoryTableColumn;

	@FXML
	private Button logOutButton;

	@FXML
	private Label verificationLabel;

	@FXML
	private Label exchangeRateLabel;

	@FXML
	private Label loggedCompanyLabel;

	@FXML
	private Button placeOrderButton;

	@FXML
	private TextField numberOfProductsTextField;

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
		numberOfProductsTextField.setVisible(false);
		imageTableColumn.setCellValueFactory(new PropertyValueFactory<Product, ImageView>("image"));
		categoryTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		productIdTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
		productNameTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		unitPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("unitPrice"));
		stockAmountTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("stockAmount"));
		productsTableView.setItems(productManager.getProducts());
		loggedCompanyLabel.setText(companyManager.getLoggedCompany().companyName);
		verificationLabel.setTextFill(Color.RED);
		loggedCompanyLabel.setAlignment(Pos.CENTER);
		verificationLabel.setAlignment(Pos.CENTER);
		exchangeRateLabel.setAlignment(Pos.CENTER);
		exchangeRateLabel.setText("1$ = " + ExchangeRate.getExchangeRate());
	}

	@FXML
	public void logOut(ActionEvent event) {
		sceneController.getUserScenes(event, "Login");
	}

	@FXML
	public void backToMenu(ActionEvent event) {
		sceneController.getUserScenes(event, "User");
	}

	@FXML
	void getCart(ActionEvent event) {
		sceneController.getUserScenes(event, "UserCart");
	}

	@FXML
	public void addCart(ActionEvent event) {
		if (productsTableView.getSelectionModel().getSelectedItem() != null) {
			Company company = companyManager.getLoggedCompany();
			Product product = productsTableView.getSelectionModel().getSelectedItem();
			if (!numberOfProductsTextField.getText().isEmpty() && numberOfProductsTextField.getText().matches("[0-9]*")
					&& Double.parseDouble(product.stockAmount) >= Double
							.parseDouble(numberOfProductsTextField.getText())) {
				double orderPrice = (Double.parseDouble(product.unitPrice)
						* Double.parseDouble(numberOfProductsTextField.getText()));
				cartManager.addToCart(new Cart(new Order(company.companyId, product.id, product.imagePath, product.name,
						product.unitPrice, numberOfProductsTextField.getText(), Double.toString(orderPrice))));
				verificationLabel.setVisible(false);
			} else {
				verificationLabel.setText("Eksik veya geçersiz ürün sayısı!");
				verificationLabel.setVisible(true);
			}
		} else {
			verificationLabel.setText("Bu işlem için ürün seçmeniz gerekmektedir!");
			verificationLabel.setVisible(true);
		}
	}

	@FXML
	public void getNumberOfProductTextField() {
		numberOfProductsTextField.setVisible(true);
	}
	
	@FXML
    public void getOrders(ActionEvent event) {
		sceneController.getUserScenes(event, "UserOrder");
    }
}