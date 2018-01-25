package com.tomove.controller;

import com.google.maps.*;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.tomove.common.*;
import com.tomove.controller.to.AddressDto;
import com.tomove.model.objectMover.Address;
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
        Double coeffElevator = 0.;
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

        for (Move move : data.getMoves()) {

            /* Calculate elevation price */
            List<AddressDto> moveAddresses = Arrays.asList(move.addressIn, move.addressOut);
            for (AddressDto moveAddress : moveAddresses) {

                Double coeffElevatorMove = 0.;

                if (!moveAddress.elevator) {
                    coeffElevatorMove += moveAddress.floor * PERCENT_PER_FLOOR_NO_LIFT;
                    if (coeffElevatorMove >= PERCENT_PER_FLOOR_NO_LIFT_CAP) {
                        coeffElevatorMove = PERCENT_PER_FLOOR_NO_LIFT_CAP;
                    }
                } else {
                    coeffElevatorMove += moveAddress.floor * PERCENT_PER_FLOOR_YES_LIFT;
                    if (coeffElevatorMove >= PERCENT_PER_FLOOR_YES_LIFT_CAP) {
                        coeffElevatorMove = PERCENT_PER_FLOOR_YES_LIFT_CAP;
                    }
                }
                coeffElevator += coeffElevatorMove;
            }

            /* Calculate distance price */
            distancePrice = getDistance(move.addressIn.getAddressString(), move.addressOut.getAddressString()) * PRICE_PER_KM;

            /* Calculate itemDtos price */
            for (ItemDto item : move.getItems()) {
                StringBuilder itemNameConcat = new StringBuilder();
                itemNameConcat.append(item.getName());
                for (Map.Entry<String, String> property : item.getProperties().entrySet()) {
                    itemNameConcat.append("_" + property.getKey() + "=" + property.getValue());
                }
                Double itemPrice = typePriceRepository.findByName(itemNameConcat.toString()).getPrice();
                itemsPrice += itemPrice;
            }
            // TODO: 18/01/2018 HOW TO COVER CALCULATION WITH TESTS?
            /* Calculate total price */
            totalPrice += (distancePrice + itemsPrice) * coeffElevator * coeffDate;
        }

        // FIXME: 15/01/2018 IS IT RIGHT TO ADD carSupplyPrice ONLY ONCE?
        // FIXME: 18/01/2018 IS IT RIGHT TO RETURN INT OR DOUBLE?
        return (int) (carSupplyPrice + totalPrice);
    }

    private Integer getDistance(String addressIn, String addressOut) {

        String API_KEY = "AIzaSyAz9zX-L__RluZ8C3hgBWVE3YSoiprfuYs";

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        DirectionsResult results = new DirectionsResult();
        try {
            results = new DirectionsApiRequest(context)
                    .origin(addressIn)
                    .destination(addressOut)
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


