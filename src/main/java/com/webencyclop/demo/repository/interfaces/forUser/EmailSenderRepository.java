package com.webencyclop.demo.repository.interfaces.forUser;


import com.webencyclop.demo.model.MailMessage;
import org.springframework.scheduling.annotation.Async;

public interface EmailSenderRepository {
    void sendMail(MailMessage mailMessage);
}
