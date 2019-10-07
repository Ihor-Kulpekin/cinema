package com.webencyclop.demo.controller.interfaces.admin.historyOrderings;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface BaseHistoryOrderingController {

    @GetMapping(value = "/admin/historyOrderings")
    ModelAndView showPageHistoryOrderings();

}
