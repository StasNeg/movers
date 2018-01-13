package com.tomove.controller;

import com.google.maps.*;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.tomove.common.DataTo;
import com.tomove.common.RequestData;
import com.tomove.common.TotalCostEstimate;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class CostEstimateController {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;

    private static double PRICE_PER_KM = 30;

    @Value("${cartonPrice}")
    Integer cartonPrice;

    @Value("${packetPrice}")
    Integer packetPrice;

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
        Double distancePrice = getDistance(data.getItems().get(0).getAddressIn(), data.getItems().get(0).getAddressOut())*PRICE_PER_KM;
        //TODO CALCULATE PRICE FOR ALL ITEMS AND LIFTS
        return distancePrice.intValue();
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

        return ((int) results.routes[0].legs[0].distance.inMeters) / 1000;
    }
}


