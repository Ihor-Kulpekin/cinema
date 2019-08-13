package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

public interface BaseOrderingController {

    @GetMapping("/home/buyTicket")
    String showPageOrdering();

    @PostMapping(value = "/home/buyTicket", produces = MediaType.APPLICATION_JSON_VALUE)
    ModelAndView buyTicket(@RequestBody Ordering ordering);

}
