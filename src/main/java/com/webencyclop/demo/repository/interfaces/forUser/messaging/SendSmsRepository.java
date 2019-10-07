package com.webencyclop.demo.repository.interfaces.forUser.messaging;

import com.webencyclop.demo.model.messaging.Sms;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendSmsRepository extends SmsRepository {

    List<Sms> findAllByFroms(String froms);

}
