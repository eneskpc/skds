package model;

public class Response {
	private int id;
	private int previous;
	private String message;
	private int requestId;
	private int userId;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	public Response(int id, int previous, String message, int requestId, int userId) {
		super();
		this.id = id;
		this.previous = previous;
		this.message = message;
		this.requestId = requestId;
		this.userId = userId;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
