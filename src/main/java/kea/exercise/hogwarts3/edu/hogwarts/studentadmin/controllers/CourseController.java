package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.controllers;


import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Course;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Student;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Teacher;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.CourseRepository;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.StudentRepository;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository,
                            StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }


    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        return courses;
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);

        return ResponseEntity.of(course);
    }



    @GetMapping("courses/{id}/students")
    public ResponseEntity<List<Student>> getCourseStudents(@PathVariable int id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {
            List<Student> courseStudents = optionalCourse.get().getStudents();
            return ResponseEntity.ok(courseStudents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("courses/{id}/teacher")
    public ResponseEntity<Teacher> getCourseTeacher(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);

        if(course.isPresent()) {
            Teacher teacher = course.get().getTeacher();

            return ResponseEntity.ok().body(teacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PostMapping("/courses/{id}/students")
    public ResponseEntity<Course> addStudentsToCourse(@PathVariable int id, @RequestBody List<Integer> studentIds) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (!courseOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Course course = courseOptional.get();
        List<Student> studentsToAdd = studentRepository.findAllById(studentIds);


        for (Student student : studentsToAdd) {
            course.getStudents().add(student);
        }

        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }
    //  Test data for postman ex: http://localhost:8080/courses/2/students ; this data    [3,4]


    @PutMapping("courses/{id}/students")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable int id, @RequestBody Student student) {
        Optional<Course> course = courseRepository.findById(id);

        if(course.isPresent()) {
            Course existingCourse = course.get();
            existingCourse.setStudentToCourse(student);

            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("courses/{id}/teacher")
    public ResponseEntity<Course> updateCourseTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Course> course = courseRepository.findById(id);
        Optional<Teacher> newTeacher = teacherRepository.findById(teacher.getId());

        if (course.isPresent() && newTeacher.isPresent()) {
            Course existingCourse = course.get();
            Teacher existingTeacher = newTeacher.get();

            existingCourse.setTeacher(existingTeacher);

            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            course.get().getStudents().clear();
        }

        courseRepository.deleteById(id);

        return ResponseEntity.of(course);
    }

    @DeleteMapping("courses/{id}/students")
    public ResponseEntity<Course> deleteStudentFromCourse(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> deleteStudent = studentRepository.findById(student.getId());
        Optional<Course> course = courseRepository.findById(id);

        if (deleteStudent.isPresent() && course.isPresent()) {
            Course existingCourse = course.get();
            Student existingStudent = deleteStudent.get();

            existingCourse.getStudents().remove(existingStudent);

            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
