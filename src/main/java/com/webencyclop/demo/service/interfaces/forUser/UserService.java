package com.webencyclop.demo.service.interfaces.forUser;

import com.webencyclop.demo.model.forUser.User;

import java.util.List;

public interface UserService {

	 void saveUser(User user);

	 boolean isUserAlreadyPresent(User user);

	 List<User> getAllUser();

	 User findUserByEmail(String email);
}
