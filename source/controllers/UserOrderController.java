package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.CompanyManager;
import business.OrderManager;
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

public class UserOrderController implements Initializable{
	
	CompanyManager companyManager = new CompanyManager();
	
	OrderManager orderManager = new OrderManager();

	SceneController sceneController = new SceneController();
	
    @FXML
    private Button backButton;

    @FXML
    private Button cartButton;

    @FXML
    private TableColumn<Order, ImageView> imageTableColumn;

    @FXML
    private Button logOutButton;

    @FXML
    private Label loggedCompanyLabel;

    @FXML
    private Label exchangeRateLabel;

    @FXML
    private TableColumn<Order, String> numberOfProductTableColumn;

    @FXML
    private TableColumn<Order, String> orderIdTableColumn;

    @FXML
    private TableColumn<Order, String> orderPrcoessTableColumn;

    @FXML
    private TableColumn<Order, String> orderPriceTableColumn;

    @FXML
    private Button productsButton;
    
    @FXML
    private Button deleteOrderButton;

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
    	productsTableView.setPlaceholder(new Label("Siparişiniz bulunmamakta!"));
		loggedCompanyLabel.setText(companyManager.getLoggedCompany().companyName);
		imageTableColumn.setCellValueFactory(new PropertyValueFactory<Order, ImageView>("image"));
		productIdTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("productId"));
		orderIdTableColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("orderId"));
		orderPrcoessTableColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("orderProcess"));
		productNameTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("productName"));
		unitPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("unitPrice"));
		numberOfProductTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("numberOfProduct"));
		orderPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderPrice"));
		productsTableView.setItems(orderManager.getCompanyOrders(companyManager.getLoggedCompany().companyId));
		loggedCompanyLabel.setAlignment(Pos.CENTER);
		loggedCompanyLabel.setText(companyManager.getLoggedCompany().companyName);
		verificationLabel.setTextFill(Color.RED);
		verificationLabel.setAlignment(Pos.CENTER);
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
    public void deleteOrder() {
    	if (productsTableView.getSelectionModel().getSelectedItem() != null) {
    		Order order = productsTableView.getSelectionModel().getSelectedItem();
			orderManager.deleteOrder(new Order(order.orderId, order.companyId, order.productId, order.imagePath,
					order.productName, order.unitPrice, order.numberOfProduct, order.orderPrice, order.orderProcess));
			productsTableView.setItems(orderManager.getCompanyOrders(companyManager.getLoggedCompany().companyId));
		}
    	else {
			verificationLabel.setText("Bu işlem için tablodan seçim yapmanız gerekmektedir!");
			verificationLabel.setVisible(true);
		}
    }

    @FXML
	public void logOut(ActionEvent event) {
    	sceneController.getUserScenes(event, "Login");
	}

	@FXML
	public void backToMenu(ActionEvent event) {
		sceneController.getUserScenes(event, "User");
	}
}
