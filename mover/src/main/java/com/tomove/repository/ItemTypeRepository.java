package com.tomove.repository;

import com.tomove.model.enums.RoomType;
import com.tomove.model.objectMover.ItemType;
import com.tomove.model.objectMover.TypeProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepository extends CrudRepository< ItemType, Integer> {


	Iterable<ItemType> findAllByroomType(RoomType roomType);

	@Query("select i.properties from ItemType i where i.id = :idItemType" )
	Iterable<TypeProperties> findTypeProperties(@Param("idItemType") Integer idItemType);

}
