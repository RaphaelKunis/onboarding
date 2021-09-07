package de.raphaelkunis.springbootmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Optional;
import javax.validation.Valid;

@Controller                     // This means that this class is a Controller
@RequestMapping(path="/demo")   // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@Valid @RequestParam String name
            , @Valid @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        // @Valid means we validate against the validation annotations in the User class, i.e. @NotBlank

        return userService.addNewUser(name, email);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    /* rk1 added new method for retrieving user by id
     * TODO:
     *  - check return off null */
    @GetMapping(path="/getUser")
    public @ResponseBody Optional<User> getUserById(@Valid @RequestParam int id) {
        // This returns a JSON or XML with the user if exists
        return userRepository.findById(id);
    }
}