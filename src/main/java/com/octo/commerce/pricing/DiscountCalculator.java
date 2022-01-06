package com.octo.commerce.pricing;

import com.octo.commerce.pricing.model.Discount;
import com.octo.commerce.pricing.model.SKUItem;

import java.util.List;

/**
 * Discount Calculator interface.
 */
public interface DiscountCalculator {
    /**
     * Common strategy to priceitems.
     * @param discounts - list of discounts
     * @param skuItemList - list of skuitems
     */
    void priceItems(List<Discount> discounts, List<SKUItem> skuItemList);
}
