package com.webencyclop.demo.service.implementation.forUser;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.interfaces.forUser.UserHistoryOrderingsRepository;
import com.webencyclop.demo.repository.interfaces.forUser.UserRepository;
import com.webencyclop.demo.service.interfaces.forUser.UserHistoryOrderingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryOrderingsServiceImpl implements UserHistoryOrderingsService {

    private final UserHistoryOrderingsRepository userHistoryOrderingsRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserHistoryOrderingsServiceImpl(UserHistoryOrderingsRepository userHistoryOrderingsRepository, UserRepository userRepository) {
        this.userHistoryOrderingsRepository = userHistoryOrderingsRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Ordering> findByUserId(User userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userId = userRepository.findByEmail(auth.getName());
        return userHistoryOrderingsRepository.findByUserId(userId);
    }
}
