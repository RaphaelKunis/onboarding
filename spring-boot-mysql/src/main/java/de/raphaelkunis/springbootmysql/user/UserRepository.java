package de.raphaelkunis.springbootmysql.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Integer> {

    /* explicit declaration necessary for testing ?
        public Optional<User> findByName(String name);
        public List<User> findAll();
     */
}