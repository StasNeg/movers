package com.tomove.controller;

import com.google.maps.*;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.tomove.common.*;
import com.tomove.common.AddressDto;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.RequestRepository;
import com.tomove.repository.TypePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class CostEstimateController {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;
    private TypePriceRepository typePriceRepository;

    private static double PRICE_PER_KM = 30;
    private static double PERCENT_PER_FLOOR_NO_LIFT = 1.03;
    private static double PERCENT_PER_FLOOR_YES_LIFT = 1.01;
    private static double PERCENT_PER_FLOOR_NO_LIFT_CAP = 1.15;
    private static double PERCENT_PER_FLOOR_YES_LIFT_CAP = 1.05;
    private static double PERCENT_IF_HOLIDAY = 1.05;

    @Value("${cartonPrice}")
    Integer cartonPrice;

    @Value("${packetPrice}")
    Integer packetPrice;

    @Value("${carSupplyPrice}")
    Integer carSupplyPrice;

    @Autowired
    public CostEstimateController(
            AccountRepository accountRepository,
            RequestRepository requestRepository,
            TypePriceRepository typePriceRepository) {
        this.accountRepository = accountRepository;
        this.requestRepository = requestRepository;
        this.typePriceRepository = typePriceRepository;
    }

    @PostMapping(value = GET_TOTAL_COST_ESTIMATE)
    public DataTo getTotalCostEstimate(@RequestBody RequestData data) {
        Integer totalCost = calculateCost(data);
        return new DataTo(true, new TotalCostEstimate(totalCost, cartonPrice, packetPrice));
    }

    private Integer calculateCost(RequestData data) {

        Double coeffDate = 1.;
        Double coeffLift = 0.;
        Double distancePrice = 0.;
        Double itemsPrice = 0.;
        Double totalPrice = 0.;

        LocalDate date = LocalDate.parse(data.move_date);

        // TODO CHECK FOR HOLIDAY
        /**  В даты с 1 по 8 и с 24 по 31, а так же в прздничные дни + 5% */

        List holidays = new ArrayList();
        if (holidays.contains(date)) {
            coeffDate = PERCENT_IF_HOLIDAY;
        }

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
            distancePrice = getDistance(move.addressIn, move.addressOut) * PRICE_PER_KM;

            // TODO: 07/02/2018 INCLUDE DISTANCE FROM ADDRESSES 0-1 AND 1-2 WHEN MOVING ITEM 0-2, VISITING ADDRESS 1. NOW ONLY 0-2 IS CALCULATED
            /* Calculate items price */
            for (RoomDto room : move.getRooms()) {
                for (ItemDto item : room.getItems()) {
                    StringBuilder itemNameConcat = new StringBuilder();
                    itemNameConcat.append(item.getName());
                    if (item.getProperties() !=null) {
                        for (Map.Entry<String, String> property : item.getProperties().entrySet()) {
                            itemNameConcat.append("_" + property.getKey() + "=" + property.getValue());
                        }
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

    private Integer getDistance(AddressDto addressIn, AddressDto addressOut) {

        String API_KEY = "AIzaSyAz9zX-L__RluZ8C3hgBWVE3YSoiprfuYs";

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        DirectionsResult results = new DirectionsResult();
        try {
            results = new DirectionsApiRequest(context)
                    .origin(new LatLng(addressIn.latitude, addressIn.longitude))
                    .destination(new LatLng(addressOut.latitude, addressOut.longitude))
                    .mode(TravelMode.DRIVING)
                    .await();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (results.routes == null) {
            System.out.println("Null response from google API");
            return 0;
        } else {
            return ((int) results.routes[0].legs[0].distance.inMeters) / 1000;
        }
    }
}


