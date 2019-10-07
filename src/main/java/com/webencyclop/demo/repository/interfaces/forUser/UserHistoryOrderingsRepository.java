package com.webencyclop.demo.repository.interfaces.forUser;

import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.model.forUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryOrderingsRepository extends JpaRepository<Ordering,Integer> {
    List<Ordering> findByUserId(User userId);
}
