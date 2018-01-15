package com.tomove.controller;

import com.google.maps.*;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.tomove.common.DataTo;
import com.tomove.common.Move;
import com.tomove.common.RequestData;
import com.tomove.common.TotalCostEstimate;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class CostEstimateController {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;

    private static double PRICE_PER_KM = 30;
    private static double PERCENT_PER_FLOOR_NO_LIFT = 1.03;
    private static double PERCENT_PER_FLOOR_YES_LIFT = 1.01;
    private static double PERCENT_IF_HOLIDAY = 1.05;

    @Value("${cartonPrice}")
    Integer cartonPrice;

    @Value("${packetPrice}")
    Integer packetPrice;

    @Value("${carSupplyPrice}")
    Integer carSupplyPrice;

    @Autowired
    public CostEstimateController(AccountRepository accountRepository, RequestRepository requestRepository) {
        this.accountRepository = accountRepository;
        this.requestRepository = requestRepository;
    }

    @PostMapping(value = GET_TOTAL_COST_ESTIMATE)
    public DataTo getTotalCostEstimate(@RequestBody RequestData data) {
        Integer totalCost = calculateCost(data);
        return new DataTo(true, new TotalCostEstimate(totalCost, cartonPrice, packetPrice));
    }

    private Integer calculateCost(RequestData data) {

        Double dateCoeff = 1.;
        Double elevatorCoeff = 0.;
        Double distancePrice = 0.;
        Double itemsPrice = 0.;

        LocalDate date = LocalDate.parse(data.date);

        // TODO CHECK FOR HOLIDAY
        /**  В даты с 1 по 8 и с 24 по 31, а так же в прздничные дни + 5% */

        List holidays = new ArrayList();
        if (holidays.contains(date)) {
            dateCoeff = PERCENT_IF_HOLIDAY;
        }

        for (Move move : data.getMoves()) {

            // TODO CAP AT 15%(YES_ELEV) AND 5%(NO_ELEV)
            /* Calculate elevation price */
            if (!move.addressIn.elevator) {
                elevatorCoeff += move.addressIn.floor*PERCENT_PER_FLOOR_NO_LIFT;
            } else {
                elevatorCoeff += move.addressIn.floor*PERCENT_PER_FLOOR_YES_LIFT;
            }
            if (!move.addressOut.elevator) {
                elevatorCoeff += move.addressOut.floor*PERCENT_PER_FLOOR_NO_LIFT;
            } else {
                elevatorCoeff += move.addressOut.floor*PERCENT_PER_FLOOR_YES_LIFT;
            }

            distancePrice += getDistance(move.addressIn.address, move.addressOut.address) * PRICE_PER_KM;
            itemsPrice += move.getItems().size() * 3.;
            //TODO CALCULATE PRICE FOR ALL ITEMS
        }

        //// FIXME: 15/01/2018 carSupplyPrice only once?
        return (int) ((carSupplyPrice + distancePrice + itemsPrice)*elevatorCoeff*dateCoeff);
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
//        System.out.println((int) (results.routes[0].legs[0].distance.inMeters) / 1000);

        return ((int) results.routes[0].legs[0].distance.inMeters) / 1000;
    }
}


