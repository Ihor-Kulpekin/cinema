package com.webencyclop.demo.service.implementation;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.repository.interfaces.OrderingRepository;
import com.webencyclop.demo.service.interfaces.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderingServiceImpl implements OrderingService {

    @Autowired
    private OrderingRepository orderingRepository;

    @Override
    public void doOrdering(Ordering ordering) {
        orderingRepository.save(ordering);
    }

    @Override
    public List<Ordering> listOrderings() {
        return orderingRepository.findAll();
    }

    @Override
    public Ordering getOrderingById(int id) {
        return orderingRepository.getOne(id);
    }
}
