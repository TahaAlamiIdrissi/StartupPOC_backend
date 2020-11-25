package io.alami.idrissi.achieve.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.alami.idrissi.achieve.domain.NotificationEmail;
import io.alami.idrissi.achieve.exception.AchieveNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	private final static String FROM="taha.alami@achieve.io";
	private final JavaMailSender mailSender;
	
	@Async
	void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(FROM);
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(notificationEmail.getBody());
		};
		
		try {
			mailSender.send(messagePreparator);
			log.info("Activation email has been sent");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured when sending email | "+e);
			throw new AchieveNotFoundException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
		}
	}
}
