package business;

import entities.Product;
import javafx.collections.ObservableList;

public interface IProductService {

	void addProduct(Product product);
	
	void updateProduct(String id, String name, String unitPrice, String stockAmount, String image, String category);

	void deleteProduct(String id);

	ObservableList<String> getProductsId();

	String getImagePath(String productId);

	String getStockAmount(String productId);

	String getUnitPrice(String productId);

	String getProductName(String productId);

	ObservableList<Product> getProducts();

	String getCategory(String productId);
    
}