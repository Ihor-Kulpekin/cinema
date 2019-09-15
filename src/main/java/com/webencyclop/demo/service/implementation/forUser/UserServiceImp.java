package com.webencyclop.demo.service.implementation.forUser;

import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.interfaces.RoleRepository;
import com.webencyclop.demo.repository.interfaces.forUser.UserRepository;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		if(user!=null){
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}


	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}