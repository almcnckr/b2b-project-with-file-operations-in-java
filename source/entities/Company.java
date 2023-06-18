package entities;

import java.util.Random;

public class Company implements IEntity {
	public String companyId;
	public String companyName;
	public String mail;
	public String contactNumber;
	public String password;
	Random random = new Random();

	public Company(String companyName, String mail, String contactNumber, String password) {
		this.companyId = Integer.toString(random.nextInt(100000, 999999)) + companyName.charAt(0);
		this.companyName = companyName;
		this.mail = mail;
		this.contactNumber = contactNumber;
		this.password = password;
	}

	public Company(String companyId, String companyName, String mail, String contactNumber, String password) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.mail = mail;
		this.contactNumber = contactNumber;
		this.password = password;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getMail() {
		return mail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getPassword() {
		return password;
	}
}