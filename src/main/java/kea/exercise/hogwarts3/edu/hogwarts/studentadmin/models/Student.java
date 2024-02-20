package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String middleName;
    @JsonIgnore
    private String lastName;
    @JsonIgnore
    private LocalDate dateOfBirth;
    @ManyToOne
    private House house;
    @JsonIgnore
    private boolean prefect;
    @JsonIgnore
    private int enrollmentYear;
    @JsonIgnore
    private int schoolYear;
    @JsonIgnore
    private int graduationYear;
    @JsonIgnore
    private boolean graduated;

    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, boolean prefect, int enrollmentYear, int schoolYear, int graduationYear, boolean graduated) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.prefect = prefect;
        this.enrollmentYear = enrollmentYear;
        this.schoolYear = schoolYear;
        this.graduationYear = graduationYear;
        this.graduated = graduated;
    }

    public void copyFrom(Student otherStudent) {
        this.setFullName(otherStudent.getFullName());
        this.setDateOfBirth(otherStudent.getDateOfBirth());
        this.setHouse(otherStudent.getHouse());
        this.setPrefect(otherStudent.isPrefect());
        this.setEnrollmentYear(otherStudent.getEnrollmentYear());
        this.setSchoolYear(otherStudent.getSchoolYear());
        this.setGraduationYear(otherStudent.getGraduationYear());
        this.setGraduated(otherStudent.isGraduated());
    }

    public Student() {
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

    public boolean isPrefect() {
        return prefect;
    }

    public void setPrefect(boolean prefect) {
        this.prefect = prefect;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }



}
