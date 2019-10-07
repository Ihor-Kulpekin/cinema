package com.webencyclop.demo.controller.interfaces.forUser.messaging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface BaseSendSmsController {

    @GetMapping("/home/sendMessageBuCurrentUser")
    ModelAndView showPageSentMessage();

}
