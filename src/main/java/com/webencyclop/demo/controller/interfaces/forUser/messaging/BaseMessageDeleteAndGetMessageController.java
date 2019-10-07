package com.webencyclop.demo.controller.interfaces.forUser.messaging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

public interface BaseMessageDeleteAndGetMessageController {

    @GetMapping("/home/messages/{id}")
    String deleteMessage(@PathVariable int id);

    @GetMapping("/home/messages/details/{id}")
    ModelAndView detailsMessage(@PathVariable int id);

}
