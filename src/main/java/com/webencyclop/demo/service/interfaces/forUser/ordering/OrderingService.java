package com.webencyclop.demo.service.interfaces.forUser.ordering;

import com.webencyclop.demo.model.forUser.Ordering;

public interface OrderingService {
    void doOrdering(Ordering ordering);
    Ordering getOrderingById(int id);
}
