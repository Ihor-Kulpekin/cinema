package com.webencyclop.demo.service.interfaces.forUser;

import com.webencyclop.demo.model.Ordering;

public interface OrderingService {
    void doOrdering(Ordering ordering);
    Ordering getOrderingById(int id);
}
