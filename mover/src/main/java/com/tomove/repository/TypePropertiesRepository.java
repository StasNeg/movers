package com.tomove.repository;

import com.tomove.model.objectMover.TypeProperties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePropertiesRepository  extends CrudRepository<TypeProperties, Integer> {
}
