package com.ptit.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaSenderService {
	
	@Autowired
	private JavaMailSender mailSender; 
	
	@Autowired
	private Environment env; 
	
	public void sendMailVerifyCode(String toEmail, String body) {
		String emailFrom = env.getProperty("spring.mail.username");
		SimpleMailMessage mail = new SimpleMailMessage(); 
		
		mail.setFrom(emailFrom);
		mail.setTo(toEmail);
		mail.setText(body);
		mail.setSubject("Xác minh mật khẩu hai bước tại Bookstore");
		mailSender.send(mail);
		
	}
}
