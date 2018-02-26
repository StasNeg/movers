package com.tomove.repository;


import com.tomove.common.AddressDto;
import com.tomove.model.objectMover.RequestAdress;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface RequestAddressRepository extends CrudRepository<RequestAdress, Integer> {
	@Query("select new com.tomove.common.AddressDto(addr.id, ra.seqnumberRequest, addr.city, addr.street, addr.building, addr.apartment, addr.floor, addr.lift) from RequestAdress ra "
			+ " join ra.address addr "
			+ " join ra.request req "
	   		+ " where req.id = :reqId order by ra.seqnumberRequest asc" )
	public Iterable<AddressDto> getAddressByRequest(@Param("reqId") int requestId);
	
}
