package fr.tse.fise3.poc.service.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import fr.tse.fise3.poc.service.MailContentBuilder;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilderImpl implements MailContentBuilder {
	private final TemplateEngine templateEngine;

	public String build(String message) {
		Context context = new Context();
		context.setVariable("message", message);
		return templateEngine.process("mailTemplate", context);
	}

}