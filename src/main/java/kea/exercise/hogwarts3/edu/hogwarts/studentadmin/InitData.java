package kea.exercise.hogwarts3.edu.hogwarts.studentadmin;


import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.*;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.CourseRepository;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.HouseRepository;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.StudentRepository;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.List;


@Component
public class InitData implements CommandLineRunner {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private HouseRepository houseRepository;

    public InitData() {
    }

    @Autowired
    public InitData(CourseRepository courseRepository, StudentRepository studentRepository,
                           TeacherRepository teacherRepository, HouseRepository houseRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
    }


    public void run(String...args) {
        House gryffindor = new House("Godric Gryffindor",
                List.of(new HouseColor("Red"), new HouseColor("Gold")));
        House hufflepuff = new House("Helga Hufflepuff",
                List.of(new HouseColor("Yellow"), new HouseColor("Black")));
        House ravenclaw = new House("Rowena Ravenclaw",
                List.of(new HouseColor("Blue"), new HouseColor("Bronze")));
        House slytherin = new House("Salazar Slytherin",
                List.of(new HouseColor("Green"), new HouseColor("Silver")));

        houseRepository.save(gryffindor);
        houseRepository.save(hufflepuff);
        houseRepository.save(ravenclaw);
        houseRepository.save(slytherin);


        Student harry = new Student("Harry", "James", "Potter",
                LocalDate.of(1980, 7, 31),
                gryffindor, true, 1991, 1998, true );

        Student cedric = new Student("Cedric", "", "Diggory",
                LocalDate.of(1977, 9, 1),
                hufflepuff, false, 1996, 1989, true);

        Student eleanor = new Student("Eleanor", "Jane", "Fawley", LocalDate.of(1980, 3, 22), ravenclaw, false, 1991, 1998, true);

        Student draco = new Student("Draco", "Lucius", "Malfoy",
                LocalDate.of(1980, 6, 5),
                slytherin, false, 1992, 1998, true);

        studentRepository.save(harry);
        studentRepository.save(cedric);
        studentRepository.save(eleanor);
        studentRepository.save(draco);


        Teacher mcGonagall = new Teacher("Minerva", "", "McGonagall", LocalDate.of(1935, 10, 4), gryffindor, true, EmpType.TENURED, LocalDate.of(1956, 9, 1), null);
        Teacher snape = new Teacher("Severus", "", "Snape", LocalDate.of(1960, 1, 9), slytherin, true, EmpType.TENURED, LocalDate.of(1981, 9, 1), LocalDate.of(1997, 7, 31));
        Teacher flitwick = new Teacher("Filius", "", "Flitwick", LocalDate.of(1958, 10, 17), ravenclaw, true, EmpType.TEMPORARY, LocalDate.of(1980, 9, 1), null);
        Teacher sprout = new Teacher("Pomona", "", "Sprout", LocalDate.of(1941, 5, 15), hufflepuff, true, EmpType.TENURED, LocalDate.of(1971, 9, 1), null);

        teacherRepository.save(mcGonagall);
        teacherRepository.save(snape);
        teacherRepository.save(flitwick);
        teacherRepository.save(sprout);

        Course transfiguration = new Course("Transfiguration", 1992, true, mcGonagall, List.of(harry, cedric, draco));
        Course potions = new Course("Potions", 1992, true, snape, List.of(harry, cedric));
        Course charms = new Course("Charms", 1992, true, flitwick, List.of(cedric, eleanor));
        Course DADA = new Course("Defense Against the Dark Arts", 1992, true, snape, List.of(harry, cedric, eleanor));

        courseRepository.save(transfiguration);
        courseRepository.save(potions);
        courseRepository.save(charms);
        courseRepository.save(DADA);

    }

}
