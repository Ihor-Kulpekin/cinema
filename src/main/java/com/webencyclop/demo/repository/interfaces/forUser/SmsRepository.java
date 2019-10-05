package com.webencyclop.demo.repository.interfaces.forUser;

import com.webencyclop.demo.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsRepository extends JpaRepository<Sms,Integer> {
    List<Sms> findSMSByTos(String emailUser);
}
