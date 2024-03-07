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
    public ResponseEntity<Teacher> patchTeacher(@PathVariable int id, @RequestBody edu.hogwarts.studentadmin.models.DTOs.TeacherDTO teacherDTO) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isPresent()) {
            Teacher existingTeacher = optionalTeacher.get();
            existingTeacher.applyPatch(teacherDTO);

            teacherRepository.save(existingTeacher);
            return ResponseEntity.ok().body(existingTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
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
