package com.webencyclop.demo.service.implementation;

import com.webencyclop.demo.model.MasterCard;
import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.interfaces.RoleRepository;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

@Rollback
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImpTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    private MasterCard expectedMasterCard;
    private User expectedUser;

    @Before
    public void setUp() throws Exception {
        expectedUser = new User();
        expectedMasterCard = new MasterCard();
        expectedMasterCard.setId(1);
        expectedMasterCard.setMoney(100);
        expectedMasterCard.setNumberCard("123456789");
        expectedUser.setId(1);
        expectedUser.setPassword("1234567");
        expectedUser.setEmail("ihor.kulpekin@gmail.com");
        expectedUser.setStatus("dasd");
        expectedUser.setMasterCard(expectedMasterCard);
        expectedUser.setName("Ihor");
        expectedUser.setLastName("Kulpekin");
        Role userRole = roleRepository.findByRole("SITE_USER");
        expectedUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    }

    @Test
    public void saveUser() {
        userService.saveUser(expectedUser);
        User resultUser = userService.findUserByEmail("ihor.kulpekin@gmail.com");
        assertNotNull(resultUser);
    }

    @Test
    public void isUserAlreadyPresent() {
        userService.saveUser(expectedUser);
        User resultUser = userService.findUserByEmail("ihor.kulpekin@gmail.com");
        boolean isUserExist = userService.isUserAlreadyPresent(resultUser);
        assertTrue(isUserExist);
    }

    @Test
    public void getAllUser() {
        int sizeListUser = userService.getAllUser().size();
        assertEquals(sizeListUser,userService.getAllUser().size());
    }

    @Test
    public void findUserByEmail() {
        userService.saveUser(expectedUser);
        User resultUser = userService.findUserByEmail("ihor.kulpekin@gmail.com");
        assertNotNull(resultUser);
    }
}
