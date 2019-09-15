package com.webencyclop.demo.repository.interfaces.forUser;

import com.webencyclop.demo.model.Ordering;

import java.util.List;

public interface UserHistoryOrderingsRepository {
    List<Ordering> userHistoryOrderings(int id);
}
