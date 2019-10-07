package com.webencyclop.demo.service.implementation.forUser.resetPassword;

import com.webencyclop.demo.model.resetPassword.MailMessage;
import com.webencyclop.demo.repository.interfaces.forUser.resetPassword.EmailSenderRepository;
import com.webencyclop.demo.service.interfaces.forUser.resetPassword.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private EmailSenderRepository emailSenderRepository;

    @Autowired
    public void setEmailSenderRepository(EmailSenderRepository emailSenderRepository) {
        this.emailSenderRepository = emailSenderRepository;
    }

    @Override
    public void sendMail(MailMessage mailMessage) {
        emailSenderRepository.sendMail(mailMessage);
    }
}
