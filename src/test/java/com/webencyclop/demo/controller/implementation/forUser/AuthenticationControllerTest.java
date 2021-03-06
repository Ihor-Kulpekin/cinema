package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.model.forUser.Role;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.repository.interfaces.forUser.RoleRepository;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
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

import java.util.Collections;
import java.util.HashSet;

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Rollback
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private UserService userService ;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private View view;

    private MockMvc mockMvc;

    private User user;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(authenticationController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void loginTest() throws Exception {
        String urlStatus = "/login";
        MvcResult mvcResult = mockMvc.perform(get(urlStatus))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void registerTest() throws Exception {
        String urlStatus = "/register";
        MvcResult mvcResult = mockMvc.perform(get(urlStatus))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void homeTest() throws Exception {
        String urlStatus = "/home";
        user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        user.setEmail("ihor.kulpekin@gmail.com");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");
        doNothing().when(userService).saveUser(user);
        MvcResult mvcResult = mockMvc.perform(get(urlStatus))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void adminHomeTest() throws Exception {
        String urlStatus = "/admin";
        MvcResult mvcResult = mockMvc.perform(get(urlStatus))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void registerUserTest() throws Exception {
        String url = "/register";
        user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        user.setEmail("ihor.kulpekin@gmail.com");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");
        doNothing().when(userService).saveUser(user);
        mockMvc.perform(post(url)
                .param("id","1")
                .param("name","Ihor")
                .param("lastName","Kulpekin")
                .param("email","ihor.kulpekin@gmail.com")
                .param("password","123456789")
                .param("status","VERIFIED"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("register"))
                    .andExpect(model().attribute("user",instanceOf(User.class)))
                    .andExpect(model().attribute("user",hasProperty("id")))
                    .andExpect(model().attribute("user",hasProperty("name")))
                    .andExpect(model().attribute("user",hasProperty("lastName")))
                    .andExpect(model().attribute("user",hasProperty("email")))
                    .andExpect(model().attribute("user",hasProperty("password")))
                    .andExpect(model().attribute("user",hasProperty("status")));
        ArgumentCaptor<User> boundUser = ArgumentCaptor.forClass(User.class);
        verify(userService).saveUser(boundUser.capture());
        assertEquals(user.getId(),boundUser.getValue().getId());
    }

    @Test
    public void logoutTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/logout")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }
}