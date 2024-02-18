package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.controllers;

import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.House;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.HouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HouseController {
    private final HouseRepository houseRepository;

    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping("/houses")
    public List<House> getHouses() {
        List<House> allHouses = houseRepository.findAll();

        return allHouses;
    }

    @GetMapping("/houses/{id}")
    public ResponseEntity<House> getHouse(@PathVariable int id) {
        Optional<House> house = houseRepository.findById(id);

        return ResponseEntity.of(house);
    }

    @PostMapping("/houses")
    public House createHouse(@RequestBody House house) {
        return houseRepository.save(house);
    }

    @PutMapping("/houses/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable int id, @RequestBody House house) {
        Optional<House> updateHouse = houseRepository.findById(id);

        if(updateHouse.isPresent()) {
            House objectGettingUpdated = updateHouse.get();
            objectGettingUpdated.copyFrom(house);

            House updatedHouse = houseRepository.save(objectGettingUpdated);
            return ResponseEntity.ok().body(updatedHouse);
        } else {
            House newHouse = new House(house);

            House savedHouse = houseRepository.save(newHouse);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHouse);
        }
    }

    @DeleteMapping("/houses/{id}")
    public ResponseEntity<House> deleteHouse(@PathVariable int id) {

            Optional<House> house = houseRepository.findById(id);
            houseRepository.deleteById(id);

            return ResponseEntity.of(house);
        }
    }


