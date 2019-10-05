package com.webencyclop.demo.service.interfaces.forUser;


import java.util.List;
import com.webencyclop.demo.model.Sms;

public interface SmsService {

    void sentAndSaveSMS(Sms sms);
    Sms getSMSById(int id);
    void removeSMS(int id);
    List<Sms> findSMSByTos(String emailUser);

}
