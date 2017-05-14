package model;

public class Staff extends User {

	private int companyId;

	public Staff() {
		super();
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}