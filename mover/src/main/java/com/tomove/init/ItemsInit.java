package com.tomove.init;

import com.tomove.model.enums.RoomType;
import com.tomove.model.objectMover.ItemType;
import com.tomove.model.objectMover.TypeProperties;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.ItemPropertyRepository;
import com.tomove.repository.ItemRepository;
import com.tomove.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class ItemsInit implements ApplicationRunner {

    private ItemRepository itemRepository;
    private ItemTypeRepository itemTypeRepository;
    private ItemPropertyRepository itemPropertyRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String isInit;

    @Autowired
    public ItemsInit(ItemRepository itemRepository,
                     ItemTypeRepository itemTypeRepository,
                     ItemPropertyRepository itemPropertyRepository
    ) {
        this.itemRepository = itemRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.itemPropertyRepository = itemPropertyRepository;
    }

    public void run(ApplicationArguments args) {
        if (!isInit.equals("none")) {

            /* CUPBOARD */
            ItemType cupBoard = new ItemType(RoomType.BEDROOM, "Cupboard");
            itemTypeRepository.save(cupBoard);

            HashSet<TypeProperties> cupBoardProperties = new HashSet<>();
            cupBoardProperties.add(new TypeProperties(1, "doors", "one"));
            cupBoardProperties.add(new TypeProperties(2,"doors", "two"));
            cupBoardProperties.add(new TypeProperties(3,"task", "disassemble-assemble"));
            cupBoardProperties.add(new TypeProperties(4,"task", "moveDown-moveUp"));

            cupBoard.setProperties(cupBoardProperties);
            for (TypeProperties cupBoardProperty : cupBoardProperties) {
                cupBoardProperty.setItemType(cupBoard);
            }
            itemPropertyRepository.saveAll(cupBoardProperties);

            /* FRIDGE */
            ItemType fridge = new ItemType(RoomType.KITCHEN, "Fridge");
            itemTypeRepository.save(fridge);

            HashSet<TypeProperties> fridgeProperties = new HashSet<>();
            fridgeProperties.add(new TypeProperties(1, "volume", "<400L"));
            fridgeProperties.add(new TypeProperties(2, "volume", "side-by-side"));
            fridgeProperties.add(new TypeProperties(3, "volume", "four-doors"));

            fridge.setProperties(fridgeProperties);
            for (TypeProperties fridgeProperty : fridgeProperties) {
                fridgeProperty.setItemType(fridge);
            }
            itemPropertyRepository.saveAll(fridgeProperties);

            /* TV */
            ItemType tv = new ItemType(RoomType.SALON, "TV");
            itemTypeRepository.save(tv);

            HashSet<TypeProperties> tvProperties = new HashSet<>();
            tvProperties.add(new TypeProperties(1, "size", "<50\""));
            tvProperties.add(new TypeProperties(2, "size", "50\""));

            tv.setProperties(tvProperties);
            for (TypeProperties tvProperty : tvProperties) {
                tvProperty.setItemType(tv);
            }
            itemPropertyRepository.saveAll(tvProperties);

        }
    }
}

