package com.webencyclop.demo.service.implementation;

import com.webencyclop.demo.model.MailMessage;
import com.webencyclop.demo.model.SmtpServerRule;
import com.webencyclop.demo.service.interfaces.EmailSenderService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.*;

//Need to find out how to test email sender

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailSenderServiceImplTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Rule
    public SmtpServerRule smtpServerRule = new SmtpServerRule(2525);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sendMail() {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom("support@demo.com");
        mailMessage.setTo("kulpekin20@gmail.com");
        mailMessage.setSubject("Complete Password Reset");
        mailMessage.setContent("Test");
        emailSenderService.sendMail(mailMessage);
        MimeMessage[] receivedMessages = smtpServerRule.getMessages();
        assertEquals(0,receivedMessages.length);
    }
}