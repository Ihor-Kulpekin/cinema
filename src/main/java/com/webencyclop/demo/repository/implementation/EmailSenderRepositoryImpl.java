package com.webencyclop.demo.repository.implementation;

import com.webencyclop.demo.model.MailMessage;
import com.webencyclop.demo.repository.interfaces.EmailSenderRepository;
import com.webencyclop.demo.settings_email.SimpleMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSenderRepositoryImpl implements EmailSenderRepository {

    private final SimpleMailSender simpleMailSender;

    public EmailSenderRepositoryImpl(SimpleMailSender simpleMailSender) {
        this.simpleMailSender = simpleMailSender;
    }



    @Override
    public void sendMail(final MailMessage mail) {
        simpleMailSender.sendEmail(mail);
    }
}
