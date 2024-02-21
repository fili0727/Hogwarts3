package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.controllers;

import jakarta.transaction.Transactional;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.EmpType;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Teacher;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable int id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return ResponseEntity.of(teacher);
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }

    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return teacherRepository.save(teacher);
    }

    @PatchMapping("/teachers/{id}")
    @Transactional
    public ResponseEntity<Teacher> updateEmployee(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Teacher original = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "headOfHouse":
                    original.setHeadOfHouse((Boolean) value);
                    break;
                case "employmentEnd":
                    original.setEmploymentEnd(value == null ? null : LocalDate.parse((String) value));
                    break;
                case "employment":
                    original.setEmployment(EmpType.valueOf((String) value));
                    break;

            }
        });

        teacherRepository.save(original);
        return ResponseEntity.ok(original);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher){
        Optional<Teacher> original = teacherRepository.findById(id);

        if(original.isPresent()){
            Teacher originalTeacher = original.get();
            originalTeacher.copyFrom(teacher);

            Teacher updatedTeacher = teacherRepository.save(originalTeacher);
            return ResponseEntity.ok().body(updatedTeacher);
        } else {
            Teacher newTeacher = new Teacher();
            newTeacher.copyFrom(teacher);

            Teacher savedTeacher = teacherRepository.save(newTeacher);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);

        }
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        teacherRepository.deleteById(id);
        return ResponseEntity.of(teacher);
    }

}
