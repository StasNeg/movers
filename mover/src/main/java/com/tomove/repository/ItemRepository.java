package com.tomove.repository;

import com.tomove.model.objectMover.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    Optional<Item> findByName(String name);

    @Query(" select new com.tomove.model.objectMover.Item(item.id, item.name, item.from, item.to, item.room)  from Item item where item.room.id in " +
            "(select room.id from Room room where room.request.id = :requestId)")
    List<Item> getAllByRequestId(@Param("requestId")Integer requestId);
}
