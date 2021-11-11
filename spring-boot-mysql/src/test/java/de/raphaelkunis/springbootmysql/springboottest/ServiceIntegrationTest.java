package de.raphaelkunis.springbootmysql.springboottest;

import de.raphaelkunis.springbootmysql.user.User;
import de.raphaelkunis.springbootmysql.user.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Test the repository and service without web frontend
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
//@Disabled
public class ServiceIntegrationTest {

    // UserService service = new UserServiceImpl();     does not work -> Validator is not initialized correctly (null) -> Autowired problem
    @Autowired
    UserService service;

    @Test
    public void whenValidInput_thenCreateUser() {
        User bob = new User("bob", "bob@home.de");
        String result = service.addNewUser(bob.getName(), bob.getEmail());
        assertEquals(result, "Saved");
    }

    @Test
    public void whenValidInput_thenCreateUserAndCheck() {
        User max = new User("max", "max@home.de");
        service.addNewUser(max.getName(), max.getEmail());
        User found = null;
        for (User user : service.getAllUsers()) {
            if (user.getName().equals(max.getName()) && user.getEmail().equals(max.getEmail())) { found = user; }
        }
        assertNotNull(found);
    }

    @Test
    public void whenInValidInput_thenReturnError() {
        User bob = new User("bob", "thisIsNoMail");
        String result = service.addNewUser(bob.getName(), bob.getEmail());
        assertTrue(result.startsWith("Validation Error: "));
    }
}
