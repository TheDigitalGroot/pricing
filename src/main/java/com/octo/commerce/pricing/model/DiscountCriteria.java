package com.octo.commerce.pricing.model;

import java.util.Map;

/**
 * The criteria to identify discounts.
 */
public class DiscountCriteria {
    /**
     * sku id.
     */
    private final Map<String, Integer> discountCriteriaMap;

    /**
     * Constructor.
     *
     * @param discountCriteriaMap - maps
     */
    public DiscountCriteria(final Map<String, Integer> discountCriteriaMap) {
        this.discountCriteriaMap = discountCriteriaMap;
    }


}
