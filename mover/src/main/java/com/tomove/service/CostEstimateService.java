package com.tomove.service;


import com.tomove.common.*;
import com.tomove.repository.TypePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CostEstimateService {

    private TypePriceRepository typePriceRepository;

    private static double PRICE_PER_KM = 5;
    private static double PERCENT_PER_FLOOR_NO_LIFT = 1.03;
    private static double PERCENT_PER_FLOOR_YES_LIFT = 1.01;
    private static double PERCENT_PER_FLOOR_NO_LIFT_CAP = 1.15;
    private static double PERCENT_PER_FLOOR_YES_LIFT_CAP = 1.05;
    private static double PERCENT_IF_HOLIDAY = 1.05;

    @Value("${carSupplyPrice}")
    private Integer carSupplyPrice;

    @Autowired
    public CostEstimateService(TypePriceRepository typePriceRepository) {
        this.typePriceRepository = typePriceRepository;
    }

    public Integer calculateCost(RequestData data) {

        Double coeffDate = 1.;
        Double coeffLift = 0.;
        Double distancePrice = 0.;
        Double itemsPrice = 0.;
        Double totalPrice = 0.;
        //for distances between addresses
        Integer distances[] = new Integer[data.addresses.size() - 1];
        LocalDate date = LocalDate.parse(data.move_date);

        // TODO CHECK FOR HOLIDAY
        /**  В даты с 1 по 8 и с 24 по 31, а так же в прздничные дни + 5% */
        List holidays = new ArrayList();
        if (holidays.contains(date)) {
            coeffDate = PERCENT_IF_HOLIDAY;
        }
//        init distance array
        initDistances(distances, data.addresses);

        for (MoveDto move : data.getMoves()) {
            /* Calculate elevation price */
            List<AddressDto> moveAddresses = Arrays.asList(move.addressIn, move.addressOut);
            for (AddressDto moveAddress : moveAddresses) {
                Double coeffLiftMove = 0.;
                if (moveAddress.lift.equals("NO_LIFT")) {
                    coeffLiftMove = moveAddress.floor * PERCENT_PER_FLOOR_NO_LIFT / 100;
                    if (coeffLiftMove >= PERCENT_PER_FLOOR_NO_LIFT_CAP) {
                        coeffLiftMove = PERCENT_PER_FLOOR_NO_LIFT_CAP;
                    }
                } else if (moveAddress.lift.equals("LIFT")) {
                    coeffLiftMove = moveAddress.floor * PERCENT_PER_FLOOR_YES_LIFT / 100;
                    if (coeffLiftMove >= PERCENT_PER_FLOOR_YES_LIFT_CAP) {
                        coeffLiftMove = PERCENT_PER_FLOOR_YES_LIFT_CAP;
                    }
                }
                coeffLift += coeffLiftMove;
            }
            /* Calculate distance price */
            distancePrice = calculateDistance(distances, move.addressIn, move.addressOut) * PRICE_PER_KM;
            /* Calculate items price */
            for (RoomDto room : move.getRooms()) {
                for (ItemDto item : room.getItems()) {
                    StringBuilder itemNameConcat = new StringBuilder();
                    itemNameConcat.append(item.getName());
                    if (item.getProperties() != null) {
                        itemNameConcat.append(item.getProperties());
                    }
                    Double itemPrice = typePriceRepository.findByName(itemNameConcat.toString()).getPrice();
                    itemsPrice += itemPrice;
                }
            }
            /* Calculate total price */
            totalPrice += (distancePrice + itemsPrice) * (coeffLift + coeffDate);
        }
        // FIXME: 15/01/2018 IS IT RIGHT TO ADD carSupplyPrice ONLY ONCE?
        return (int) (carSupplyPrice + totalPrice + 0.5);
    }

    private int calculateDistance(Integer[] distances, AddressDto addressIn, AddressDto addressOut) {
        int distance = 0;
        for (int i = addressOut.seqnumber; i < addressIn.seqnumber; i++) {
            distance += distances[i];
        }
        return distance;
    }

    private void initDistances(Integer[] distances, List<AddressDto> addresses) {
        for (int i = 0; i < addresses.size() - 1; i++) {
            distances[i] = GeoApiService.getDistance(addresses.get(i), addresses.get(i + 1));
        }
    }

}
