package de.raphaelkunis.springbootmysql;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/* proviedes services for manipulationg user data */
@Service
public class UserService {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    public String addNewUser(String name, String email) {

        String retVal = "Saved";

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        try {
            userRepository.save(n);
        } catch (Exception e) {
            retVal = "Error: " + e.getMessage();
        }

        return retVal;
    }
}