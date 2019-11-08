package com.webencyclop.demo.controller.jsonResponse_Request.impl;

import com.webencyclop.demo.controller.jsonResponse_Request.interfaces.BaseJsonUserController;
import com.webencyclop.demo.convertorJson.BaseJsonConvector;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class JsonUserController implements BaseJsonUserController {

    private BaseJsonConvector baseJsonConvector;

    private final UserService userService;

    @Autowired
    public JsonUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getAllUser() throws JSONException, IllegalAccessException {
        List<User> userList = userService.getAllUser();
        return baseJsonConvector.ListToJson(userList);
    }
}
