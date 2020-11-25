package fr.tse.fise3.poc.exception;

public class AchieveNotFoundException extends RuntimeException{

	public AchieveNotFoundException(String message) {
		super(message);
	}
	public AchieveNotFoundException(String message, Exception e) {
		super(message,e);
	}
}
