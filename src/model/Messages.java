package model;

public class Messages {

	private int id;
	private int senderId;
	private int receiverId;
	private String title;
	private String detail;
	private int previous;
	
	
	public Messages() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Messages(int id, int senderId, int receiverId, String title, String detail, int previous) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.title = title;
		this.detail = detail;
		this.previous = previous;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrevious() {
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}
	
	
	
	
}
