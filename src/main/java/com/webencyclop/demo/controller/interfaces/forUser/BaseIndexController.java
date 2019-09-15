package com.webencyclop.demo.controller.interfaces.forUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface BaseIndexController {

    @GetMapping("/")
    ModelAndView showIndexPage();
}
