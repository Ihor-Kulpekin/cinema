package com.webencyclop.demo.controller.interfaces.forUser;

import com.webencyclop.demo.model.Ordering;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface BaseOrderingController {

    @GetMapping("/home/buyTicket")
    ModelAndView showPageOrdering();

    @PostMapping(value = "/home/buyTicket")
    ModelAndView buyTicket(@Valid Ordering ordering);

}
