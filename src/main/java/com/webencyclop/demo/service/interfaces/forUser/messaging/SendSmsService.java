package com.webencyclop.demo.service.interfaces.forUser.messaging;

import com.webencyclop.demo.model.messaging.Sms;

import java.util.List;

public interface SendSmsService {

    List<Sms> findAllByFroms(String froms);

}
