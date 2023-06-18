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
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OrderManager implements IOrderService {
	Logger logger = new FileLogger();
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);
	File file = new File("orders.txt");
	
	public OrderManager() {
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

	@Override
	public void addOrder(Order order) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Sipariş İşlemi Başarılı!");
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("orders.txt", true));
			bWriter.write(order.orderId + "/" + order.companyId + "/" + order.productId + "/" + order.imagePath + "/"
					+ order.productName + "/" + order.unitPrice + "/" + order.numberOfProduct + "/" + order.orderPrice
					+ "/" + "Hazırlanıyor...");
			bWriter.newLine();
			bWriter.close();
			Path source = Paths.get("tempCarts.txt");
			File file = new File("carts.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			bWriter = new BufferedWriter(new FileWriter("tempCarts.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(order.orderId)) {
					bWriter.write(line);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("carts.txt"));
			String[] product;
			int stockAmount;
			source = Paths.get("tempProducts.txt");
			file = new File("products.txt");
			bReader = new BufferedReader(new FileReader(file));
			bWriter = new BufferedWriter(new FileWriter("tempProducts.txt"));
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(order.productId)) {
					bWriter.write(line);
					bWriter.newLine();
				} else {
					product = line.split("/");
					stockAmount = Integer.parseInt(product[4]) - Integer.parseInt(order.numberOfProduct);
					bWriter.write(order.productId + "/" + product[1] + "/" + product[2] + "/" + product[3] + "/"
							+ stockAmount + "/" + product[5]);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("products.txt"));
			logger.log("Product ordered " + order.orderId + " " + order.companyId);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir yetkili ile iletişime geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public void deleteOrder(Order order) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Sipariş İptal İşlemi Başarılı!");
		try {
			String[] product;
			Path source = Paths.get("temp.txt");
			File file = new File("orders.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("temp.txt"));

			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(order.orderId)) {
					bWriter.write(line);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("orders.txt"));
			int stockAmount;
			source = Paths.get("tempProducts.txt");
			file = new File("products.txt");
			bReader = new BufferedReader(new FileReader(file));
			bWriter = new BufferedWriter(new FileWriter("tempProducts.txt"));
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(order.productId)) {
					bWriter.write(line);
					bWriter.newLine();
				} else {
					product = line.split("/");
					stockAmount = Integer.parseInt(product[4]) + Integer.parseInt(order.numberOfProduct);
					bWriter.write(order.productId + "/" + product[1] + "/" + product[2] + "/" + product[3] + "/"
							+ stockAmount + "/" + product[5]);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("products.txt"));
			logger.log("Order deleted " + order.orderId);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir yetkili iletişime geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	public void completeOrder(Order order) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Sipariş Tamamlama İşlemi Başarılı!");
		try {
			Path source = Paths.get("temp.txt");
			File file = new File("orders.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("temp.txt"));

			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(order.orderId)) {
					bWriter.write(line);
					bWriter.newLine();
				} else {
					bWriter.write(order.orderId + "/" + order.companyId + "/" + order.productId + "/" + order.imagePath
							+ "/" + order.productName + "/" + order.unitPrice + "/" + order.numberOfProduct + "/"
							+ order.orderPrice + "/" + "Tamamlandı");
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("orders.txt"));
			logger.log("Order completed " + order.orderId);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public ObservableList<Order> getCompanyOrders(String companyId) {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		try {
			String[] order;
			BufferedReader bReader = new BufferedReader(new FileReader("orders.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				if (line.contains(companyId)) {
					order = line.split("/");
					orders.add(new Order(order[0], order[1], order[2], order[3], order[4], order[5], order[6], order[7],
							order[8]));
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir yetkili ile iletişime geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return orders;
	}

	@Override
	public ObservableList<Order> getOrders() {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		try {
			String[] order;
			BufferedReader bReader = new BufferedReader(new FileReader("orders.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				order = line.split("/");
				orders.add(new Order(order[0], order[1], order[2], order[3], order[4], order[5], order[6], order[7],
						order[8]));
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları kontrol edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return orders;
	}
}
