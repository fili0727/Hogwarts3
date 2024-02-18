package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String founder;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HouseColor> houseColors;


    public House(String founder, List<HouseColor> houseColors) {
        this.founder = founder;
        this.houseColors = houseColors;
    }

    public House(House house) {
    }

    public void copyFrom(House otherHouse){
        this.setFounder(otherHouse.getFounder());
        this.setHouseColors(otherHouse.getHouseColors());
    }

    public House() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
