package exceptions;

public class CheckBoxNotSelectedException extends Exception {
	private String strMessage;
	
	public CheckBoxNotSelectedException(String message) {
		strMessage = message;
	}
	
	public String getMessage() {
		return strMessage;
	}

}
