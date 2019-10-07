package com.webencyclop.demo.controller.implementation.forUser.messaging;

import com.webencyclop.demo.controller.interfaces.forUser.messaging.BaseMessageDeleteAndGetMessageController;
import com.webencyclop.demo.service.interfaces.forUser.messaging.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageDeleteAndGetMessageController implements BaseMessageDeleteAndGetMessageController {

    private final SmsService smsService;

    @Autowired
    public MessageDeleteAndGetMessageController(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String deleteMessage(int id) {
        smsService.removeSMS(id);
        return "messages";
    }

    @Override
    public ModelAndView detailsMessage(int id) {
        ModelAndView modelAndView = new ModelAndView("detailsMessage");
        smsService.getSMSById(id);
        return modelAndView;
    }
}
