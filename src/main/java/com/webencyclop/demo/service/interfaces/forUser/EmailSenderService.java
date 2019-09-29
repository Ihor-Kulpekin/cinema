package com.webencyclop.demo.service.interfaces.forUser;

import com.webencyclop.demo.model.MailMessage;

public interface EmailSenderService {
    void sendMail(MailMessage mailMessage);
}
