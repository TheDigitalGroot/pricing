package com.octo.commerce.pricing.model;

/**
 * Enum type to differentiate Reward type.
 */
public enum RewardType {
    /**
     * Fixed Discount on single.
     */
    FiXED_SINGLE,
    /**
     * Fixed Discount on combinations.
     */
    FiXED_COMBO,
    /**
     * Percentage Discount on single item.
     */
    PERCENT_SINGLE,
    /**
     * Percentage Discount on a combination of items.
     */
    PERCENT_COMBO
}
