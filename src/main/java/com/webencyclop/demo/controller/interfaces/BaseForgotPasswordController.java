package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
