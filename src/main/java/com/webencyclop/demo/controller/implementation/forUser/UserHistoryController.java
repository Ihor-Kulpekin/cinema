package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.controller.interfaces.forUser.BaseUserHistoryController;
import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.interfaces.forUser.UserHistoryOrderingsService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserHistoryController implements BaseUserHistoryController {

    private final UserHistoryOrderingsService userHistoryOrderingsService;

    private final UserService userService;

    @Autowired
    public UserHistoryController(UserHistoryOrderingsService userHistoryOrderingsService, UserService userService) {
        this.userHistoryOrderingsService = userHistoryOrderingsService;
        this.userService = userService;
    }

    @Override
    public ModelAndView showPageUserHistoryOrdering() {
        ModelAndView modelAndView = new ModelAndView("showPageUserHistoryOrdering");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userId = userService.findUserByEmail(auth.getName());
        List<Ordering> listOrderingsByUserId = userHistoryOrderingsService.findByUserId(userId);
        modelAndView.addObject("listOrderingsByUserId",listOrderingsByUserId);
        return modelAndView;
    }
}
