package de.raphaelkunis.springbootmysql;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import java.util.Set;

/** Proviedes services for manipulating user data */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;

    /** Add new user after validating the input
     * todo: return better result, i.e. the result of userRepository.findById(id) -> JSON */
    public String addNewUser(String name, String email) {

        String retVal = "Saved";

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            // throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
            retVal = "Validation Error: " + sb.toString() + "; ";
        } else {
            try {
                userRepository.save(user);
            } catch (Exception e) {
                retVal = "Error: " + e.getMessage();    // should be less internal
            }
        }

        return retVal;
    }
}