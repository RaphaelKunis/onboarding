package de.raphaelkunis.springbootmysql;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Optional;
import de.raphaelkunis.springbootmysql.user.UserService;
import de.raphaelkunis.springbootmysql.user.User;
import de.raphaelkunis.springbootmysql.demo.DemoService;

@Controller                     // This means that this class is a Controller
@RequestMapping(path="/demo")   // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private DemoService demoService;

	/* Test some new features */
	@GetMapping(path="/", produces = "application/plain; charset=UTF-8")
	public @ResponseBody String sayHello() {
		return demoService.sayHello();
	}

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return userService.addNewUser(name, email);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /* rk1 added new method for retrieving user by id */
    @GetMapping(path="/getUser")
    public @ResponseBody Optional<User> getUserById(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    /* error page for browser in case of problems */
    /*
    @GetMapping(path="/error")
    public @ResponseBody String error() {     return "Something bad happened - this replaces the Whitelabel Error Page ";}
    does not work that way -> use public class AppErrorController implements ErrorController instead
    see https://stackoverflow.com/questions/31134333/this-application-has-no-explicit-mapping-for-error
    */
}