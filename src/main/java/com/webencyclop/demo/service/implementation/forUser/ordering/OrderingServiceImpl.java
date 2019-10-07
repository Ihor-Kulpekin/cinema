package com.webencyclop.demo.service.implementation.forUser.ordering;

import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.repository.interfaces.forUser.ordering.OrderingRepository;
import com.webencyclop.demo.service.interfaces.forUser.ordering.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderingServiceImpl implements OrderingService {

    private final OrderingRepository orderingRepository;

    @Autowired
    public OrderingServiceImpl(OrderingRepository orderingRepository) {
        this.orderingRepository = orderingRepository;
    }

    @Override
    public void doOrdering(Ordering ordering) {
        orderingRepository.save(ordering);
    }

    @Override
    public Ordering getOrderingById(int id) {
        return orderingRepository.getOne(id);
    }
}
