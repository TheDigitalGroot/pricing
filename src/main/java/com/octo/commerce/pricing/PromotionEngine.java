package com.octo.commerce.pricing;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.model.Discount;
import com.octo.commerce.pricing.model.RewardType;
import com.octo.commerce.pricing.model.SKUItem;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the Promotion Engine.
 */
@Slf4j
public class PromotionEngine {
    /**
     * list of stored discounts.
     */
    private final List<Discount> discountList = new ArrayList<>();

    /**
     * main method to calculate order total.
     *
     * @param cart - accepts a cart object
     * @return - total order value
     */
    public BigDecimal calculateOrderTotal(final ShoppingCart cart) {
        applyDiscounts(cart);
        return cart.getItems().stream().map(SKUItem::getItemSubTotal).reduce(BigDecimal::add).get().setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Method to calculate the discounts.
     *
     * @param cart - accepts a cart
     */
    private void applyDiscounts(final ShoppingCart cart) {
        calculateItemSubTotalForSingleDiscounts(cart);
        calculateItemSubTotalForComboDiscounts(cart);
        calculateItemSubTotalForNoDiscounts(cart);
    }

    /**
     * calculate item subtotal for combinations sku discounts.
     *
     * @param cart
     */
    private void calculateItemSubTotalForComboDiscounts(final ShoppingCart cart) {
        List<SKUItem> undiscountedItems =
                cart.getItems().stream().filter(skuItem -> !skuItem.isDiscounted()).collect(Collectors.toList());

        discountList.stream().filter(discount -> discount.getRewardType().equals(RewardType.FiXED_COMBO)).forEach(discount -> {
                    List<String> comboSkuList = new ArrayList<>(discount.getSkuQtyRelation().keySet());
                    log.info("comboSkuList {} ", String.valueOf(comboSkuList));

                    List<String> undiscountedSkuList =
                            undiscountedItems.stream().map(SKUItem::getSkuID).collect(Collectors.toList());
                    log.info("undiscountedSkuList {} ", String.valueOf(undiscountedSkuList));

                    List<SKUItem> comboDiscountedItems =
                            undiscountedItems.stream().filter(skuItem -> comboSkuList.contains(skuItem.getSkuID())).collect(Collectors.toList());
                    log.info("comboDiscountedItems {} ", String.valueOf(comboDiscountedItems));


                    if (undiscountedSkuList.containsAll(comboSkuList)) {
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
                                log.info(String.valueOf(comboItem));
                            }
                        });
                    }
                }
        );

    }

    /**
     * calculate item subtotal for non discounted skus.
     *
     * @param cart - accepts a cart
     */
    private void calculateItemSubTotalForNoDiscounts(final ShoppingCart cart) {
        cart.getItems().stream().filter(skuItem -> !skuItem.isDiscounted()).forEach(skuItem -> {
            skuItem.setItemSubTotal(skuItem.getItemPrice().multiply(BigDecimal.valueOf(skuItem.getQuantity())));
            log.info(String.valueOf(skuItem));

        });
    }

    /**
     * calculate item subtotal for single sku discounts.
     *
     * @param cart - accept a shopping cart
     */
    private void calculateItemSubTotalForSingleDiscounts(final ShoppingCart cart) {
        cart.getItems().stream().filter(skuItem -> !skuItem.isDiscounted()).forEach(skuItem -> {
            discountList.stream().filter(discount -> discount.getRewardType().equals(RewardType.FiXED_SINGLE)).forEach(discount -> {
                        Integer qtyToBeDiscounted = discount.getSkuQtyRelation().get(skuItem.getSkuID());
                        if (!Objects.isNull(qtyToBeDiscounted)) {
                            int discountedTimes = skuItem.getQuantity() / qtyToBeDiscounted;
                            int nonDiscountedQty = skuItem.getQuantity() % qtyToBeDiscounted;
                            skuItem.setDiscounted(Boolean.TRUE);
                            skuItem.setItemSubTotal(discount.getReward().multiply(BigDecimal.valueOf(discountedTimes))
                                    .add(skuItem.getItemPrice().multiply(BigDecimal.valueOf(nonDiscountedQty))));
                            log.info(String.valueOf(skuItem));
                        }
                    }
            );
        });

        cart.getItems().stream().filter(skuItem -> !skuItem.isDiscounted()).forEach(skuItem -> {
            discountList.stream().filter(discount -> discount.getRewardType().equals(RewardType.PERCENT_SINGLE)).forEach(discount -> {
                        Integer qtyToBeDiscounted = discount.getSkuQtyRelation().get(skuItem.getSkuID());
                        if (!Objects.isNull(qtyToBeDiscounted)) {
                            int nonDiscountedQty = skuItem.getQuantity() - qtyToBeDiscounted;
                            skuItem.setDiscounted(Boolean.TRUE);
                            skuItem.setItemSubTotal(discount.getReward().multiply(skuItem.getItemPrice())
                                    .multiply(BigDecimal.valueOf(qtyToBeDiscounted))
                                    .add(skuItem.getItemPrice().multiply(BigDecimal.valueOf(nonDiscountedQty))));
                            log.info("{} {} ", discount.getReward(), String.valueOf(skuItem));
                        }
                    }
            );
        });
    }


    /**
     * add a discount to local storage.
     *
     * @param discount - add a discount
     */
    public void addDiscount(final Discount discount) {
        discountList.add(discount);
    }
}
