package com.octo.commerce.pricing.model;

public class DiscountCriteria {
    private final String skuId;
    private final int quantity;

    public DiscountCriteria(String skuId, int quantity) {
        this.skuId = skuId;
        this.quantity = quantity;
    }

    public String getSkuId() {
        return skuId;
    }

    public int getQuantity() {
        return quantity;
    }


}
