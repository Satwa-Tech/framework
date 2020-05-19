package exceptions;

public class ValueNotSelectInListBoxException extends Exception{
	
	private String strMessage;
	
	public ValueNotSelectInListBoxException(String message) {
		strMessage = message;
	}
	
	public String getMessage() {
		return strMessage;
	}

}
