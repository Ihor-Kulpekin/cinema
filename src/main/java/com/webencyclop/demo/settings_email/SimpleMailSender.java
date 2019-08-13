package com.webencyclop.demo.settings_email;

import com.webencyclop.demo.model.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;

@Component
public class SimpleMailSender {

    private final JavaMailSender mailSender;
    private final EmailSetting emailSetting;

    @Autowired
    public SimpleMailSender(JavaMailSender mailSender, EmailSetting emailSetting) {
        this.mailSender = mailSender;
        this.emailSetting = emailSetting;
    }


    /**
     * compose and send email to the given receiver
     *
     * @param mailMessage containing the receiver email information and content
     */
    public void sendEmail(MailMessage mailMessage) {
        MimeMessagePreparator preparator = mimeMessage -> {
            InternetAddress senderAddress = new InternetAddress("test@gmail.com", "Test Test");
            InternetAddress receiverAddress = new InternetAddress(mailMessage.getTo(), "Ihor");
            mimeMessage.setSender(senderAddress);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(senderAddress);
            helper.setTo(receiverAddress);
            helper.setSubject(mailMessage.getSubject());
            helper.setText(mailMessage.getContent());
        };
        mailSender.send(preparator);
    }
}
