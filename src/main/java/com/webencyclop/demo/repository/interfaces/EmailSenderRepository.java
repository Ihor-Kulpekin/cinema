package com.webencyclop.demo.repository.interfaces;


import com.webencyclop.demo.model.MailMessage;
import org.springframework.scheduling.annotation.Async;

public interface EmailSenderRepository {
    void sendMail(MailMessage mailMessage);
}
