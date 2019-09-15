package com.webencyclop.demo.service.implementation.forAdmin;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.repository.interfaces.forAdmin.HistoryOrderingsRepository;
import com.webencyclop.demo.service.interfaces.forAdmin.HistoryOrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryOrderingsServiceImpl implements HistoryOrderingService {

    @Autowired
    private HistoryOrderingsRepository historyOrderingsRepository;

    @Override
    public List<Ordering> listHistoryOrderings() {
        return historyOrderingsRepository.findAll();
    }
}
