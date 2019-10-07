package com.webencyclop.demo.repository.interfaces.forUser.ordering;

import com.webencyclop.demo.model.forUser.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering,Integer> {
}
