package com.webencyclop.demo.controller.implementation;

import java.util.List;
import com.webencyclop.demo.controller.interfaces.BaseUserController;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.interfaces.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController implements BaseUserController {

    @Autowired
    private UserService userService;

    @Override
    public ModelAndView showPageResetPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",new User());
        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    @Override
    public String resetPassword(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String email = httpServletRequest.getParameter("email");
        String newPassword = httpServletRequest.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(newPassword);
        userService.updatePassword(user);
        return "redirect:/login";
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
