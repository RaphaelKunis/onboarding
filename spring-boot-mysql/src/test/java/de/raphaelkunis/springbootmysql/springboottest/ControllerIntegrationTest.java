package de.raphaelkunis.springbootmysql.springboottest;

import de.raphaelkunis.springbootmysql.SpringbootmysqlApplication;
import de.raphaelkunis.springbootmysql.user.User;
import de.raphaelkunis.springbootmysql.user.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.raphaelkunis.springbootmysql.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment  = SpringBootTest.WebEnvironment.MOCK,classes = SpringbootmysqlApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
//@Disabled
public class ControllerIntegrationTest {
    // from https://www.baeldung.com/spring-boot-testing

    @Value("${springbootmysql.pwd_plain}")          // gets the property from application.properties
    private String pwd;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    /**
     * create a user object and store it into repository
     */
    private User createTestUser(String name) {
        User user = new User(name, new String(name+"@home.de"));
        repository.save(user);
        return user;
    }

    @Test
    @WithMockUser(username = "user", password = "38183996-f260-402f-afba-46e494fa7174" , roles = "USER")     // needed because we use spring boot security
    public void whenValidInput_thenCreateUser() throws IOException, Exception {
        User bob = createTestUser("bob");
        mvc.perform(post("/demo/add")
                .param("name", bob.getName())
                .param("email", bob.getEmail()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void whenValidInput_thenCreateUserAndCheck() throws IOException, Exception {
        User bob = createTestUser("bob");
        mvc.perform(post("/demo/add")
                        .param("name", bob.getName())
                        .param("email", bob.getEmail())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user",pwd))
                    );

        Iterable<User> found = service.getAllUsers();
        assertThat(found).extracting(User::getName).containsOnly(bob.getName());
    }

    /*
    @Test
    public void givenUser_whenGetUser_thenReturnJsonArray() throws Exception {

        User alex = createTestUser("alex","alex@home.de");

        List<User> allUsers = Arrays.asList(alex);

        given(service.getAllUsers()).willReturn(allUsers);

        mvc.perform(get("/demo/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(alex.getName())));
    }
    */
}
