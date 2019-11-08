package com.webencyclop.demo.controller.implementation.admin.historyOrderings;

import com.webencyclop.demo.controller.interfaces.admin.historyOrderings.BaseHistoryOrderingController;
import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.service.interfaces.forAdmin.HistoryOrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HistoryOrderingController implements BaseHistoryOrderingController {

    private final HistoryOrderingService historyOrderingService;

    @Autowired
    public HistoryOrderingController(HistoryOrderingService historyOrderingService) {
        this.historyOrderingService = historyOrderingService;
    }

    @Override
    public ModelAndView historyOrderings() {
        ModelAndView modelAndView = new ModelAndView("historyOrderings");
        List<Ordering> listOrderings = historyOrderingService.listHistoryOrderings();
        modelAndView.addObject("listOrderings",listOrderings);
        return modelAndView;
    }
}
