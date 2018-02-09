package com.tomove.init;

import com.tomove.model.enums.RoomType;
import com.tomove.model.objectMover.*;
import com.tomove.repository.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


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
            Map<String, ItemToAdd> items = new HashMap<>();
            int currentRow = 1;
            Row row;
            DataFormatter formatter = new DataFormatter();

            while (sheet.getRow(currentRow) != null) {
                row = sheet.getRow(currentRow);
                String roomType = row.getCell(0).getStringCellValue();
                String itemName = row.getCell(2).getStringCellValue();
                String propertyType = row.getCell(3).getStringCellValue();
                String propertyTypeValue = formatter.formatCellValue(row.getCell(4));
                String key = itemName + propertyType;
                if (items.containsKey(key)) {

                    items.get(key).propertyTypeValue = items.get(key).propertyTypeValue + "|" + propertyTypeValue;
                } else {
                    items.put(key, new ItemToAdd(roomType, itemName, propertyType, propertyTypeValue));
                }
                Double price = row.getCell(6).getNumericCellValue();
                String typePriceName = itemName + "_" + propertyType + "=" + propertyTypeValue;
                typePriceRepository.save(new TypePrice(typePriceName, price));
                currentRow++;
            }
            addItemToDatabase(items);
        }
    }

    private class ItemToAdd {
        private String roomType = "";
        private String itemName = "";
        private String propertyType = "";
        private String propertyTypeValue = "";

        public ItemToAdd(String roomType, String itemName, String propertyType, String propertyTypeValue) {
            this.roomType = roomType;
            this.itemName = itemName;
            this.propertyType = propertyType;
            this.propertyTypeValue = propertyTypeValue;
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
        } else {
            typePriceRepository.save(new TypePrice(itemName, price));
        }
    }

    private void addItemToDatabase(Map<String, ItemToAdd> items) {
        for (ItemToAdd item : items.values()) {
            ItemType _itemType;
            if (itemTypeRepository.findByName(item.itemName).orElse(null) == null) {
                _itemType = itemTypeRepository.save(new ItemType(RoomType.valueOf(item.roomType), item.itemName));
            } else {
                _itemType = itemTypeRepository.findByName(item.itemName).get();
            }
            if (!item.propertyType.equals("")) {
                itemPropertyRepository.save(new TypeProperties(item.propertyType, item.propertyTypeValue, _itemType));
            }
        }
    }
}

