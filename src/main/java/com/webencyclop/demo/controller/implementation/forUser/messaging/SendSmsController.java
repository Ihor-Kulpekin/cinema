package com.webencyclop.demo.controller.implementation.forUser.messaging;

import com.webencyclop.demo.controller.interfaces.forUser.messaging.BaseSendSmsController;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.model.messaging.Sms;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import com.webencyclop.demo.service.interfaces.forUser.messaging.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SendSmsController implements BaseSendSmsController {

    private final SendSmsService sendSmsService;

    private final UserService userService;

    @Autowired
    public SendSmsController(SendSmsService sendSmsService, UserService userService) {
        this.sendSmsService = sendSmsService;
        this.userService = userService;
    }

    @Override
    public ModelAndView showPageSentMessage() {

        ModelAndView modelAndView = new ModelAndView("sentSmsByCurrentUser");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());

        List<Sms> smsListSendByCurrentUser = sendSmsService.findAllByFroms(loggedUser.getEmail());

        modelAndView.addObject("smsList",smsListSendByCurrentUser);

        return modelAndView;
    }
}
