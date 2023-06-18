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

import entities.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ApplicationManager implements IApplicationService {
	Logger logger = new FileLogger();
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);
	File file = new File("pendingApplications.txt");

	public ApplicationManager() {
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
	public void addApplication(Company company) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Başvuru İşleminiz Başarılı!");
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("pendingApplications.txt", true));
			bWriter.write(company.companyId + "/" + company.companyName + "/" + company.mail + "/"
					+ company.contactNumber + "/" + company.password);
			bWriter.newLine();
			bWriter.close();
			logger.log("Company application " + company.companyId + " " + company.companyName);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Lütfen bir yetkiliyle iletişime geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public void confirmApplicaion(Company company) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Başvuru Kabul Edildi Başarılı!");
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("companies.txt", true));
			bWriter.write(company.companyId + "/" + company.companyName + "/" + company.mail + "/"
					+ company.contactNumber + "/" + company.password);
			bWriter.newLine();
			bWriter.close();
			Path source = Paths.get("temp.txt");
			File file = new File("pendingApplications.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			bWriter = new BufferedWriter(new FileWriter("temp.txt"));

			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(company.companyId)) {
					bWriter.write(line);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("pendingApplications.txt"));
			logger.log("Confirmed application " + company.companyId + " " + company.companyName);
			processInfoAlert.showAndWait();

		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public void rejectApplication(String companyId) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Başvuru Silme İşlemi Başarılı!");
		try {
			Path source = Paths.get("temp.txt");
			File file = new File("pendingApplications.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("temp.txt"));

			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(companyId)) {
					bWriter.write(line);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("pendingApplications.txt"));
			logger.log("Application deleted " + companyId);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public ObservableList<Company> getApplications() {
		ObservableList<Company> companies = FXCollections.observableArrayList();
		try {
			String[] company;
			BufferedReader bReader = new BufferedReader(new FileReader("pendingApplications.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				company = line.split("/");
				companies.add(new Company(company[0], company[1], company[2], company[3], company[4]));
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return companies;
	}
}
