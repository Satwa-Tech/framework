package exceptions;

public class SearchResultIsEmptyException extends Exception {
	
	private String strMessage;
	
	public SearchResultIsEmptyException(String message) {
		strMessage = message;
	}
	
	public String getMessage() {
		return strMessage;
	}


}
