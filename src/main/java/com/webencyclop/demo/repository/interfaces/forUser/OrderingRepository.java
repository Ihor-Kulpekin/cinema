package com.webencyclop.demo.repository.interfaces.forUser;

import com.webencyclop.demo.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering,Integer> {
}
