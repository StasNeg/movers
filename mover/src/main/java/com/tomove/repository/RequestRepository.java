package com.tomove.repository;

import com.tomove.model.objectMover.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {
}