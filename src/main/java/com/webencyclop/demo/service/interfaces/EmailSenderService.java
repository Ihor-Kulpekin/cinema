package com.webencyclop.demo.service.interfaces;

import com.webencyclop.demo.model.MailMessage;

public interface EmailSenderService {
    void sendMail(MailMessage mailMessage);
}
