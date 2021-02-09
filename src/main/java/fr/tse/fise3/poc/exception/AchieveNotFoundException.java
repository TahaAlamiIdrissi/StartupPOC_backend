package fr.tse.fise3.poc.exception;

public class AchieveNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AchieveNotFoundException(String message) {
		super(message);
	}
	public AchieveNotFoundException(String message, Exception e) {
		super(message,e);
	}
}
