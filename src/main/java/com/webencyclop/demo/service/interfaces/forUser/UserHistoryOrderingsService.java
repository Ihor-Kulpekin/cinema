package com.webencyclop.demo.service.interfaces.forUser;

import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.model.forUser.User;

import java.util.List;

public interface UserHistoryOrderingsService {
    List<Ordering> findByUserId(User userId);
}
