package com.octo.commerce.pricing.model;

/**
 * The criteria to identify discounts.
 */
public class DiscountCriteria {
    /**
     * sku id.
     */
    private final String skuId;
    /**
     * quantity.
     */
    private final Integer quantity;

    /**
     * Constructor.
     * @param skuId - set a skuid
     * @param quantity - set a qty
     */
    public DiscountCriteria(final String skuId, final Integer quantity) {
        this.skuId = skuId;
        this.quantity = quantity;
    }

    /**
     * get a skuid.
     * @return skuid
     */
    public String getSkuId() {
        return skuId;
    }

    /**
     * get the qty.
     * @return qty
     */
    public Integer getQuantity() {
        return quantity;
    }


}
