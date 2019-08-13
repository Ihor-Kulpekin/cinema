package com.webencyclop.demo.service.interfaces;

import java.util.List;
import com.webencyclop.demo.model.Ordering;

public interface OrderingService {
    void doOrdering(Ordering ordering);
    List<Ordering> listOrdering();
    Ordering getOrderingById(int id);
}
