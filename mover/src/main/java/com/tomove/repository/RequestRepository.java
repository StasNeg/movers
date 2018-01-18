package com.tomove.repository;

import com.tomove.model.mapping.RequestDetails;
import com.tomove.model.objectMover.Request;
import com.tomove.model.subjectMover.Account;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository extends CrudRepository<Request,Integer>{
	
	List<Request> findAllByCustomer_id(Integer id);
	List<Request> findAllByTruckId(Integer truckId);
	List<Request> findAllByCost(Integer cost);
	List<Request> findAllByCustomerId(Account userId);
    
    @Query("select new com.tomove.model.mapping.RequestDetails(r.id, r.status, r.dateTime, r.cost, r.isPersonal) from Request r "
    		+ " where r.customer = :userId "
    		+ " and r.status in ('INITIAL','CONFIRMED','UPDATED') "
    		+ " order by r.id asc ")
    public Iterable<RequestDetails> reqDetails(@Param("userId") Account userId);
    
    @Query("select new com.tomove.model.mapping.RequestDetails(r.id, r.status, r.dateTime, r.cost, r.isPersonal) from Request r "
    		+ " where r.customer = :userId "
    		+ " and r.status in ('INITIAL','CONFIRMED','UPDATED') and r.dateTime between :reqDateFrom and :reqDateTo "
    		+ " order by r.id asc ")
    public Iterable<RequestDetails> reqDetailsDate(@Param("userId") Account userId, @Param("reqDateFrom") LocalDateTime reqDateMin, @Param("reqDateTo") LocalDateTime reqDateMax);
    
    @Query("select new com.tomove.model.mapping.RequestDetails(r.id, r.status, r.dateTime, r.cost, r.isPersonal) from Request r "
    		+ " where r.customer = :userId "
    		+ " and r.status in ('INITIAL','CONFIRMED','UPDATED') and r.dateTime >= :reqDateFrom "
    		+ " order by r.id asc ")
	public Iterable<RequestDetails> reqDetailsFromDate(@Param("userId") Account userId, @Param("reqDateFrom") LocalDateTime reqDateMin);
    
    @Query("select new com.tomove.model.mapping.RequestDetails(r.id, r.status, r.dateTime, r.cost, r.isPersonal) from Request r "
    		+ " join Truck tr on tr.id = r.truck "
    		+ " where tr.mover = :moverId "
    		+ " and r.dateTime between :reqDateFrom and :reqDateTo "
    		+ " order by r.id asc ")
    public Iterable<RequestDetails> reqDetailsMoverDate(@Param("moverId") Account userId, @Param("reqDateFrom") LocalDateTime reqDateMin, @Param("reqDateTo") LocalDateTime reqDateMax);

	
}
