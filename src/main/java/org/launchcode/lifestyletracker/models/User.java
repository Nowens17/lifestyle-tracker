package org.launchcode.lifestyletracker.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity

public class User {

    @NotNull
    private String username;

    private String email;

    @NotNull
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Log> logs;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Food> foods;

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
