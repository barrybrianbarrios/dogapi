package com.udacity.bootstrap.web;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.udacity.bootstrap.service.DogService;

import java.util.List;

@RestController
//@TestConfiguration
public class DogController {
    private DogService dogService;

    @Autowired
    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }


    @GetMapping("/breeds")
    public ResponseEntity<List<String>> getBreeds() {
        List<String> list = dogService.retrieveDogBreed();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }


    @GetMapping("/names")
    public ResponseEntity<List<String>> getNames() {
        List<String> list = dogService.retrieveDogNames();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }


    @GetMapping("/breed/{id}")
    public ResponseEntity<String> getBreed(@PathVariable("id") long id) {
        String breed = dogService.retrieveDogBreedById(id);
        if (breed != null)
            return new ResponseEntity<String>(breed, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
