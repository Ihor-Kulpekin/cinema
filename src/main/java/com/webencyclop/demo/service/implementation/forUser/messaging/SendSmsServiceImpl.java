package com.webencyclop.demo.service.implementation.forUser.messaging;

import com.webencyclop.demo.model.messaging.Sms;
import com.webencyclop.demo.repository.interfaces.forUser.messaging.SendSmsRepository;
import com.webencyclop.demo.service.interfaces.forUser.messaging.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendSmsServiceImpl implements SendSmsService {

    private final SendSmsRepository sendSmsRepository;

    @Autowired
    public SendSmsServiceImpl(SendSmsRepository sendSmsRepository) {
        this.sendSmsRepository = sendSmsRepository;
    }

    @Override
    public List<Sms> findAllByFroms(String froms) {
        return sendSmsRepository.findAllByFroms(froms);
    }
}
