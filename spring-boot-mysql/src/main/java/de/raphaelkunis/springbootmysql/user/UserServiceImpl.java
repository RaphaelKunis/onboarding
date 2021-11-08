package de.raphaelkunis.springbootmysql.user;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.Optional;

/** Provides services for manipulating user data
 * */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    /** Add new user after validating the input
     * todo: return better result, i.e. the result of userRepository.findById(id) -> JSON
     * result should look like
     *     {
     *          "timestamp": "2021-09-14T20:34:54.087+00:00",
     *          "status": 200,
     *          "message": "user was added",
     *          "path": "/demo/add"
     *     }
     *     May be done with Responsebody an an object with the given data
     */
    @Override
    public String addNewUser(String name, String email) {

        String retVal = "Saved";

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        // just for testing
        //System.out.println("called addNewUser");

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

    /**
     * Return a list of all users or an empty list
     */
    @Override
    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    /**
     * Return the user of a given id or empty
     */
    @Override
    public Optional<User> getUserById(Integer id) {
        // This returns a JSON or XML with the user if exists
        return userRepository.findById(id);
    }
}