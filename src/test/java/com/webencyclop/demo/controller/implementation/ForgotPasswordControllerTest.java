package com.webencyclop.demo.controller.implementation;

import com.webencyclop.demo.model.ConfirmationToken;
import com.webencyclop.demo.model.MailMessage;
import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.interfaces.RoleRepository;
import com.webencyclop.demo.service.interfaces.ConfirmationTokenService;
import com.webencyclop.demo.service.interfaces.EmailSenderService;
import com.webencyclop.demo.service.interfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


//There is an error in processForgotPasswordTest() method

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class ForgotPasswordControllerTest {

    @InjectMocks
    private ForgotPasswordController forgotPasswordController;

    @Mock
    private UserService userService;

    @Mock
    private EmailSenderService emailSenderService;

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Mock
    private View view;

    @Mock
    private RoleRepository roleRepository;

    private MockMvc mockMvc;

    private ConfirmationToken confirmationToken;

    private User user;

    private MailMessage mailMessage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(forgotPasswordController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void showForgotPasswordPageTest() throws Exception {
        String url = "/forgot";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("forgotPassword"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    //There is an error in execution test
    @Test
    public void processForgotPasswordTest() throws Exception {
        String url = "/forgot";
        user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        user.setEmail("ihor.kulpekin@gmail.com");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");
        doNothing().when(userService).saveUser(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        doNothing().when(confirmationTokenService).save(confirmationToken);

        mailMessage = new MailMessage();
        mailMessage.setFrom("support@demo.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Password Reset");
        mailMessage.setContent("To complete the password reset process, please click here: "
                +"http://localhost:8080/reset?token="+confirmationToken.getConfirmationToken());

        mockMvc.perform(post(url)
        .param("from",mailMessage.getFrom())
        .param("to",mailMessage.getTo())
        .param("subject",mailMessage.getSubject())
        .param("content",mailMessage.getContent()))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("mailMessage",instanceOf(MailMessage.class)))
                .andExpect(model().attribute("mailMessage",hasProperty("from")))
                .andExpect(model().attribute("mailMessage",hasProperty("to")))
                .andExpect(model().attribute("mailMessage",hasProperty("subject")))
                .andExpect(model().attribute("mailMessage",hasProperty("content")));

        ArgumentCaptor<MailMessage> boundMailMessage = ArgumentCaptor.forClass(MailMessage.class);
        verify(emailSenderService).sendMail(boundMailMessage.capture());
        assertEquals(mailMessage.getTo(),boundMailMessage.getValue().getTo());
    }

    @Test
    public void showResetPasswordPageTest() throws Exception {
        String url = "/reset";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void setNewPasswordTest() {
    }
}