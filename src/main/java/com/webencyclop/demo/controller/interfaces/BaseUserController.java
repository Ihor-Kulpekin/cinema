package com.webencyclop.demo.controller.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface BaseUserController {

    @GetMapping("/resetPassword")
    ModelAndView showPageResetPassword();


    @GetMapping("/admin/listUsers")
    ModelAndView showPageListUsers();

}
