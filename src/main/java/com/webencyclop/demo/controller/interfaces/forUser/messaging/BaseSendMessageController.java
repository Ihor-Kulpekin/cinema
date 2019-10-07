package com.webencyclop.demo.controller.interfaces.forUser.messaging;

import com.webencyclop.demo.model.messaging.Sms;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface BaseSendMessageController {

    @GetMapping("/home/sendMessage")
    ModelAndView showPageSendMessage();

    @PostMapping("/home/sendMessage")
    ModelAndView sentMessage(@Valid Sms sms, BindingResult bindingResult, ModelMap modelMap);

}
