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
    private final BigDecimal itemPrice;
    /**
     * The sku quantity.
     */
    private final int quantity;
    /**
     * The discounted sku price.
     */
    private BigDecimal salesPrice;

    /**
     * The item subTotal.
     */
    private BigDecimal itemSubTotal;

    /**
     * SKU Item constructor.
     *
     * @param skuID    - accept skuID
     * @param itemPrice - accepts skuPrice
     * @param quantity - accepts qty
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
    public int getQuantity() {
        return quantity;
    }

    /**
     * get discounted unit price.
     * @return discounted unit price
     */
    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    /**
     * set discount unit price.
     * @param salesPrice - set discount unit price
     */
    public void setSalesPrice(final BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    /**
     * get item subtotal.
     * @return itemsubtotal
     */
    public BigDecimal getItemSubTotal() {
        return itemSubTotal;
    }

    /**
     * set item subtotal.
     * @param itemSubTotal - set itemsubtotal
     */
    public void setItemSubTotal(final BigDecimal itemSubTotal) {
        this.itemSubTotal = itemSubTotal;
    }
}
