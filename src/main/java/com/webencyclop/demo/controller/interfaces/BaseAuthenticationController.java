package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.User;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface BaseAuthenticationController {
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
     ModelAndView login();

    @RequestMapping(value = "/register", method = RequestMethod.GET)
     ModelAndView register();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
     ModelAndView home();

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
     ModelAndView adminHome();

    @RequestMapping(value="/register", method=RequestMethod.POST)
     ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap);

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    String logout(HttpServletRequest request, HttpServletResponse response);
}
