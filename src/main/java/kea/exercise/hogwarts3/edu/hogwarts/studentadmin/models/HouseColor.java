package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HouseColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public HouseColor() {
    }

    public HouseColor(String name) {
        this.name = name;
    }

    public HouseColor(HouseColor otherHouseColor) {
        this.id = otherHouseColor.getId();
        this.name = otherHouseColor.getName();
    }

    public void copyFrom(HouseColor otherHouseColor) {
        this.setName(otherHouseColor.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}