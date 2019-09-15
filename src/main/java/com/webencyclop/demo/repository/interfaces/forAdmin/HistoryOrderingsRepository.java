package com.webencyclop.demo.repository.interfaces.forAdmin;

import com.webencyclop.demo.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryOrderingsRepository extends JpaRepository<Ordering,Integer> {
}
