package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories;

import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
