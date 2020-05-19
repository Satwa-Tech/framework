package exceptions;

public class ValueIsInvalidFormatException extends RuntimeException{
	private String strMessage;
	
	public ValueIsInvalidFormatException(String message) {
		strMessage = message;
	}
	
	public String getMessage() {
		return strMessage;
	}
}
