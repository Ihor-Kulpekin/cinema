package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.service.interfaces.forUser.SmsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Rollback
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class SendMessageControllerTest {

    @InjectMocks
    private SendMessageController sendMessageController;

    @Mock
    private SmsService smsService;

    @Mock
    private View view;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(sendMessageController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void showPageSendMessage() throws Exception {
        String url = "/home/sendMessage";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("sendMessage"))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,resultStatus);
    }

    @Test
    public void sentMessage() {
    }
}