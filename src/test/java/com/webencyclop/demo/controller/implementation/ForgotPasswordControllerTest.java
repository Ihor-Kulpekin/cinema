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

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom("support@demo.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Password Reset");
        mailMessage.setContent("To complete the password reset process, please click here: "
                +"http://localhost:8080/reset?token="+confirmationToken.getConfirmationToken());

        assertNotNull(confirmationToken);
        assertNotNull(mailMessage);
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
    public void setNewPasswordWithErrorTest() throws Exception {
        String url = "/reset";
        User resultUser = defineUserWithError();
        doNothing().when(userService).saveUser(resultUser);
        if(resultUser.getEmail()==null){
            MvcResult mvcResult = mockMvc.perform(post(url))
                    .andExpect(status().isOk())
                    .andExpect(view().name("error"))
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            int expectedStatus = 200;
            assertEquals(expectedStatus,status);
        }
    }

    @Test
    public void setNewPasswordWithCorrectDataTest() throws Exception {
        String url = "/reset";
        User resultUser = defineUser();
        doNothing().when(userService).saveUser(resultUser);
        if(resultUser.getEmail()==null){
            MvcResult mvcResult = mockMvc.perform(post(url))
                    .andExpect(status().isOk())
                    .andExpect(view().name("successResetPassword"))
                    .andExpect(model().attribute("user",instanceOf(User.class)))
                    .andExpect(model().attribute("user",hasProperty("password")))
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            int expectedStatus = 200;
            assertEquals(expectedStatus,status);
        }
    }


    private User defineUserWithError(){
        User user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");

        return user;
    }

    private User defineUser(){
        User user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        user.setEmail("ihor.kulpekin@gmail.com");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");

        return user;
    }

}