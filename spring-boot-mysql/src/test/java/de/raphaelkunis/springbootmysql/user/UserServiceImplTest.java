package de.raphaelkunis.springbootmysql.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;

/**
 * real unit test without Sprint framework overhead
 * runs like hell :)
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Validator validator = factory.getValidator();

    private UserServiceImpl userService;

    //init mock service
    @BeforeEach
    void setup() {
        userService = new UserServiceImpl(userRepository,validator);
    }

    //remove state from mock after each test
    @AfterEach
    void reset() {
        Mockito.reset(userRepository);
    }

    @Test
    void basicTestOfTest(){
        Assertions.assertNotNull(userService.validator);
    }

    @Test
    void anotherUsefulTest() {
        Mockito.when(userRepository.findById(Mockito.eq(12))).thenReturn(Optional.empty());
        Optional<User> user = userService.getUserById(12);
        Assertions.assertTrue(user.isEmpty());

        //verify that findById was called
        Mockito.verify(userRepository,Mockito.times(1)).findById(ArgumentMatchers.anyInt());

    }

    @Test
    void usefulTest() {
        Mockito.when(userRepository.findById(Mockito.eq(12))).thenReturn(Optional.of(new User()));
        Optional<User> user = userService.getUserById(12);
        Assertions.assertTrue(user.isPresent());

        //verify that findById was called
        Mockito.verify(userRepository,Mockito.times(1)).findById(ArgumentMatchers.anyInt());

    }



}
