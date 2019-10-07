package com.webencyclop.demo.repository.implementation;

import com.webencyclop.demo.model.resetPassword.MailMessage;
import com.webencyclop.demo.repository.interfaces.forUser.resetPassword.EmailSenderRepository;
import com.webencyclop.demo.settings_email.SimpleMailSender;
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
