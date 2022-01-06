package com.octo.commerce.pricing.model;

import lombok.ToString;

import java.math.BigDecimal;

/**
 * Represent a cart item.
 */
@ToString
public class SKUItem {
    /**
     * The sku identifier.
     */
    private final String skuID;
    /**
     * The sku price.
     */
    private final BigDecimal itemPrice;
    /**
     * The sku quantity.
     */
    private final Integer quantity;

    /**
     * The item subTotal.
     */
    private BigDecimal itemSubTotal;
    /**
     * The discountedFlag.
     */
    private boolean discounted;

    /**
     * SKU Item constructor.
     *
     * @param skuID     - accept skuID
     * @param itemPrice - accepts skuPrice
     * @param quantity  - accepts qty
     */
    public SKUItem(final String skuID, final BigDecimal itemPrice, final int quantity) {
        this.skuID = skuID;
        this.itemPrice = itemPrice;
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
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * qty getter.
     *
     * @return qty
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * get item subtotal.
     *
     * @return itemsubtotal
     */
    public BigDecimal getItemSubTotal() {
        return itemSubTotal;
    }

    /**
     * set item subtotal.
     *
     * @param itemSubTotal - set itemsubtotal
     */
    public void setItemSubTotal(final BigDecimal itemSubTotal) {
        this.itemSubTotal = itemSubTotal;
    }

    /**
     * discounted boolean flag.
     *
     * @return discounted state
     */
    public boolean isDiscounted() {
        return discounted;
    }

    /**
     * set discounted flag.
     *
     * @param discounted - accepts a boolean
     */
    public void setDiscounted(final boolean discounted) {
        this.discounted = discounted;
    }


}
