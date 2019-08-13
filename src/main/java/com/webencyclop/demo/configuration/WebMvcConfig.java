package com.webencyclop.demo.configuration;

import com.webencyclop.demo.settings_email.EmailSetting;
import com.webencyclop.demo.settings_email.constants.EmailConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	private final EmailSetting emailSetting;

	@Autowired
	public WebMvcConfig(EmailSetting emailSetting) {
		this.emailSetting = emailSetting;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPassword("201199ss");
		javaMailSender.setUsername("kulpekin20@gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setProtocol("smtp");
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailSender;
	}


	private Properties getMailProperties(){
		Properties props = new Properties();
		props.put(EmailConstant.SMTP_AUTH, true);
		props.put(EmailConstant.SMTP_STARTLS, true);
		return props;
	}
}
