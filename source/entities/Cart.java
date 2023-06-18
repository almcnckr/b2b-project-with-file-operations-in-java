package entities;

import javafx.scene.image.ImageView;

public class Cart {
	public Order order;
	
	public Cart(Order order) {
		this.order = order;
	}
	
	public ImageView getImage() {
		ImageView image = new ImageView(order.imagePath);
		image.setFitWidth(75);
		image.setFitHeight(75);
		return image;
	}

	public String getOrderId() {
		return order.orderId;
	}

	public String getCompanyId() {
		return order.companyId;
	}

	public String getProductId() {
		return order.productId;
	}

	public String getProductName() {
		return order.productName;
	}

	public String getNumberOfProduct() {
		return order.numberOfProduct;
	}

	public String getUnitPrice() {
		return order.unitPrice;
	}

	public String getOrderPrice() {
		return order.orderPrice;
	}
	
}