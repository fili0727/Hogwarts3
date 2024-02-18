package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories;

import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer>{
}
