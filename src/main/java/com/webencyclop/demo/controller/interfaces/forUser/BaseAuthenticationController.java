package com.webencyclop.demo.controller.interfaces.forUser;

import com.webencyclop.demo.model.User;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface BaseAuthenticationController {

     @GetMapping("/login")
     ModelAndView login();

     @GetMapping("/register")
     ModelAndView register();

     @GetMapping("/home")
     ModelAndView home();

     @GetMapping("/admin")
     ModelAndView adminHome();

     @PostMapping("/register")
     ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap);

     @GetMapping("/logout")
     String logout(HttpServletRequest request, HttpServletResponse response);
}
