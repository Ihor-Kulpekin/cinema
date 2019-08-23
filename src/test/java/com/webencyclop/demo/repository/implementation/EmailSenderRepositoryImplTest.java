package com.webencyclop.demo.repository.implementation;

import com.webencyclop.demo.model.ConfirmationToken;
import com.webencyclop.demo.model.MailMessage;
import com.webencyclop.demo.model.SmtpServerRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class EmailSenderRepositoryImplTest {

    @Autowired
    private EmailSenderRepositoryImpl senderRepository;

    @Rule
    public SmtpServerRule smtpServerRule = new SmtpServerRule(2525);

    @Test
    public void sendMail() {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom("support@demo.com");
        mailMessage.setTo("kulpekin20@gmail.com");
        mailMessage.setSubject("Complete Password Reset");
        mailMessage.setContent("Test");
        senderRepository.sendMail(mailMessage);
        MimeMessage[] receivedMessages = smtpServerRule.getMessages();
        assertEquals(0,receivedMessages.length);
    }
}