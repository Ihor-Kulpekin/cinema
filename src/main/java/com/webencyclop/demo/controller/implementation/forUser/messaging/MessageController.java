package com.webencyclop.demo.controller.implementation.forUser.messaging;

import com.webencyclop.demo.controller.interfaces.forUser.messaging.BaseMessageController;
import com.webencyclop.demo.model.messaging.Sms;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.service.interfaces.forUser.messaging.SmsService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MessageController implements BaseMessageController {

    private final SmsService smsService;

    private final UserService userService;

    @Autowired
    public MessageController(SmsService smsService, UserService userService) {
        this.smsService = smsService;
        this.userService = userService;
    }

    @Override
    public ModelAndView messagePage() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());
        List<Sms> smsList = smsService.findSMSByTos(loggedUser.getEmail());
        modelAndView.addObject("listMessage",smsList);
        modelAndView.setViewName("messages");
        return modelAndView;
    }
}
