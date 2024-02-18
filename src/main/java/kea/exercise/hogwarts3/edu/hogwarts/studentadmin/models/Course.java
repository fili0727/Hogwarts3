package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private int schoolYear;
    private boolean current;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany
    private List<Student> students;


    public Course(String subject, int schoolYear, boolean current, Teacher teacher, List<Student> students) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
        this.teacher = teacher;
        this.students = students;
    }

    public Course(Course course) {
    }

    public void copyFrom(Course otherCourse){
        this.setSubject(otherCourse.getSubject());
        this.setSchoolYear(otherCourse.getSchoolYear());
        this.setCurrent(otherCourse.isCurrent());
        this.setTeacher(otherCourse.getTeacher());
        this.setStudents(otherCourse.getStudents());
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setStudentToCourse(Student student){
        this.students.add(student);
    }


}
