package org.launchcode.lifestyletracker.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity

public class Food {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    private double servingSize;

    @NotNull
    @Min(1)
    private int calories;

    @ManyToOne
    private User user;

    public Food() { }

    public Food(int id, String date, String name, double servingSize, int calories) {
        this.id = id;
        this.name = name;
        this.servingSize = servingSize;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getServingSize() {
        return servingSize;
    }

    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
