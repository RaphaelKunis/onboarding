package de.raphaelkunis.springbootmysql.unittests;

import de.raphaelkunis.springbootmysql.user.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.context.TestPropertySource;
//import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

//import static org.mockito.Mockito.*;

//import javax.validation.Validation;
import javax.validation.Validator;
//import javax.validation.ValidatorFactory;

//import java.util.List;

/** Does not work  -> disabled */
@TestPropertySource(locations = "classpath:application-test.properties")
@Disabled
public class ServiceUnitTest {

    @MockBean
    private UserService mockService = new UserServiceImpl();

    @MockBean
    private Validator validator = new LocalValidatorFactoryBean().getValidator();

    @BeforeAll
    public static void setUpAutowiredObjects() {
        // do nothing
    }

    @Test
    public void test_useradd_returnsSuccess() {
        String result = mockService.addNewUser("Bob","bob@home.de");
        assertEquals("Saved", result);
    }

}
