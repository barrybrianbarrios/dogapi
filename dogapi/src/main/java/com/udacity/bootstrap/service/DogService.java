package com.udacity.bootstrap.service;

import java.util.List;


public interface DogService {
    List<String> retrieveDogBreed();
    String retrieveDogBreedById(long id);
    List<String> retrieveDogNames();
}
