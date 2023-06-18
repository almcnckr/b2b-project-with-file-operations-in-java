package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import entities.Cart;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CartManager {
	Logger logger = new FileLogger();
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);
	File file = new File("carts.txt");
	
	public CartManager() {
		processInfoAlert.setTitle("İşlem Bilgisi");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				processInfoAlert.setHeaderText("İşlem Başarısız! Bir Yetkili ile İletişime Geçin.");
				logger.log(e.getMessage());
				processInfoAlert.showAndWait();
			}
		}
	}

	public void addToCart(Cart cart) {
		Order order = cart.order;
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Ürün Sepete Eklendi");
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("carts.txt", true));
			bWriter.write(order.orderId + "/" + order.companyId + "/" + order.productId + "/" + order.imagePath + "/"
					+ order.productName + "/" + order.unitPrice + "/" + order.numberOfProduct + "/" + order.orderPrice + "/" + "Hazırlanıyor...");
			bWriter.newLine();
			bWriter.close();
			logger.log("Product added to cart" + order.companyId + " " + order.productId);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir Yetkili ile İletişime Geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	public void deleteFromCart(String orderId) {
		try {
			Path source = Paths.get("tempCarts.txt");
			File file = new File("carts.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("tempCarts.txt"));
			processInfoAlert.setTitle("İşlem Bilgisi");
			processInfoAlert.setHeaderText("Ürün Sepetten Silindi");
			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(orderId)) {
					bWriter.write(line);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("carts.txt"));
			logger.log("Product deleted from cart" + orderId);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir Yetkili ile İletişime Geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}

	}
	
	public ObservableList<Cart> getCart(String companyId) {
		ObservableList<Cart> carts = FXCollections.observableArrayList();
		try {
			String[] cart;
			BufferedReader bReader = new BufferedReader(new FileReader("carts.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				if (line.contains(companyId)) {
					cart = line.split("/");
					carts.add(new Cart(new Order(cart[0], cart[1], cart[2], cart[3], cart[4], cart[5], cart[6], cart[7], cart[8])));
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir yetkili ile iletişime geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return carts;
	}
}
