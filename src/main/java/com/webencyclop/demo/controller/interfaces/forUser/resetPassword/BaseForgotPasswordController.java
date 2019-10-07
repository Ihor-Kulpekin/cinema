package com.webencyclop.demo.controller.interfaces.forUser.resetPassword;

import com.webencyclop.demo.model.forUser.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface BaseForgotPasswordController {

    @GetMapping(value = "/forgot")
    ModelAndView showForgotPasswordPage(User user);

    @PostMapping(value = "/forgot")
    ModelAndView processForgotPassword(ModelAndView modelAndView,User user);

    @GetMapping(value = "/reset")
    ModelAndView showResetPasswordPage(ModelAndView modelAndView,
                                       @RequestParam("token") String token);

    @PostMapping(value = "/reset")
    ModelAndView setNewPassword(ModelAndView modelAndView, User user);
}
