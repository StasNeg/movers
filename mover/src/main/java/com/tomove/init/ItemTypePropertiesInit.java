package com.tomove.init;

import com.tomove.model.enums.RoomType;
import com.tomove.model.objectMover.ItemType;
import com.tomove.model.objectMover.TypeProperties;
import com.tomove.repository.ItemTypeRepository;
import com.tomove.repository.TypePropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemTypePropertiesInit implements ApplicationRunner {

    private ItemTypeRepository repository;
    private TypePropertiesRepository typeRepository;

    @Autowired
    public ItemTypePropertiesInit(ItemTypeRepository repository, TypePropertiesRepository typeRepository) {
        this.repository = repository;
        this.typeRepository = typeRepository;
    }

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String isInit;

    public void run(ApplicationArguments args) {
        if (!isInit.equals("none")) {
            ItemType general1 = new ItemType(RoomType.GENERAL, "table");
            ItemType general2 = new ItemType(RoomType.GENERAL, "chairs");
            ItemType kitchen1 = new ItemType(RoomType.KITCHEN, "table");
            ItemType kitchen2 = new ItemType(RoomType.KITCHEN, "refrigerator");
            ItemType room1 = new ItemType(RoomType.SALON, "refrigerator");
            ItemType room2 = new ItemType(RoomType.SALON, "tv");
            ItemType room3 = new ItemType(RoomType.SALON, "table");


            TypeProperties generalProperties = new TypeProperties("width","18 20, 40, 50", general1);
            TypeProperties generalProperties1 = new TypeProperties("width","18 20, 40, 50", general2);
            TypeProperties generalProperties3 = new TypeProperties("length","18 20, 40, 50", general1);
            TypeProperties kitchenProperties1 = new TypeProperties("type","big small", kitchen1);
            TypeProperties kitchenProperties2 = new TypeProperties("type","twoDoors oneDoors", kitchen2);
            TypeProperties kitchenProperties3 = new TypeProperties("material","steal wood", kitchen1);
            TypeProperties kitchenProperties4 = new TypeProperties("assembling","Assembling NoAssembling", kitchen1);

            List<ItemType> itempTypes = Arrays.asList(general1, general2, kitchen1,kitchen2,room1,room2,room3);
            List<TypeProperties> typesProperties = Arrays.asList(generalProperties,generalProperties1,generalProperties3,kitchenProperties1,kitchenProperties2,kitchenProperties3,kitchenProperties4);
            repository.saveAll(itempTypes);
            typeRepository.saveAll(typesProperties);
        }
    }
}
