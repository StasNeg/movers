package com.tomove.controller;

import com.tomove.common.DataTo;
import com.tomove.common.TotalCostEstimate;
import com.tomove.repository.AccountRepository;
import com.tomove.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.tomove.common.PathConstant.*;

@RestController
@CrossOrigin
public class CostEstimateController {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;

    @Value("cartonPrice")
    Integer cartonPrice;

    @Value("packetPrice")
    Integer packetPrice;

    @Autowired
    public CostEstimateController(AccountRepository accountRepository, RequestRepository requestRepository) {
        this.accountRepository = accountRepository;
        this.requestRepository = requestRepository;
    }

    @PostMapping(value = GET_TOTAL_COST_ESTIMATE)
    public DataTo getTotalCostEstimate(@RequestBody Map<String, String> data) {
        Integer totalCostEstimate = calculateCost(data);
        return new DataTo(true, new TotalCostEstimate(totalCostEstimate, cartonPrice, packetPrice)})
    }
}
