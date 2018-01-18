package com.tomove.controller;

import com.tomove.controller.to.DataTo;
import com.tomove.model.enums.RoomType;
import com.tomove.model.objectMover.ItemType;
import com.tomove.model.objectMover.TypeProperties;
import com.tomove.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static com.tomove.controller.PathConstant.GET_ALL_ITEMS_FROM_ROOMTYPE;
import static com.tomove.controller.PathConstant.GET_ALL_ROOMS_TYPE;
import static com.tomove.controller.PathConstant.PROPERTIES_OF_ITEM;

@RestController
@CrossOrigin
public class AddItemsController {

	private ItemTypeRepository repository;

	@Autowired
	public AddItemsController(ItemTypeRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = GET_ALL_ROOMS_TYPE, method = RequestMethod.GET)
	public DataTo getAllTypeRooms() {
		return new DataTo(true, RoomType.values());
	}

	@RequestMapping(value = GET_ALL_ITEMS_FROM_ROOMTYPE, method = RequestMethod.GET)
	public DataTo getAllItemsForTypeRoom(@RequestParam(value = "room", required = false) String room) {
		Iterable<ItemType> data = null;

		if (!room.isEmpty() && isValidRoom(room)) {
			data = repository.findAllByroomType(RoomType.valueOf(room));
		}
		return data == null ? new DataTo(false, null) : new DataTo(true, data);
	}

	private boolean isValidRoom(String room) {
		return Stream.of(RoomType.values()).map(RoomType::name).anyMatch(x->x.equals(room));
	}
	@RequestMapping(value = PROPERTIES_OF_ITEM, method = RequestMethod.GET)
	public DataTo getAllPropertiesOfItem(@RequestParam(value = "idItemType", required = false) String itemType) {
		Iterable<TypeProperties> data = null;
		System.out.print("hello there");
		data = repository.findTypeProperties(Integer.parseInt(itemType));

		return new DataTo(false,data);
	}

}
