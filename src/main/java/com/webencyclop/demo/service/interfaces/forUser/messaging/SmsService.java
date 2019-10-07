package com.webencyclop.demo.service.interfaces.forUser.messaging;


import com.webencyclop.demo.model.messaging.Sms;

import java.util.List;

public interface SmsService {

    void sentAndSaveSMS(Sms sms);
    Sms getSMSById(int id);
    void removeSMS(int id);
    List<Sms> findSMSByTos(String emailUser);

}
