package com.octo.commerce.pricing.model;

import java.math.BigDecimal;

/**
 * Represent a cart item.
 */
public class SKUItem {
    /**
     * The sku identifier.
     */
    private final String skuID;
    /**
     * The sku price.
     */
    private final BigDecimal skuUnitPrice;
    /**
     * The sku quantity.
     */
    private final int quantity;
    /**
     * The discounted sku price.
     */
    private BigDecimal discountedUnitSkuPrice;

    /**
     * SKU Item constructor.
     *
     * @param skuID    - accept skuID
     * @param skuUnitPrice - accepts skuPrice
     * @param quantity - accepts qty
     */
    public SKUItem(final String skuID, final BigDecimal skuUnitPrice, final int quantity) {
        this.skuID = skuID;
        this.skuUnitPrice = skuUnitPrice;
        this.quantity = quantity;
    }

    /**
     * skuid getter.
     *
     * @return skuid
     */
    public String getSkuID() {
        return skuID;
    }

    /**
     * skuprice getter.
     *
     * @return skuprice
     */
    public BigDecimal getSkuUnitPrice() {
        return skuUnitPrice;
    }

    /**
     * qty getter.
     *
     * @return qty
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * get discounted unit price.
     * @return discounted unit price
     */
    public BigDecimal getDiscountedUnitSkuPrice() {
        return discountedUnitSkuPrice;
    }

    /**
     * set discount unit price.
     * @param discountedUnitSkuPrice - set discount unit price
     */
    public void setDiscountedUnitSkuPrice(final BigDecimal discountedUnitSkuPrice) {
        this.discountedUnitSkuPrice = discountedUnitSkuPrice;
    }
}
