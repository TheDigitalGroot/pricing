package com.octo.commerce.pricing.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * A discount type for single items discounts.
 */
public class BuyXforAmt implements PriceDiscount {
    /**
     * List of Discount criteria.
     */
    private final List<DiscountCriteria> discountCriterias;
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
     * @param discountCriterias - set a list
     * @param rewardType - set reward type
     * @param rewardAmount - set a reward amount
     * @param rewardDesc - set reward desc
     */
    public BuyXforAmt(final List<DiscountCriteria> discountCriterias, final RewardType rewardType, final BigDecimal rewardAmount, final String rewardDesc) {
        this.discountCriterias = discountCriterias;
        this.rewardType = rewardType;
        this.rewardAmount = rewardAmount;
        this.rewardDesc = rewardDesc;
    }

    /**
     * get list of discount criterias.
     * @return list
     */
    @Override
    public List<DiscountCriteria> getDiscountCriteria() {
        return discountCriterias;
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
