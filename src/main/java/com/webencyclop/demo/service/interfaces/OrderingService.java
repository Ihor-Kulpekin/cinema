package com.webencyclop.demo.service.interfaces;

import com.webencyclop.demo.model.Ordering;

import java.util.List;

public interface OrderingService {
    void doOrdering(Ordering ordering);
    List<Ordering> listOrderings();
    Ordering getOrderingById(int id);
}
