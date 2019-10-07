package com.webencyclop.demo.controller.implementation.forUser.messaging;

import com.webencyclop.demo.controller.interfaces.forUser.messaging.BaseSendMessageController;
import com.webencyclop.demo.model.messaging.Sms;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.service.interfaces.forUser.messaging.SmsService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class SendMessageController implements BaseSendMessageController {

    private final SmsService smsService;

    private final UserService userService;

    @Autowired
    public SendMessageController(SmsService smsService, UserService userService) {
        this.smsService = smsService;
        this.userService = userService;
    }


    @Override
    public ModelAndView showPageSendMessage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sendMessage");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());
        Sms sms = new Sms();
        sms.setDateGettingMessage(new Date());
        sms.setFroms(loggedUser.getEmail());
        modelAndView.addObject("sms",sms);
        return modelAndView;
    }

    @Override
    public ModelAndView sentMessage(Sms sms, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        // we will save the message if, no binding errors
        else {
            smsService.sentAndSaveSMS(sms);
            modelAndView.addObject("successMessage", "Message is sent successfully!");
        }
        modelAndView.addObject("sms", new Sms());
        modelAndView.setViewName("sendMessage");
        return modelAndView;
    }
}
