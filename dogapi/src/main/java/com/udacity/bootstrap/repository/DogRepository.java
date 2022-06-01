package com.udacity.bootstrap.repository;

import com.udacity.bootstrap.projections.*;
import org.springframework.data.repository.CrudRepository;
import com.udacity.bootstrap.Entity.Dog;
import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {
    List<Name> findNameBy();
    List<Breed> findOriginBy();
    Breed findRazaById(long Id);

}
