package com.webencyclop.demo.controller.implementation.admin;

import com.webencyclop.demo.controller.interfaces.admin.BaseHistoryOrderingController;
import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.service.interfaces.forAdmin.HistoryOrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HistoryOrderingController implements BaseHistoryOrderingController {

    @Autowired
    private HistoryOrderingService historyOrderingService;

    @Override
    public ModelAndView showPageHistoryOrderings() {
        ModelAndView modelAndView = new ModelAndView("historyOrderings");
        List<Ordering> listOrderings = historyOrderingService.listHistoryOrderings();
        modelAndView.addObject("listOrderings",listOrderings);
        return modelAndView;
    }
}
