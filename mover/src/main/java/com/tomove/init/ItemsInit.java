package com.tomove.init;

import com.tomove.model.enums.RoomType;
import com.tomove.model.objectMover.*;
import com.tomove.model.subjectMover.Account;
import com.tomove.model.subjectMover.Customer;
import com.tomove.model.subjectMover.Mover;
import com.tomove.repository.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class ItemsInit implements ApplicationRunner {

    private ItemRepository itemRepository;
    private ItemTypeRepository itemTypeRepository;
    private ItemPropertyRepository itemPropertyRepository;
    private TypePriceRepository typePriceRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String isInit;

    @Autowired
    public ItemsInit(ItemRepository itemRepository,
                     ItemTypeRepository itemTypeRepository,
                     ItemPropertyRepository itemPropertyRepository,
                     TypePriceRepository typePriceRepository
    ) {
        this.itemRepository = itemRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.itemPropertyRepository = itemPropertyRepository;
        this.typePriceRepository = typePriceRepository;
    }

    public void run(ApplicationArguments args) throws IOException, InvalidFormatException {
        if (!isInit.equals("none")) {

            /* Load data from xls file to database */
            InputStream inp = getClass().getResourceAsStream("/items.xlsx");
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);

            int currentRow = 1;
            Row row;
            DataFormatter formatter = new DataFormatter();

            while (sheet.getRow(currentRow) != null) {
                row = sheet.getRow(currentRow);
                String roomType = row.getCell(0).getStringCellValue();
                String itemName = row.getCell(2).getStringCellValue();
                String propertyType = row.getCell(3).getStringCellValue();
                String propertyTypeValue = formatter.formatCellValue(row.getCell(4));
                Double price = row.getCell(6).getNumericCellValue();

                addItemToDatabase(roomType, itemName, propertyType, propertyTypeValue, price);

                currentRow++;
            }

//            /* CUPBOARD */
//            ItemType cupBoard = new ItemType(RoomType.BEDROOM, "Cupboard");
//            itemTypeRepository.save(cupBoard);
//
//            HashSet<TypeProperties> cupBoardProperties = new HashSet<>();
//            cupBoardProperties.add(new TypeProperties(1, "doors", "one"));
//            cupBoardProperties.add(new TypeProperties(2, "doors", "two"));
//            cupBoardProperties.add(new TypeProperties(3, "task", "disassemble-assemble"));
//            cupBoardProperties.add(new TypeProperties(4, "task", "moveDown-moveUp"));
//
//            cupBoard.setProperties(cupBoardProperties);
//            for (TypeProperties cupBoardProperty : cupBoardProperties) {
//                cupBoardProperty.setItemType(cupBoard);
//            }
//            itemPropertyRepository.saveAll(cupBoardProperties);
//
//            // save prices
//            List<TypePrice> typePrices = Arrays.asList(
//                    new TypePrice("Cupboard_doors=one", 10.),
//                    new TypePrice("Cupboard_doors=two", 15.),
//                    new TypePrice("Cupboard_task=disassemble-assemble", 20.),
//                    new TypePrice("Cupboard_task=moveDown-moveUp", 25.),
//                    new TypePrice("Cupboard_doors=one_task=moveDown-moveUp", 35.)
//            );
//
//            typePriceRepository.saveAll(typePrices);
//
//            /* FRIDGE */
//            ItemType fridge = new ItemType(RoomType.KITCHEN, "Fridge");
//            itemTypeRepository.save(fridge);
//
//            HashSet<TypeProperties> fridgeProperties = new HashSet<>();
//            fridgeProperties.add(new TypeProperties(1, "volume", "<400L"));
//            fridgeProperties.add(new TypeProperties(2, "volume", "side-by-side"));
//            fridgeProperties.add(new TypeProperties(3, "volume", "four-doors"));
//
//            fridge.setProperties(fridgeProperties);
//            for (TypeProperties fridgeProperty : fridgeProperties) {
//                fridgeProperty.setItemType(fridge);
//            }
//            itemPropertyRepository.saveAll(fridgeProperties);
//
//            // save prices
//            typePrices = Arrays.asList(
//                    new TypePrice("Fridge_volume=<400L", 10.),
//                    new TypePrice("Fridge_volume=side-by-side", 15.),
//                    new TypePrice("Fridge_volume=four-doors", 20.)
//            );
//
//            typePriceRepository.saveAll(typePrices);
//
//            /* TV */
//            ItemType tv = new ItemType(RoomType.SALON, "TV");
//            itemTypeRepository.save(tv);
//
//            HashSet<TypeProperties> tvProperties = new HashSet<>();
//            tvProperties.add(new TypeProperties(1, "size", "<50\""));
//            tvProperties.add(new TypeProperties(2, "size", "50\""));
//
//            tv.setProperties(tvProperties);
//            for (TypeProperties tvProperty : tvProperties) {
//                tvProperty.setItemType(tv);
//            }
//            itemPropertyRepository.saveAll(tvProperties);
//
//            // save prices
//            typePrices = Arrays.asList(
//                    new TypePrice("TV_size=<50", 10.),
//                    new TypePrice("TV_size=50\"", 15.)
//            );
//
//            typePriceRepository.saveAll(typePrices);

        }
    }


    private void addItemToDatabase(String roomType, String itemName, String propertyType, String propertyTypeValue, Double price) {
        ItemType _itemType;
        if (itemTypeRepository.findByName(itemName).orElse(null) == null) {
            _itemType = itemTypeRepository.save(new ItemType(RoomType.valueOf(roomType), itemName));
        } else {
            _itemType = itemTypeRepository.findByName(itemName).get();
        }

        if (!propertyType.equals("")) {
            itemPropertyRepository.save(new TypeProperties(propertyType, propertyTypeValue, _itemType));
            String typePriceName = itemName + "_" + propertyType + "=" + propertyTypeValue;
            typePriceRepository.save(new TypePrice(typePriceName, price));
        } else {
            typePriceRepository.save(new TypePrice(itemName, price));
        }
    }
}

