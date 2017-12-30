package com.tomove.repository;

import com.tomove.model.subjectMover.Truck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TruckRepository extends CrudRepository<Truck,Integer> {

}
