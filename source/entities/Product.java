package entities;

import java.util.Random;

import javafx.scene.image.ImageView;

public class Product implements IEntity {
	public String category;
	public String id;
	public String name;
	public String stockAmount;
	public String unitPrice;
	public String imagePath;
	Random random = new Random();

	public Product(String category, String name, String stockAmount, String unitPrice, String imagePath) {
		this.id = Integer.toString(random.nextInt(10000, 99999)) + name.charAt(0);
		this.name = name;
		this.stockAmount = stockAmount;
		this.unitPrice = unitPrice;
		this.imagePath = imagePath;
		this.category = category;
	}

	public Product(String id, String category, String name, String stockAmount, String unitPrice, String imagePath) {
		this.id = id;
		this.name = name;
		this.stockAmount = stockAmount;
		this.unitPrice = unitPrice;
		this.imagePath = imagePath;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStockAmount() {
		return stockAmount;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public ImageView getImage() {
		ImageView image = new ImageView(imagePath);
		image.setFitWidth(75);
		image.setFitHeight(75);
		return image;
	}

	public String getCategory() {
		return category;
	}
}