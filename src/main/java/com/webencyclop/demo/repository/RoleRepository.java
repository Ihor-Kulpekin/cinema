package com.webencyclop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRole(String role);
}
