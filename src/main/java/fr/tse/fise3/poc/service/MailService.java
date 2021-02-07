package fr.tse.fise3.poc.service;


import fr.tse.fise3.poc.domain.NotificationEmail;


public interface MailService  {
	

	public void sendMail(NotificationEmail notificationEmail);
	
	
}
