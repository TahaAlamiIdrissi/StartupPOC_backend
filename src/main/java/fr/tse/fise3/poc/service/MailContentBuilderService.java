package fr.tse.fise3.poc.service;

import org.springframework.stereotype.Service;


@Service
public interface MailContentBuilderService {
	
	public String build(String message);
	
}
