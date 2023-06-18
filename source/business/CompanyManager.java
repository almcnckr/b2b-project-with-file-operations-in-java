package business;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import entities.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CompanyManager implements ICompanyService {
	Logger logger = new FileLogger();
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);
	File file = new File("companies.txt");

	public CompanyManager() {
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
	public void addCompany(Company company) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Bayi Ekleme İşlemi Başarılı!");
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("companies.txt", true));
			bWriter.write(company.companyId + "/" + company.companyName + "/" + company.mail + "/"
					+ company.contactNumber + "/" + company.password);
			bWriter.newLine();
			bWriter.close();
			logger.log("Company added " + company.companyId + " " + company.companyName);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public void deleteCompany(String id) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Bayi Silme İşlemi Başarılı!");
		try {
			Path source = Paths.get("temp.txt");
			File file = new File("companies.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("temp.txt"));

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
			Files.move(source, source.resolveSibling("companies.txt"));
			logger.log("Company deleted " + id);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public void updateCompany(String companyId, String companyName, String mail, String contactNumber,
			String password) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		processInfoAlert.setHeaderText("Bayi Güncelleme İşlemi Başarılı!");
		try {
			Path source = Paths.get("temp.txt");
			File file = new File("companies.txt");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("temp.txt"));

			String line;
			while ((line = bReader.readLine()) != null) {
				if (!line.startsWith(companyId)) {
					bWriter.write(line);
					bWriter.newLine();
				} else {
					bWriter.write(companyId + "/" + companyName + "/" + mail + "/" + contactNumber + "/" + password);
					bWriter.newLine();
				}
			}
			bReader.close();
			bWriter.close();
			file.delete();
			Files.move(source, source.resolveSibling("companies.txt"));
			logger.log("Company updated " + companyId + " " + companyName);
			processInfoAlert.showAndWait();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	@Override
	public ObservableList<Company> getCompanies() {
		ObservableList<Company> companies = FXCollections.observableArrayList();
		try {
			String[] company;
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));
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

	@Override
	public ObservableList<String> getCompaniesId() {
		ObservableList<String> companyIdList = FXCollections.observableArrayList();
		try {
			String[] company;
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));
			String line;
			while ((line = bReader.readLine()) != null) {
				company = line.split("/");
				companyIdList.add(company[0]);
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return companyIdList;
	}

	@Override
	public String getCompanyName(String companyId) {
		String[] company = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(companyId)) {
					company = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return company[1];
	}

	@Override
	public String getMail(String companyId) {
		String[] company = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(companyId)) {
					company = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return company[2];
	}

	@Override
	public String getContactNumber(String companyId) {
		String[] company = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(companyId)) {
					company = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return company[3];
	}

	@Override
	public String getPassword(String companyId) {
		String[] company = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));

			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(companyId)) {
					company = line.split("/");
					break;
				}
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return company[4];
	}

	public void setLoggedCompany(String companyId) {
		String[] company = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("companies.txt"));
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("loggedCompany.txt"));
			while ((line = bReader.readLine()) != null) {
				if (line.startsWith(companyId)) {
					company = line.split("/");
					bWriter.write(
							company[0] + "/" + company[1] + "/" + company[2] + "/" + company[3] + "/" + company[4]);
					break;
				}
			}
			bWriter.close();
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	public Company getLoggedCompany() {
		Company loggedCompany = null;
		String[] company = null;
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("loggedCompany.txt"));
			while ((line = bReader.readLine()) != null) {
				company = line.split("/");
				loggedCompany = new Company(company[0], company[1], company[2], company[3], company[4]);
				break;
			}
			bReader.close();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları Kontrol Edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
		return loggedCompany;
	}
}