package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.DemoApplication;
import com.webencyclop.demo.model.Sms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;



@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MessageControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }



    @Test
    public void messagePageTest() throws Exception {
        String url = "/home/messages";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Sms> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> responseEntity =restTemplate.exchange(getRootUrl()+url, HttpMethod.GET,
                entity,String.class);
        assertNotNull(responseEntity.getBody());
    }


}