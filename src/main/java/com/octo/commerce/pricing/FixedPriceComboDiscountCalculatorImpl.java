package com.octo.commerce.pricing;

import com.octo.commerce.pricing.model.Discount;
import com.octo.commerce.pricing.model.RewardType;
import com.octo.commerce.pricing.model.SKUItem;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Fixed Price Combo Discount Calculator Implementation.
 */
@Slf4j
public class FixedPriceComboDiscountCalculatorImpl implements DiscountCalculator {
    /**
     * Only discounts items which are present in two or more quantities to affect a discount.
     * @param discountList - list of discounts
     * @param skuItemList - list of skuitems
     */
    @Override
    public void priceItems(final List<Discount> discountList, final List<SKUItem> skuItemList) {
        List<SKUItem> nonDiscountedItems =
                skuItemList.stream().filter(skuItem -> !skuItem.isDiscounted()).collect(Collectors.toList());

        discountList.stream().filter(discount -> discount.getRewardType().equals(RewardType.FiXED_COMBO)).forEach(discount -> {
                    List<String> comboSkuList = new ArrayList<>(discount.getSkuQtyRelation().keySet());
                    List<String> nonDiscountedSkuList =
                            nonDiscountedItems.stream().map(SKUItem::getSkuID).collect(Collectors.toList());
                    List<SKUItem> comboDiscountedItems =
                            nonDiscountedItems.stream().filter(skuItem -> comboSkuList.contains(skuItem.getSkuID())).collect(Collectors.toList());

                    if (nonDiscountedSkuList.containsAll(comboSkuList)) {
                        SKUItem comboItemWithMinQty = comboDiscountedItems
                                .stream()
                                .min(Comparator.comparing(SKUItem::getQuantity))
                                .orElseThrow(NoSuchElementException::new);

                        comboDiscountedItems.forEach(comboItem -> {
                            Integer qtyToBeDiscounted = discount.getSkuQtyRelation().get(comboItem.getSkuID());
                            int totalQtyInCombo = comboSkuList.size();
                            if (!Objects.isNull(qtyToBeDiscounted)) {
                                int discountedTimes = comboItemWithMinQty.getQuantity() / qtyToBeDiscounted;
                                int nonDiscountedQty =
                                        comboItem.getQuantity() - comboItemWithMinQty.getQuantity();
                                comboItem.setDiscounted(Boolean.TRUE);
                                comboItem.setItemSubTotal(discount.getReward().divide(BigDecimal.valueOf(totalQtyInCombo))
                                        .multiply(BigDecimal.valueOf(discountedTimes))
                                        .add(comboItem.getItemPrice().multiply(BigDecimal.valueOf(nonDiscountedQty))));
                            }
                        });
                    }
                }
        );

    }
}
