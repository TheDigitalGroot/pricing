package com.octo.commerce.pricing;

import com.octo.commerce.pricing.model.Discount;
import com.octo.commerce.pricing.model.RewardType;
import com.octo.commerce.pricing.model.SKUItem;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Fixed Price Single item discount calculator.
 */
@Slf4j
public class FixedPriceDiscountCalculatorImpl implements DiscountCalculator {
    /**
     * applies fixed price discounts to single skuitem.
     * @param discountList - discount list
     * @param cartItems - sku items
     */
    @Override
    public void priceItems(final List<Discount> discountList, final List<SKUItem> cartItems) {
        cartItems.stream().filter(skuItem -> !skuItem.isDiscounted()).forEach(skuItem -> {
            discountList.stream().filter(discount -> discount.getRewardType().equals(RewardType.FiXED_SINGLE)).forEach(discount -> {
                        Integer qtyToBeDiscounted = discount.getSkuQtyRelation().get(skuItem.getSkuID());
                        if (!Objects.isNull(qtyToBeDiscounted)) {
                            int discountedTimes = skuItem.getQuantity() / qtyToBeDiscounted;
                            int nonDiscountedQty = skuItem.getQuantity() % qtyToBeDiscounted;
                            skuItem.setDiscounted(Boolean.TRUE);
                            skuItem.setItemSubTotal(discount.getReward().multiply(BigDecimal.valueOf(discountedTimes))
                                    .add(skuItem.getItemPrice().multiply(BigDecimal.valueOf(nonDiscountedQty))));
                        }
                    }
            );
        });
    }
}
