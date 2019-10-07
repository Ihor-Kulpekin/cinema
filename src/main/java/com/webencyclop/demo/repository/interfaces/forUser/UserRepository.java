package com.webencyclop.demo.repository.interfaces.forUser;

import com.webencyclop.demo.model.forUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
