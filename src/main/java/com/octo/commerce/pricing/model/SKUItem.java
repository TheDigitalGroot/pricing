package com.octo.commerce.pricing.model;

import java.math.BigDecimal;

/**
 * Represent a cart item.
 */
public class SKUItem {
    /**
     * The sku identifier.
     */
    private String skuID;
    /**
     * The sku price.
     */
    private BigDecimal skuPrice;
    /**
     * The sku quantity.
     */
    private int quantity;

    /**
     * SKU Item constructor.
     *
     * @param skuID    - accept skuID
     * @param skuPrice - accepts skuPrice
     * @param quantity - accepts qty
     */
    public SKUItem(final String skuID, final BigDecimal skuPrice, final int quantity) {
        this.skuID = skuID;
        this.skuPrice = skuPrice;
        this.quantity = quantity;
    }
}
