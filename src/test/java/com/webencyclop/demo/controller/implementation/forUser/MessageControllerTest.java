package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.Sms;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.interfaces.forUser.RoleRepository;
import com.webencyclop.demo.service.interfaces.forUser.SmsService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//There is the error

@Rollback
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private UserService userService;

    @Mock
    private SmsService smsService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private View view;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(messageController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void messagePageTest() throws Exception {
        String url = "/home/messages";
        Sms sms = defineSms();
        User user = defineUser();
        doNothing().when(userService).saveUser(user);
        List<Sms> smsList = smsService.findSMSByTos(user.getEmail());
        smsList.add(sms);
        Mockito.when(smsService.findSMSByTos(user.getEmail())).thenReturn(smsList);
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(model().attribute("sms",smsList.get(0)))
                .andExpect(view().name("messages"))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,resultStatus);
    }

    private Sms defineSms(){
        Sms sms = new Sms();
        sms.setId(1);
        sms.setTos("ihor.kulpekin@gmail.com");
        sms.setContents("contents");
        sms.setSubjects("subjects");
        sms.setFroms("froms");
        sms.setDateGettingMessage(new Date());

        return sms;
    }

    private User defineUser(){
        User user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        user.setEmail("ihor.kulpekin@gmail.com");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");

        return user;
    }
}