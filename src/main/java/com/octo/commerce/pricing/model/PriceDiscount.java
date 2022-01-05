package com.octo.commerce.pricing.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Discount Super type.
 */
public interface PriceDiscount {
    /**
     * Gets the list of discount criterias.
     * @return list
     */
    List<DiscountCriteria> getDiscountCriteria();

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
