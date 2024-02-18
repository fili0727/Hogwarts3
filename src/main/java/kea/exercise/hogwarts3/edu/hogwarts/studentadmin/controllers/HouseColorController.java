package kea.exercise.hogwarts3.edu.hogwarts.studentadmin.controllers;


import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.models.HouseColor;
import kea.exercise.hogwarts3.edu.hogwarts.studentadmin.repositories.HouseColorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HouseColorController {
    private final HouseColorRepository houseColorRepository;

    public HouseColorController(HouseColorRepository houseColorRepository) {
        this.houseColorRepository = houseColorRepository;
    }

    @GetMapping("/housecolors")
    public List<HouseColor> getHouseColors() {
        List<HouseColor> allHouseColors = houseColorRepository.findAll();

        return allHouseColors;
    }

    @GetMapping("/housecolors/{id}")
    public ResponseEntity<HouseColor> getHouseColor(int id) {
        Optional<HouseColor> houseColor = houseColorRepository.findById(id);

        return ResponseEntity.of(houseColor);
    }

    @PostMapping("/housecolors")
    public HouseColor createHouseColor(HouseColor houseColor) {
        return houseColorRepository.save(houseColor);
    }

    @PutMapping("/housecolors/{id}")
    public ResponseEntity<HouseColor> updateHouseColor(@PathVariable int id, @RequestBody HouseColor houseColor) {
        Optional<HouseColor> original = houseColorRepository.findById(id);

        if(original.isPresent()) {
            HouseColor originalHouseColor = original.get();
            originalHouseColor.copyFrom(houseColor);

            HouseColor updateHouseColor = houseColorRepository.save(originalHouseColor);
            return ResponseEntity.ok().body(updateHouseColor);
        } else {
            HouseColor newHouseColor = new HouseColor(houseColor);

            HouseColor savedHouseColor = houseColorRepository.save(newHouseColor);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHouseColor);
        }
    }

    @DeleteMapping("/housecolors/{id}")
    public ResponseEntity<HouseColor> deleteHouseColor(@PathVariable int id) {
        Optional<HouseColor> houseColorToDelete = houseColorRepository.findById(id);
        houseColorRepository.deleteById(id);

        return ResponseEntity.of(houseColorToDelete);
    }

}
