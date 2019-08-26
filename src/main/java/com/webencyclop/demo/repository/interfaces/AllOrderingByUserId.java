package com.webencyclop.demo.repository.interfaces;

import com.webencyclop.demo.model.Ordering;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllOrderingByUserId {

    List<Ordering> getAllOrderingByUserId(int id);

}
