package de.raphaelkunis.springbootmysql.springboottest;

import de.raphaelkunis.springbootmysql.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 * Test the repository and service without web frontend
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Disabled
public class RepositoryTest {

    @Test
    public void emptyTest() { assertTrue(true); }

    // TODO: test tehe repository
}
