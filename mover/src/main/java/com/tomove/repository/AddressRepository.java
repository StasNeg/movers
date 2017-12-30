package com.tomove.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tomove.model.objectMover.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}
