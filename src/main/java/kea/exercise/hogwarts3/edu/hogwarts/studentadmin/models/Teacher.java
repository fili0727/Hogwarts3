package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String middleName;
    @JsonIgnore
    private String lastName;
    private LocalDate dateOfBirth;
    @ManyToOne
    private House house;
    private boolean headOfHouse;
    @Enumerated(EnumType.STRING)
    private EmpType employment;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    public Teacher (String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, boolean headOfHouse, EmpType employment, LocalDate employmentStart, LocalDate employmentEnd){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.headOfHouse = headOfHouse;
        this.employment = employment;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
    }

    public void copyFrom(Teacher otherTeacher){
        this.setFullName(otherTeacher.getFullName());
        this.setDateOfBirth(otherTeacher.getDateOfBirth());
        this.setHouse(otherTeacher.getHouse());
        this.setHeadOfHouse(otherTeacher.isHeadOfHouse());
        this.setEmployment(otherTeacher.getEmployment());
        this.setEmploymentStart(otherTeacher.getEmploymentStart());
        this.setEmploymentEnd(otherTeacher.getEmploymentEnd());
    }

    public Teacher (){}

    public void applyPatch(edu.hogwarts.studentadmin.models.DTOs.TeacherDTO teacherDTO) {
        if (teacherDTO.getHeadOfHouse() != null) {
            this.setHeadOfHouse(teacherDTO.getHeadOfHouse());
        }

        if (teacherDTO.getEmploymentEnd() != null) {
            this.setEmploymentEnd(teacherDTO.getEmploymentEnd());
        }

        if (teacherDTO.getEmploymentType() != null) {
            this.setEmployment(teacherDTO.getEmploymentType());
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @JsonGetter("fullName")
    public String getFullName() {
        if(middleName== null) {
            return firstName + " " + lastName;
        }else{
            return firstName + " " + middleName + " " + lastName;
        }
    }

    @JsonSetter("fullName")
    public String setFullName(String fullName) {
        String[] names = fullName.split(" ");
        if(names.length == 2) {
            this.firstName = names[0];
            this.middleName = null;
            this.lastName = names[1];
        }else {
            this.firstName = names[0];
            this.middleName = names[1];
            this.lastName = names[2];
        }
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonIgnore
    public House getHouse() {
        return house;
    }

    @JsonGetter("house")
    public String getHouseName() {
        return house.getHouseName();
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public boolean isHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(boolean headOfHouse) {
        this.headOfHouse = headOfHouse;
    }

    public EmpType getEmployment() {
        return employment;
    }

    public void setEmployment(EmpType employment) {
        this.employment = employment;
    }

    public LocalDate getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(LocalDate employmentStart) {
        this.employmentStart = employmentStart;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }

}
