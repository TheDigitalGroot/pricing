package com.octo.commerce.pricing.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * A discount type for combination items discounts.
 */
public class BuyXandYforAmt implements Discount {
    /**
     * Discount criteria.
     */
    private final Map<String, Integer> skuQtyRelation;
    /**
     * Reward Type.
     */
    private final RewardType rewardType;
    /**
     * Reward Amount.
     */
    private final BigDecimal rewardAmount;
    /**
     * Reward Desc.
     */
    private final String rewardDesc;

    /**
     * Constructor.
     *
     * @param skuQtyRelation - set a discount criteria
     * @param rewardType     -  set reward type
     * @param rewardAmount   - set reward amount
     * @param rewardDesc     - set reward desc
     */
    public BuyXandYforAmt(final Map<String, Integer> skuQtyRelation, final RewardType rewardType, final BigDecimal rewardAmount, final String rewardDesc) {
        this.skuQtyRelation = skuQtyRelation;
        this.rewardType = rewardType;
        this.rewardAmount = rewardAmount;
        this.rewardDesc = rewardDesc;
    }

    /**
     * get list of discount criterias.
     *
     * @return list
     */
    @Override
    public Map<String, Integer> getSkuQtyRelation() {
        return skuQtyRelation;
    }

    /**
     * get reward type.
     *
     * @return reward type
     */
    @Override
    public RewardType getRewardType() {
        return rewardType;
    }

    /**
     * get reward desc.
     *
     * @return reward desc
     */
    @Override
    public String getRewardDescription() {
        return rewardDesc;
    }

    /**
     * get reward amt.
     *
     * @return reward amt
     */
    @Override
    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }
}
