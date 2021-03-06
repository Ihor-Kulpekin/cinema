package com.webencyclop.demo.controller.interfaces.forUser.messaging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@FunctionalInterface
public interface BaseMessageController {
    @GetMapping(value = "/home/messages")
    ModelAndView messagePage();
}
