package org.launchcode.lifestyletracker.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity

public class Log {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String date;

    @NotNull
    private double weight;

    public Log(){}

    public Log(String date, int weight) {
        this.date = date;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
