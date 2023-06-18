package business;

import entities.Order;
import javafx.collections.ObservableList;

public interface IOrderService {

	void addOrder(Order order);
	
	ObservableList<Order> getCompanyOrders(String companyId);

	ObservableList<Order> getOrders();

	void deleteOrder(Order order);

}
