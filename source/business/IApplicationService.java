package business;

import entities.Company;
import javafx.collections.ObservableList;

public interface IApplicationService {

	void confirmApplicaion(Company company);

	void rejectApplication(String companyId);

	void addApplication(Company company);

	ObservableList<Company> getApplications();

}
