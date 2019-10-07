package com.webencyclop.demo.service.implementation.forUser;

import com.webencyclop.demo.model.messaging.Sms;
import com.webencyclop.demo.service.interfaces.forUser.messaging.SmsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@Transactional
@Rollback
@RunWith(SpringRunner.class)
public class SmsServiceImplTest {

    @Autowired
    private SmsService smsServiceInterface;


    private Sms expectedSMS;

    @Before
    public void setUp() {
        expectedSMS = new Sms();
        expectedSMS.setTos("tos");
        expectedSMS.setFroms("froms");
        expectedSMS.setContents("contents");
        expectedSMS.setSubjects("subjects");
        expectedSMS.setDateGettingMessage(new Date());
        smsServiceInterface.sentAndSaveSMS(expectedSMS);
    }

    @Test
    public void sentAndSaveSMSTest() {
        Sms sms = smsServiceInterface.getSMSById(expectedSMS.getId());
        assertEquals(expectedSMS,sms);
    }

    @Test
    public void getSMSByIdTest() {
        List<Sms> smsList = smsServiceInterface.findSMSByTos(expectedSMS.getTos());
        Sms smsFromList = smsList.get(0);
        Sms smsGotById = smsServiceInterface.getSMSById(smsFromList.getId());
        assertNotNull(smsGotById);
    }

    @Test
    public void removeSMSTest() {
        assertEquals(1L,smsServiceInterface.findSMSByTos(expectedSMS.getTos()).size());
        smsServiceInterface.removeSMS(expectedSMS.getId());
        assertEquals(0L,smsServiceInterface.findSMSByTos(expectedSMS.getTos()).size());
    }

    @Test
    public void findSMSByTosTest() {
        smsServiceInterface.sentAndSaveSMS(expectedSMS);
        int sizeList = smsServiceInterface.findSMSByTos(expectedSMS.getTos()).size();
        assertEquals(sizeList,smsServiceInterface.findSMSByTos(expectedSMS.getTos()).size());
    }
}