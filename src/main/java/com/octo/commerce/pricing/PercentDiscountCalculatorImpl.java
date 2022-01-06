package com.octo.commerce.pricing;

import com.octo.commerce.pricing.model.Discount;
import com.octo.commerce.pricing.model.RewardType;
import com.octo.commerce.pricing.model.SKUItem;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Percent Discount Calculator.
 */
@Slf4j
public class PercentDiscountCalculatorImpl implements DiscountCalculator {
    /**
     * Applies percent discount to the sku price.
     * @param discountList - list of discounts
     * @param skuItemList - list of skuitems
     */
    @Override
    public void priceItems(final List<Discount> discountList, final List<SKUItem> skuItemList) {
        skuItemList.stream().filter(skuItem -> !skuItem.isDiscounted()).forEach(skuItem -> {
            discountList.stream().filter(discount -> discount.getRewardType().equals(RewardType.PERCENT_SINGLE)).forEach(discount -> {
                        Integer qtyToBeDiscounted = discount.getSkuQtyRelation().get(skuItem.getSkuID());
                        if (!Objects.isNull(qtyToBeDiscounted)) {
                            int nonDiscountedQty = skuItem.getQuantity() - qtyToBeDiscounted;
                            skuItem.setDiscounted(Boolean.TRUE);
                            skuItem.setItemSubTotal(discount.getReward().multiply(skuItem.getItemPrice())
                                    .multiply(BigDecimal.valueOf(qtyToBeDiscounted))
                                    .add(skuItem.getItemPrice().multiply(BigDecimal.valueOf(nonDiscountedQty))));
                        }
                    }
            );
        });
    }
}
