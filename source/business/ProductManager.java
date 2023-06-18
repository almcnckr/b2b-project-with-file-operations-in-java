package business;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProductManager implements IProductService {
	Logger logger = new FileLogger();
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);
	File file = new File("products.txt");
	
	public ProductManager() {
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
	public void addProduct(Product product) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Ürün Ekleme İşlemi Başarılı!");
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("products.txt", true));
			bWriter.write(product.id + "/" + product.category + "/" + product.name + "/" + product.unitPrice + "/"
					+ product.stockAmount + "/" + product.imagePath);
			bWriter.newLine();
			bWriter.close();
			logger.log("Product added " + product.id + " " + product.name);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public void deleteProduct(String id) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Ürün Silme İşlemi Başarılı!");
		try {
			Path source = Paths.get("tempProducts.txt");
			File file = new File("products.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("tempProducts.txt"));

			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(id)) {
					bWriter.write(line);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("products.txt"));
			logger.log("Product deleted " + id);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public void updateProduct(String id, String category, String name, String unitPrice, String stockAmount,
			String image) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Ürün Güncelleme İşlemi Başarılı!");
		try {
			Path source = Paths.get("tempProducts.txt");
			File file = new File("products.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("tempProducts.txt"));

			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(id)) {
					bWriter.write(line);
					bWriter.newLine();
				} else {
					bWriter.write(id + "/" + category + "/" + name + "/" + unitPrice + "/" + stockAmount + "/" + image);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("products.txt"));
			logger.log("Product updated " + id + " " + name);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public ObservableList<Product> getProducts() {
		ObservableList<Product> products = FXCollections.observableArrayList();
		try {
			String[] product;
			BufferedReader bReader = new BufferedReader(new FileReader("products.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				product = line.split("/");
				products.add(new Product(product[0], product[1], product[2], product[4], product[3], product[5]));
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return products;
	}

	@Override
	public ObservableList<String> getProductsId() {
		ObservableList<String> productIdList = FXCollections.observableArrayList();
		try {
			String[] product;
			BufferedReader bReader = new BufferedReader(new FileReader("products.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				product = line.split("/");
				productIdList.add(product[0]);
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return productIdList;
	}

	@Override
	public String getCategory(String productId) {
		String[] product = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("products.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(productId)) {
					product = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return product[1];
	}

	@Override
	public String getProductName(String productId) {
		String[] product = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("products.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(productId)) {
					product = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return product[2];
	}

	@Override
	public String getUnitPrice(String productId) {
		String[] product = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("products.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(productId)) {
					product = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return product[3];
	}

	@Override
	public String getStockAmount(String productId) {
		String[] product = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("products.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(productId)) {
					product = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return product[4];
	}

	@Override
	public String getImagePath(String productId) {
		String[] product = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("products.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(productId)) {
					product = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return product[5];
	}
}