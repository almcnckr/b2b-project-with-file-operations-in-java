package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.OrderManager;
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

public class AdminOrderController implements Initializable {

	OrderManager orderManager = new OrderManager();

	SceneController sceneController = new SceneController();

	@FXML
	private Button backButton;

	@FXML
	private TableColumn<Order, String> companyIdTableColumn;

	@FXML
	private TableColumn<Order, ImageView> imageTableColumn;

	@FXML
	private Button logOutButton;

	@FXML
	private Button completeOrderButton;

	@FXML
	private Button deleteOrderButton;

	@FXML
	private TableColumn<Order, String> numberOfProductTableColumn;

	@FXML
	private TableColumn<Order, String> orderIdTableColumn;

	@FXML
	private TableColumn<Order, String> orderPrcoessTableColumn;

	@FXML
	private TableColumn<Order, String> orderPriceTableColumn;

	@FXML
	private TableColumn<Order, String> productIdTableColumn;

	@FXML
	private TableColumn<Order, String> productNameTableColumn;

	@FXML
	private TableView<Order> productsTableView;

	@FXML
	private TableColumn<Order, String> unitPriceTableColumn;

	@FXML
	private Label verificationLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productsTableView.setPlaceholder(new Label("Sipariş Bulunmamakta"));
		companyIdTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("companyId"));
		imageTableColumn.setCellValueFactory(new PropertyValueFactory<Order, ImageView>("image"));
		productIdTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("productId"));
		orderIdTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderId"));
		orderPrcoessTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderProcess"));
		productNameTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("productName"));
		unitPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("unitPrice"));
		numberOfProductTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("numberOfProduct"));
		orderPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderPrice"));
		productsTableView.setItems(orderManager.getOrders());
		verificationLabel.setTextFill(Color.RED);
		verificationLabel.setAlignment(Pos.CENTER);

	}

	@FXML
	public void completeOrder(ActionEvent event) {
		if (productsTableView.getSelectionModel().getSelectedItem() != null) {
			Order order = productsTableView.getSelectionModel().getSelectedItem();
			orderManager.completeOrder(new Order(order.orderId, order.companyId, order.productId, order.imagePath,
					order.productName, order.unitPrice, order.numberOfProduct, order.orderPrice, order.orderProcess));
			productsTableView.setItems(orderManager.getOrders());
			verificationLabel.setVisible(false);
		} else {
			verificationLabel.setText("Bu işlem için tablodan seçim yapmanız gerekmektedir!");
			verificationLabel.setVisible(true);
		}
	}

	@FXML
	public void deleteOrder(ActionEvent event) {
		if (productsTableView.getSelectionModel().getSelectedItem() != null) {
			Order order = productsTableView.getSelectionModel().getSelectedItem();
			orderManager.deleteOrder(new Order(order.orderId, order.companyId, order.productId, order.imagePath,
					order.productName, order.unitPrice, order.numberOfProduct, order.orderPrice, order.orderProcess));
			productsTableView.setItems(orderManager.getOrders());
			verificationLabel.setVisible(false);
		} else {
			verificationLabel.setText("Bu işlem için tablodan seçim yapmanız gerekmektedir!");
			verificationLabel.setVisible(true);
		}
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