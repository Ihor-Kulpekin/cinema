package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface BaseUserController {

    @GetMapping("/resetPassword")
    ModelAndView showPageResetPassword();

    @PostMapping("/resetPassword")
    String resetPassword(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    @GetMapping("/admin/listUsers")
    ModelAndView showPageListUsers();
}
