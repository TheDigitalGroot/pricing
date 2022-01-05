package com.octo.commerce.pricing.model;

import java.math.BigDecimal;

/**
 * A discount type for combination items discounts.
 */
public class BuyXandYforAmt implements PriceDiscount {
    /**
     * Discount criteria.
     */
    private final DiscountCriteria discountCriteria;
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
     * @param discountCriteria - set a discount criteria
     * @param rewardType -  set reward type
     * @param rewardAmount - set reward amount
     * @param rewardDesc - set reward desc
     */
    public BuyXandYforAmt(final DiscountCriteria discountCriteria, final RewardType rewardType, final BigDecimal rewardAmount, final String rewardDesc) {
        this.discountCriteria = discountCriteria;
        this.rewardType = rewardType;
        this.rewardAmount = rewardAmount;
        this.rewardDesc = rewardDesc;
    }

    /**
     * get list of discount criterias.
     * @return list
     */
    @Override
    public DiscountCriteria getDiscountCriteria() {
        return discountCriteria;
    }

    /**
     * get reward type.
     * @return reward type
     */
    @Override
    public RewardType getRewardType() {
        return rewardType;
    }

    /**
     * get reward desc.
     * @return reward desc
     */
    @Override
    public String getRewardDescription() {
        return rewardDesc;
    }

    /**
     * get reward amt.
     * @return reward amt
     */
    @Override
    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }
}
