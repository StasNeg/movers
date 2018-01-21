package com.tomove.repository;

import com.tomove.model.objectMover.Item;
import com.tomove.model.objectMover.TypeProperties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemPropertyRepository extends CrudRepository<TypeProperties, Integer> {

}
