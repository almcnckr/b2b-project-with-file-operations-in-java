package business;

import entities.Company;
import javafx.collections.ObservableList;

public interface ICompanyService {
    void addCompany(Company company);

    void deleteCompany(String id);

	ObservableList<String> getCompaniesId();

	String getCompanyName(String companyId);

	String getMail(String companyId);

	String getContactNumber(String companyId);

	String getPassword(String companyId);

	void updateCompany(String companyId, String companyName, String mail, String contactNumber, String password);

	ObservableList<Company> getCompanies();
}