package model;

public class Customer extends User {
	
	private String gender;
	private String homeCity;
	private int birthYear;
	
	public Customer(int id, String name, String email, String gender, String homeCity, int birthYear) {
		super(id, name, email);
		this.gender = gender;
		this.homeCity = homeCity;
		this.birthYear = birthYear;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	
	
	
}
