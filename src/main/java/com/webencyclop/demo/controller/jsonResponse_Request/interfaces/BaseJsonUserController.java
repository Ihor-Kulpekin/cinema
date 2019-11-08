package com.webencyclop.demo.controller.jsonResponse_Request.interfaces;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;

public interface BaseJsonUserController {

    @GetMapping(name = "/jsonUser")
    String getAllUser() throws JSONException, IllegalAccessException;

}
