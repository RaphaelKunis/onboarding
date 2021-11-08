package de.raphaelkunis.springbootmysql.user;

import java.util.Optional;

public interface UserService {

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
    String addNewUser(String name, String email);

    /**
     * Return a list of all users or an empty list
     */
    Iterable<User> getAllUsers();

    /**
     * Return the user of a given id or empty
     */
    Optional<User> getUserById(Integer id);
}
