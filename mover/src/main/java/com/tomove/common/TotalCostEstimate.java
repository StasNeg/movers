package com.tomove.common;

public class TotalCostEstimate {
    Integer totalCost;
    Integer cartonPrice;
    Integer packetPrice;

    public TotalCostEstimate(Integer totalCost, Integer cartonPrice, Integer packetPrice) {
        this.totalCost = totalCost;
        this.cartonPrice = cartonPrice;
        this.packetPrice = packetPrice;
    }

    public TotalCostEstimate() {
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public Integer getCartonPrice() {
        return cartonPrice;
    }

    public Integer getPacketPrice() {
        return packetPrice;
    }
}
