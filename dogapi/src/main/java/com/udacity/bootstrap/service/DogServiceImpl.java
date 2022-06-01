package com.udacity.bootstrap.service;
import com.udacity.bootstrap.projections.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.bootstrap.repository.DogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {
    @Autowired
    DogRepository dogRepository;

    public List<String> retrieveDogBreed() {
        return  dogRepository.findOriginBy().stream().map(x -> x.getBreed()).collect(Collectors.toList());
    }

    public String retrieveDogBreedById(long Id) {
        Breed breed = dogRepository.findRazaById(Id);
        return breed==null?null:breed.getBreed();
    }

    public List<String> retrieveDogNames(){
        return dogRepository.findNameBy().stream().map(x -> x.getName()).collect(Collectors.toList());
    }

}
