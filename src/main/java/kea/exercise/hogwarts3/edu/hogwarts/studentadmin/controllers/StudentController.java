package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.controllers;

import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Student;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public  StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        Optional<Student> student = studentRepository.findById(id);
        return ResponseEntity.of(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }
    

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> original = studentRepository.findById(id);

        if (original.isPresent()) {
            Student originalStudent = original.get();
            originalStudent.copyFrom(student);

            Student updatedStudent = studentRepository.save(originalStudent);
            return ResponseEntity.ok().body(updatedStudent);
        } else {
            Student newStudent = new Student();
            newStudent.copyFrom(student);

            Student savedStudent = studentRepository.save(newStudent);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        }
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (!optionalStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Student student = optionalStudent.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "prefect":
                    student.setPrefect((Boolean) value);
                    break;
                case "schoolYear":
                    student.setSchoolYear((Integer) value);
                    break;
                default:
                    break;
            }
        });

        Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id){
        Optional<Student> student = studentRepository.findById(id);
        studentRepository.deleteById(id);
        return ResponseEntity.of(student);
    }


}

