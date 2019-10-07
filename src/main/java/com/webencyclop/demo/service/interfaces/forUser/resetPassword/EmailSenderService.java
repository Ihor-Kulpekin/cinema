package com.webencyclop.demo.service.interfaces.forUser.resetPassword;

import com.webencyclop.demo.model.resetPassword.MailMessage;

public interface EmailSenderService {
    void sendMail(MailMessage mailMessage);
}
