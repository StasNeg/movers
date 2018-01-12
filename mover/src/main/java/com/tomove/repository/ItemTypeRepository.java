package com.tomove.repository;

import com.tomove.model.objectMover.Item;
import com.tomove.model.objectMover.ItemType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemTypeRepository extends CrudRepository<ItemType, Integer> {

}
