package com.webencyclop.demo.controller.implementation;

import com.webencyclop.demo.controller.interfaces.BaseForgotPasswordController;
import com.webencyclop.demo.model.ConfirmationToken;
import com.webencyclop.demo.model.MailMessage;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.interfaces.ConfirmationTokenService;
import com.webencyclop.demo.service.interfaces.EmailSenderService;
import com.webencyclop.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class ForgotPasswordController implements BaseForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
