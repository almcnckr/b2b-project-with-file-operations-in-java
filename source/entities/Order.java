package entities;

import java.util.Random;

import javafx.scene.image.ImageView;

public class Order {
	public String orderId;
	public String companyId;
	public String productId;
	public String imagePath;
	public String productName;
	public String numberOfProduct;
	public String unitPrice;
	public String orderPrice;
	public String orderProcess;
	Random random = new Random();

	public Order(String companyId, String productId, String imagePath, String productName, String unitPrice,
			String numberOfProduct, String orderPrice) {
		this.orderId = Integer.toString(random.nextInt(10000, 99999)) + productName.charAt(0);
		this.companyId = companyId;
		this.imagePath = imagePath;
		this.productName = productName;
		this.numberOfProduct = numberOfProduct;
		this.orderPrice = orderPrice;
		this.unitPrice = unitPrice;
		this.productId = productId;
	}

	public Order(String orderId, String companyId, String productId, String imagePath, String productName,
			String unitPrice, String numberOfProduct, String orderPrice, String orderProcess) {
		this.orderId = orderId;
		this.companyId = companyId;
		this.imagePath = imagePath;
		this.productName = productName;
		this.numberOfProduct = numberOfProduct;
		this.orderPrice = orderPrice;
		this.unitPrice = unitPrice;
		this.productId = productId;
		this.orderProcess = orderProcess;
	}

	public ImageView getImage() {
		ImageView image = new ImageView(imagePath);
		image.setFitWidth(75);
		image.setFitHeight(75);
		return image;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getNumberOfProduct() {
		return numberOfProduct;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public String getOrderPrice() {
		return orderPrice;
	}
	
	public String getOrderProcess() {
		return orderProcess;
	}
}
