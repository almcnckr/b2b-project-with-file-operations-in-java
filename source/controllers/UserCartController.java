package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.CartManager;
import business.CompanyManager;
import business.OrderManager;
import entities.Cart;
import entities.ExchangeRate;
import entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class UserCartController implements Initializable {

	CartManager cartManager = new CartManager();

	CompanyManager companyManager = new CompanyManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Button backButton;

	@FXML
	private Button cartButton;

	@FXML
	private Button ordersButton;

	@FXML
	private TableColumn<Cart, ImageView> imageTableColumn;

	@FXML
	private Button logOutButton;

	@FXML
	private Label loggedCompanyLabel;

	@FXML
	private Label exchangeRateLabel;

	@FXML
	private TableColumn<Cart, String> numberOfProductTableColumn;

	@FXML
	private TableColumn<Cart, String> orderPriceTableColumn;

	@FXML
	private Button placeOrderButton;

	@FXML
	private Button deleteFromCartButton;

	@FXML
	private TableColumn<Cart, String> productIdTableColumn;

	@FXML
	private TableColumn<Cart, String> productNameTableColumn;

	@FXML
	private TableView<Cart> productsTableView;

	@FXML
	private TableColumn<Cart, String> unitPriceTableColumn;

	@FXML
	private Label verificationLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productsTableView.setPlaceholder(new Label("Sepetiniz Boş"));
		loggedCompanyLabel.setText(companyManager.getLoggedCompany().companyName);
		imageTableColumn.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("image"));
		productIdTableColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("productId"));
		productNameTableColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("productName"));
		unitPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("unitPrice"));
		numberOfProductTableColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("numberOfProduct"));
		orderPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("orderPrice"));
		productsTableView.setItems(cartManager.getCart(companyManager.getLoggedCompany().companyId));
		loggedCompanyLabel.setAlignment(Pos.CENTER);
		loggedCompanyLabel.setText(companyManager.getLoggedCompany().companyName);
		verificationLabel.setTextFill(Color.RED);
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
	public void deleteFromCart() {
		if (productsTableView.getSelectionModel().getSelectedItem() != null) {
			cartManager.deleteFromCart(productsTableView.getSelectionModel().getSelectedItem().getOrderId());
			productsTableView.setItems(cartManager.getCart(companyManager.getLoggedCompany().companyId));
			verificationLabel.setVisible(false);
		} else {
			verificationLabel.setText("Bu işlem için ürün seçmeniz gerekmektedir!");
			verificationLabel.setVisible(true);
		}
	}

	@FXML
	public void placeOrder() {
		if (productsTableView.getSelectionModel().getSelectedItem() != null) {
			OrderManager orderManager = new OrderManager();
			Order order = productsTableView.getSelectionModel().getSelectedItem().order;
			orderManager.addOrder(new Order(order.orderId, order.companyId, order.productId, order.imagePath,
					order.productName, order.unitPrice, order.numberOfProduct, order.orderPrice, order.orderProcess));
			productsTableView.setItems(cartManager.getCart(companyManager.getLoggedCompany().companyId));
			verificationLabel.setVisible(false);
		} else {
			verificationLabel.setText("Bu işlem için ürün seçmeniz gerekmektedir!");
			verificationLabel.setVisible(true);
		}
	}

	@FXML
	public void getOrders(ActionEvent event) {
		sceneController.getUserScenes(event, "UserOrder");
	}
	
	@FXML
	public void getProducts(ActionEvent event) {
		sceneController.getUserScenes(event, "UserProducts");
	}
}
