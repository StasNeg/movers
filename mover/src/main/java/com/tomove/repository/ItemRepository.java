package com.tomove.repository;

import com.tomove.model.objectMover.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    Optional<Item> findByName(String name);
}
