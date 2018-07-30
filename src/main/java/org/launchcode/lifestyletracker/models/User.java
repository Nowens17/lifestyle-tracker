package org.launchcode.lifestyletracker.models;


import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 15, message = "Username must contain between 1 and 15 characters")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Log> logs;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Food> foods;

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
