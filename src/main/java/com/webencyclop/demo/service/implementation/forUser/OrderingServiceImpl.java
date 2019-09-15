package com.webencyclop.demo.service.implementation.forUser;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.repository.interfaces.forUser.OrderingRepository;
import com.webencyclop.demo.service.interfaces.forUser.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderingServiceImpl implements OrderingService {

    @Autowired
    private OrderingRepository orderingRepository;

    @Override
    public void doOrdering(Ordering ordering) {
        orderingRepository.save(ordering);
    }

    @Override
    public Ordering getOrderingById(int id) {
        return orderingRepository.getOne(id);
    }
}
