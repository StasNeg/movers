package com.tomove.controller;

import com.tomove.common.DataTo;
import com.tomove.common.RequestData;
import com.tomove.common.TotalCostEstimate;
import com.tomove.service.CostEstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tomove.common.PathConstant.GET_TOTAL_COST_ESTIMATE;

@RestController
@CrossOrigin
public class CostEstimateController {


    private CostEstimateService costEstimateService;

    @Value("${cartonPrice}")
    Integer cartonPrice;

    @Value("${packetPrice}")
    Integer packetPrice;

    @Autowired
    public CostEstimateController(
            CostEstimateService costEstimateService) {
        this.costEstimateService = costEstimateService;
    }

    @PostMapping(value = GET_TOTAL_COST_ESTIMATE)
    public DataTo getTotalCostEstimate(@RequestBody RequestData data) {
        Integer totalCost = costEstimateService.calculateCost(data);
        return new DataTo(true, new TotalCostEstimate(totalCost, cartonPrice, packetPrice));
    }
}


