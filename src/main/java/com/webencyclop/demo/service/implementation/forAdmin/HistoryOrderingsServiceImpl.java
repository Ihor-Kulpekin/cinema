package com.webencyclop.demo.service.implementation.forAdmin;

import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.repository.interfaces.forAdmin.HistoryOrderingsRepository;
import com.webencyclop.demo.service.interfaces.forAdmin.HistoryOrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryOrderingsServiceImpl implements HistoryOrderingService {

    private final HistoryOrderingsRepository historyOrderingsRepository;

    @Autowired
    public HistoryOrderingsServiceImpl(HistoryOrderingsRepository historyOrderingsRepository) {
        this.historyOrderingsRepository = historyOrderingsRepository;
    }

    @Override
    public List<Ordering> listHistoryOrderings() {
        return historyOrderingsRepository.findAll();
    }
}
