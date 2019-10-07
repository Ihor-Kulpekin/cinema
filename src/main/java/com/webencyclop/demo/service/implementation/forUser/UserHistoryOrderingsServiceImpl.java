package com.webencyclop.demo.service.implementation.forUser;

import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.repository.interfaces.forUser.UserHistoryOrderingsRepository;
import com.webencyclop.demo.repository.interfaces.forUser.UserRepository;
import com.webencyclop.demo.service.interfaces.forUser.UserHistoryOrderingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryOrderingsServiceImpl implements UserHistoryOrderingsService {

    private final UserHistoryOrderingsRepository userHistoryOrderingsRepository;


    @Autowired
    public UserHistoryOrderingsServiceImpl(UserHistoryOrderingsRepository userHistoryOrderingsRepository, UserRepository userRepository) {
        this.userHistoryOrderingsRepository = userHistoryOrderingsRepository;
    }


    @Override
    public List<Ordering> findByUserId(User userId) {
        return userHistoryOrderingsRepository.findByUserId(userId);
    }
}
