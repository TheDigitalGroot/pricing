package com.octo.commerce.pricing.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Discount Super type.
 */
public interface Discount {
    /**
     * get sku to qty relation.
     *
     * @return relation
     */
    Map<String, Integer> getSkuQtyRelation();

    /**
     * Get the reward type.
     *
     * @return reward type
     */
    RewardType getRewardType();

    /**
     * Get reward desc.
     *
     * @return desc
     */
    String getRewardDescription();

    /**
     * Get reward in fixed amount or percentage per SKU based on qty.
     *
     * @return amt
     */
    BigDecimal getReward();
}
