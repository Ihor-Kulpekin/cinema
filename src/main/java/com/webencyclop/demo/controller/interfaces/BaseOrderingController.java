package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface BaseOrderingController {

    @GetMapping("/home/buyTicket")
    ModelAndView showPageOrdering();

    @PostMapping(value = "/home/buyTicket")
    ModelAndView buyTicket(@Valid Ordering ordering);

    @GetMapping(value = "/admin/listOrderings")
    ModelAndView showPageListOrderings();

}
