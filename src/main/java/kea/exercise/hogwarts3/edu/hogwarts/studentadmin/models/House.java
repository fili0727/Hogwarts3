package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class House {
    @Id
    private String houseName;
    private String founder;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HouseColor> houseColors;


    public House(String houseName, String founder, List<HouseColor> houseColors) {
        this.houseName = houseName;
        this.founder = founder;
        this.houseColors = houseColors;
    }

    public House(House house) {
    }

    public void copyFrom(House otherHouse){
        this.setHouseName(otherHouse.getHouseName());
        this.setFounder(otherHouse.getFounder());
        this.setHouseColors(otherHouse.getHouseColors());
    }

    public House() {
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public List<HouseColor> getHouseColors() {
        return houseColors;
    }

    public void setHouseColors(List<HouseColor> colors) {
        this.houseColors = colors;
    }

}
