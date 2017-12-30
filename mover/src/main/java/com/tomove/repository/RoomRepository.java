package com.tomove.repository;

import com.tomove.model.objectMover.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

}
