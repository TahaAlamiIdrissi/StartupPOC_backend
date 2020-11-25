package io.alami.idrissi.achieve.exception;

public class AchieveNotFoundException extends RuntimeException{

	public AchieveNotFoundException(String message) {
		super(message);
	}
	public AchieveNotFoundException(String message, Exception e) {
		super(message,e);
	}
}
