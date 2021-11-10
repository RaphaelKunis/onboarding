package de.raphaelkunis.springbootmysql.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;


@Entity // This tells Hibernate to make a table out of this class
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Name must not be blank")       // added for input validation
    private String name;

    @Email(message = "Email should be valid")           // added for input validation
    private String email;

    public User(String name, String email) {
        if (name != null) this.name = name;
        if (email != null) this.email = email;
    }

    public User(String name) {
        this(name, null);
    }
    public User() {
        this(null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}