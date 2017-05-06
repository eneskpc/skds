package model;

public class Staff extends User {
	
	private int companyId;

	public Staff(int companyId) {
		super();
		this.companyId = companyId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
	
}
