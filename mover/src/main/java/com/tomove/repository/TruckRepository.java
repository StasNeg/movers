package com.tomove.repository;

import com.tomove.model.enums.Area;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Truck;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface TruckRepository extends CrudRepository<Truck,Integer> {
	@Query(" select truck from Truck truck "
			+ " where truck.mover =:moverId "
			+ " and truck.area = :reqArea and truck.roomsCount >= :reqRoomCount "
			+ " and ( select count(*) from Request r "
			+ " where r.truck = truck and r.dateTime between :reqDateFrom and :reqDateTo ) <= :maxTruckDayMovesCount ")
	public Iterable<Truck> getAvailableTrucksForRequest(@Param("maxTruckDayMovesCount") long maxTruckDayMovesCount, @Param("moverId") Account userId, 
			@Param("reqArea") Area reqArea, @Param("reqRoomCount") int reqRoomCount, 
			@Param("reqDateFrom") LocalDateTime reqDateMin, @Param("reqDateTo") LocalDateTime reqDateMax);

	public Iterable<Truck> getAllByMoverId(Integer moverId);
}
