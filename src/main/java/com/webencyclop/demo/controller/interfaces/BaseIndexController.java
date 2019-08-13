package com.webencyclop.demo.controller.interfaces;

import org.apache.commons.httpclient.URIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

public interface BaseIndexController {

    @GetMapping("/")
    ModelAndView showIndexPage();
}
