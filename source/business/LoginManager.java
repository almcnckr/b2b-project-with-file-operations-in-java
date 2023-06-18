package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginManager {
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);
	Logger logger = new FileLogger();
	File file = new File("admins.txt");
	File companyFile = new File("companies.txt");

	public LoginManager() {
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
		if (!companyFile.exists()) {
			try {
				companyFile.createNewFile();
			} catch (IOException e) {
				processInfoAlert.setHeaderText("İşlem Başarısız! Bir Yetkili ile İletişime Geçin.");
				logger.log(e.getMessage());
				processInfoAlert.showAndWait();
			}
		}
	}

	public int authenticateAdmin(String userName, String password) {
		try {
			int counter = 0;
			String line;
			String[] admin;
			BufferedReader bReader = new BufferedReader(new FileReader("admins.txt"));
			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(userName)) {
					admin = line.split("/");
					if (admin[1].contentEquals(password)) {
						counter = counter + 1;
					}
				}
			}
			bReader.close();
			if (counter == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir Yetkili ile İletişime Geçinz.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return 0;
	}

	public int authenticateCompany(String companyId, String password) {
		try {
			int counter = 0;
			String line;
			String[] company;
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));
			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(companyId)) {
					company = line.split("/");
					if (company[4].contentEquals(password)) {
						counter = counter + 1;
					}
				}
			}
			bReader.close();
			if (counter == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir Yetkili ile İletişime Geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return 0;
	}
}
