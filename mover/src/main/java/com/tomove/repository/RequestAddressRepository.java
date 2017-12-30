package com.tomove.repository;

import com.tomove.model.objectMover.RequestAdress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RequestAddressRepository extends CrudRepository<RequestAdress, Integer> {

}
