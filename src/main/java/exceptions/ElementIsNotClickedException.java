package exceptions;

public class ElementIsNotClickedException extends Exception {
	private String strMessage;
	
	public ElementIsNotClickedException(String message) {
		strMessage = message;
	}
	
	public String getMessage() {
		return strMessage;
	}
}
