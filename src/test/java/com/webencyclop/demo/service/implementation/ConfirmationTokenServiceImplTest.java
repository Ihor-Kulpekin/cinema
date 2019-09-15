package com.webencyclop.demo.service.implementation;

import com.webencyclop.demo.model.ConfirmationToken;
import com.webencyclop.demo.model.MasterCard;
import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.interfaces.RoleRepository;
import com.webencyclop.demo.service.interfaces.ConfirmationTokenService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class ConfirmationTokenServiceImplTest {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService userService;

    private ConfirmationToken confirmationToken;

    private User expectedUser;

    private MasterCard expectedMasterCard;

    @Before
    public void setUp() throws Exception {
        confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmationToken("dasdasg4wdw45wwd");
        confirmationToken.setCreatedDate(new Date());
        confirmationToken.setTokenid(1);
        expectedUser = new User();
        expectedUser.setPassword(encoder.encode("1234567"));
        expectedUser.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        expectedUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        expectedUser.setLastName("Kulpekin");
        expectedUser.setEmail("kulpekin12@gmail.com");
        expectedMasterCard = new MasterCard();
        expectedMasterCard.setNumberCard("123456");
        expectedUser.setMasterCard(expectedMasterCard);
        expectedUser.setName("Ihor");
        confirmationToken.setUser(expectedUser);
    }

    @Test
    public void findByConfirmationToken() {
        userService.saveUser(expectedUser);
        confirmationTokenService.save(confirmationToken);
        ConfirmationToken resultConfirmationToken = confirmationTokenService.findByConfirmationToken("dasdasg4wdw45wwd");
        assertNotNull(resultConfirmationToken);
    }

    @Test
    public void save() {
        userService.saveUser(expectedUser);
        confirmationTokenService.save(confirmationToken);
        ConfirmationToken resultConfirmationToken = confirmationTokenService.findByConfirmationToken("dasdasg4wdw45wwd");
        assertEquals(confirmationToken.getConfirmationToken(),resultConfirmationToken.getConfirmationToken());
    }
}