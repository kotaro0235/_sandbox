package osero;

public enum Status {
	
	NONE(0),
	BLACK(1),
	WHITE(2);
	
	private int statusCode;
	
	private Status(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}

}
