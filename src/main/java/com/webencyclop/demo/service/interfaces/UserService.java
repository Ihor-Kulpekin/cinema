package com.webencyclop.demo.service.interfaces;

import java.util.List;
import com.webencyclop.demo.model.User;

public interface UserService {

	 void saveUser(User user);

	 boolean isUserAlreadyPresent(User user);

	 List<User> getAllUser();

	 String updatePassword(User user);
}
