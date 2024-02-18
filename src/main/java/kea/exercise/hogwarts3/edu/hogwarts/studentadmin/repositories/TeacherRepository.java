package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories;

import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
