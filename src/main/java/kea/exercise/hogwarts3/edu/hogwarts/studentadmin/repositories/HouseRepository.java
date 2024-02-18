package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories;

import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer>
{
}
