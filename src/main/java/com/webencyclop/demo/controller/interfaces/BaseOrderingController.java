package com.webencyclop.demo.controller.interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BaseOrderingController {

    @GetMapping("/home/buyTicket/{id}")
    String showPageOrdering(@PathVariable int id, Authentication authentication);

}
