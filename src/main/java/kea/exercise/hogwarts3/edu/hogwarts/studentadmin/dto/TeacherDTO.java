package edu.hogwarts.studentadmin.models.DTOs;


import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.EmpType;

import java.time.LocalDate;

public class TeacherDTO {
    private Boolean headOfHouse;
    private LocalDate employmentEnd;
    private EmpType empType;

    public Boolean getHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(Boolean headOfHouse) {
        this.headOfHouse = headOfHouse;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }

    public EmpType getEmploymentType() {
        return empType;
    }

    public void setEmploymentType(EmpType empType) {
        this.empType = empType;
    }
}