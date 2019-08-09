package com.webencyclop.demo.controller.implementation;

import com.webencyclop.demo.controller.interfaces.BaseOrderingController;
import com.webencyclop.demo.service.interfaces.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class OrderingController  {

    @Autowired
    private OrderingService orderingService;

//    @Override
//    public String showPageOrdering(int id, Authentication authentication) {
//        return null;
//    }
}
