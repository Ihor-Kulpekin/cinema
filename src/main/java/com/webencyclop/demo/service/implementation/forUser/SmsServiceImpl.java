package com.webencyclop.demo.service.implementation.forUser;

import com.webencyclop.demo.model.Sms;
import com.webencyclop.demo.repository.interfaces.forUser.SmsRepository;
import com.webencyclop.demo.service.interfaces.forUser.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {

    private final SmsRepository smsRepository;

    @Autowired
    public SmsServiceImpl(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Override
    public void sentAndSaveSMS(Sms sms) {
        smsRepository.save(sms);
    }

    @Override
    public Sms getSMSById(int id) {
        return smsRepository.getOne(id);
    }

    @Override
    public void removeSMS(int id) {
        smsRepository.deleteById(id);
    }

    @Override
    public List<Sms> findSMSByTos(String emailUser) {
        return smsRepository.findSMSByTos(emailUser);
    }
}
