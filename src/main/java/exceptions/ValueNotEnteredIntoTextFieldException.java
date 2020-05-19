package exceptions;

public class ValueNotEnteredIntoTextFieldException extends Exception {
	private String strMessage;
	
	public ValueNotEnteredIntoTextFieldException(String message) {
		strMessage = message;
	}
	
	public String getMessage() {
		return strMessage;
	}
}
