package com.webencyclop.demo.repository.interfaces.forUser.resetPassword;


import com.webencyclop.demo.model.resetPassword.MailMessage;

public interface EmailSenderRepository {
    void sendMail(MailMessage mailMessage);
}
