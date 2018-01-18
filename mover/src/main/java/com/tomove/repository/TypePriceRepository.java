package com.tomove.repository;

import com.tomove.model.objectMover.TypePrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypePriceRepository extends CrudRepository<TypePrice, Integer> {

}
