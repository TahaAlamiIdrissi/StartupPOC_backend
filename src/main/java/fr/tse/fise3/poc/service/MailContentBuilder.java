package fr.tse.fise3.poc.service;

import org.springframework.stereotype.Service;


@Service
public interface MailContentBuilder {
	
	public String build(String message);
	
}
