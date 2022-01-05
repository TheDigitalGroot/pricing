package com.octo.commerce.pricing.model;

import java.math.BigDecimal;
import java.util.List;

public class BuyXforAmt implements PriceDiscount {
    private List<DiscountCriteria> discountCriterias;
    private RewardType rewardType;
    private BigDecimal rewardAmount;
    private String rewardDesc;

    public BuyXforAmt(List<DiscountCriteria> discountCriterias, RewardType rewardType, BigDecimal rewardAmount, String rewardDesc) {
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
