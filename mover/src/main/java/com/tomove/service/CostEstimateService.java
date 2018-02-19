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


    private static double PERCENT_PER_FLOOR_NO_LIFT = 0.025;
    private static double PERCENT_PER_FLOOR_YES_LIFT = 0.005;
    private static double PERCENT_PER_FLOOR_NO_LIFT_CAP = 0.15;
    private static double PERCENT_PER_FLOOR_YES_LIFT_CAP = 0.05;
    private static double PERCENT_IF_HOLIDAY = 1.05;

    @Value("${carSupplyPrice}")
    private Integer carSupplyPrice;
    @Value("${deliveryCost10}")
    private Integer deliveryCost10;
    @Value("${deliveryCost25}")
    private Integer deliveryCost25;
    @Value("${deliveryCost50}")
    private Integer deliveryCost50;
    @Value("${deliveryCost100}")
    private Integer deliveryCost100;
    @Value("${deliveryCostMoreThan100}")
    private Integer deliveryCostMoreThan100;

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
        int distance = 0;
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
        /* Calculate distance price */
        distance = calculateDistance(distances);
        distancePrice = (double)(distance * getPriceForDelivery(distance)*1.5);
        for (MoveDto move : data.getMoves()) {
            /* Calculate elevation price */
            coeffLift = 0.;
            List<AddressDto> moveAddresses = Arrays.asList(move.addressIn, move.addressOut);
            for (AddressDto moveAddress : moveAddresses) {
                Double coeffLiftMove = 0.;
                if (moveAddress.lift.equals("NO_LIFT")) {
                    coeffLiftMove = moveAddress.floor * PERCENT_PER_FLOOR_NO_LIFT;
                    if (coeffLiftMove >= PERCENT_PER_FLOOR_NO_LIFT_CAP) {
                        coeffLiftMove = PERCENT_PER_FLOOR_NO_LIFT_CAP;
                    }
                } else if (moveAddress.lift.equals("LIFT")) {
                    coeffLiftMove = moveAddress.floor * PERCENT_PER_FLOOR_YES_LIFT;
                    if (coeffLiftMove >= PERCENT_PER_FLOOR_YES_LIFT_CAP) {
                        coeffLiftMove = PERCENT_PER_FLOOR_YES_LIFT_CAP;
                    }
                }
                coeffLift += coeffLiftMove;
            }
            /* Calculate items price */
            itemsPrice = 0.;
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
            totalPrice += (itemsPrice * (coeffLift + coeffDate));
        }
        // FIXME: 15/01/2018 IS IT RIGHT TO ADD carSupplyPrice ONLY ONCE?
        return (int) (carSupplyPrice + distancePrice + totalPrice);
    }

    private int getPriceForDelivery(int distance) {
        if (distance < 10)
            return deliveryCost10;
        if (distance >= 10 && distance < 25)
            return deliveryCost25;
        if (distance >= 25 && distance < 50)
            return deliveryCost50;
        if (distance >= 50 && distance < 100)
            return deliveryCost100;
        else
            return deliveryCostMoreThan100;
    }

    private int calculateDistance(Integer[] distances) {
        int distance = 0;
        for (int i = 0; i < distances.length; i++) {
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
