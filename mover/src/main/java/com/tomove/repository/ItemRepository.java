package com.tomove.repository;

import com.tomove.model.objectMover.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

}
