package com.octo.commerce.pricing.model;

import java.math.BigDecimal;

/**
 * Discount Super type.
 */
public interface PriceDiscount {
    /**
     * Gets the discount criterias.
     * @return discount criteria
     */
    DiscountCriteria getDiscountCriteria();

    /**
     * Get the reward type.
     * @return reward type
     */
    RewardType getRewardType();

    /**
     * Get reward desc.
     * @return desc
     */
    String getRewardDescription();

    /**
     * Get reward amt.
     * @return amt
     */
    BigDecimal getRewardAmount();
}
