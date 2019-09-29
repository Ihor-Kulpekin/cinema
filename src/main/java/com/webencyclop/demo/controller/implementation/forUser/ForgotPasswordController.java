package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.controller.interfaces.forUser.BaseForgotPasswordController;
import com.webencyclop.demo.model.ConfirmationToken;
import com.webencyclop.demo.model.MailMessage;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.interfaces.forUser.ConfirmationTokenService;
import com.webencyclop.demo.service.interfaces.forUser.EmailSenderService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgotPasswordController implements BaseForgotPasswordController {

    private final UserService userService;

    private final EmailSenderService emailSenderService;

    private final ConfirmationTokenService confirmationTokenService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ForgotPasswordController(UserService userService, EmailSenderService emailSenderService, ConfirmationTokenService confirmationTokenService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenService = confirmationTokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ModelAndView showForgotPasswordPage(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @Override
    public ModelAndView processForgotPassword(ModelAndView modelAndView,User user) {

        User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser!=null){
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
            confirmationTokenService.save(confirmationToken);
            MailMessage mailMessage = new MailMessage();
            mailMessage.setFrom("support@demo.com");
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset");
            mailMessage.setContent("To complete the password reset process, please click here: "
                    +"http://localhost:8080/reset?token="+confirmationToken.getConfirmationToken());
            emailSenderService.sendMail(mailMessage);

            modelAndView.setViewName("successForgotPassword");
        }else {
            modelAndView.addObject("errorMessage", "This email does not exist!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @Override
    public ModelAndView showResetPasswordPage(ModelAndView modelAndView, String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
        if(confirmationToken!=null){
            User user = userService.findUserByEmail(confirmationToken.getUser().getEmail());
            userService.saveUser(user);
            modelAndView.addObject("user",user);
            modelAndView.addObject("emailId",user.getEmail());
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.addObject("message","The link is invalid or broken");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @Override
    public ModelAndView setNewPassword(ModelAndView modelAndView, User user) {
        if(user.getEmail()!=null){
            User tokenUser = userService.findUserByEmail(user.getEmail());
            tokenUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.saveUser(tokenUser);
            modelAndView.addObject("message","Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("successResetPassword");
        }else {
            modelAndView.addObject("errorMessage","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}
