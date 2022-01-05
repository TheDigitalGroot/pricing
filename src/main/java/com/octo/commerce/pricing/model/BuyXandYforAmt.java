package com.octo.commerce.pricing.model;

import java.math.BigDecimal;
import java.util.List;

public class BuyXandYforAmt implements PriceDiscount {
    private final List<DiscountCriteria> discountCriterias;
    private final RewardType rewardType;
    private final BigDecimal rewardAmount;
    private final String rewardDesc;

    public BuyXandYforAmt(List<DiscountCriteria> discountCriterias, RewardType rewardType, BigDecimal rewardAmount, String rewardDesc) {
        this.discountCriterias = discountCriterias;
        this.rewardType = rewardType;
        this.rewardAmount = rewardAmount;
        this.rewardDesc = rewardDesc;
    }

    @Override
    public List<DiscountCriteria> getDiscountCriteria() {
        return discountCriterias;
    }

    @Override
    public RewardType getRewardType() {
        return rewardType;
    }

    @Override
    public String getRewardDescription() {
        return rewardDesc;
    }

    @Override
    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }
}
