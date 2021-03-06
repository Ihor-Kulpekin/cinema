package com.webencyclop.demo.repository.interfaces.forUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.forUser.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRole(String role);
}
