package com.octo.commerce.cart.model;

import com.octo.commerce.pricing.model.SKUItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Holder of cart items.
 */
public class ShoppingCart {
    /**
     * Order Total.
     */
    private BigDecimal orderTotal;
    /**
     * Holder of sku items.
     */
    private List<SKUItem> skuItemList = new ArrayList<>();

    /**
     * Add a new sku item to the cart.
     *
     * @param skuItem - accepts a skuitem
     */
    public void add(final SKUItem skuItem) {
        skuItemList.add(skuItem);
    }

    /**
     * Get sku items list.
     *
     * @return list of skus
     */
    public List<SKUItem> getItems() {
        return skuItemList;
    }

    /**
     * set sku items.
     * @param skuItemList - set a list of skus
     */
    public void setSkuItemList(final List<SKUItem> skuItemList) {
        this.skuItemList = skuItemList;
    }
}
