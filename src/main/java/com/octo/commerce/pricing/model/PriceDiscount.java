package com.octo.commerce.pricing.model;

import java.math.BigDecimal;
import java.util.List;

public interface PriceDiscount {
    List<DiscountCriteria> getDiscountCriteria();

    RewardType getRewardType();

    String getRewardDescription();

    BigDecimal getRewardAmount();
}
