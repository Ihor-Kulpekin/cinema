package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.controller.interfaces.forUser.BaseUserController;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController implements BaseUserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ModelAndView showPageResetPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",new User());
        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }


    @Override
    public ModelAndView showPageListUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.getAllUser();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("listUsers");
        return modelAndView;
    }
}
