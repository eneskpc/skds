package model;

public class Company extends User {
	
	private String detail;
	private String contactName;
	private String contactPhone;
	private String imageUrl;
	private boolean approved;
	
	
	public Company(String detail, String contactName, String contactPhone,
			String imageUrl, boolean approved) {
		this.detail = detail;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.imageUrl = imageUrl;
		this.approved = approved;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getContactPhone() {
		return contactPhone;
	}


	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public boolean isApproved() {
		return approved;
	}


	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	

}
