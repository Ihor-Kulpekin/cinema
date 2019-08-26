package com.webencyclop.demo.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.model.User;

public interface UserService {

	 void saveUser(User user);

	 boolean isUserAlreadyPresent(User user);

	 List<User> getAllUser();

	 User findUserByEmail(String email);
}
