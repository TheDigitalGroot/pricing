package com.octo.commerce.pricing;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.model.Discount;
import com.octo.commerce.pricing.model.RewardType;
import com.octo.commerce.pricing.model.SKUItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents the Promotion Engine.
 */
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
    public Optional<BigDecimal> calculateOrderTotal(final ShoppingCart cart) {
        applyDiscounts(cart);
        return cart.getItems().stream().map(SKUItem::getItemSubTotal).reduce(BigDecimal::add);
    }

    /**
     * Method to calculate the discounts.
     *
     * @param cart - accepts a cart
     */
    private void applyDiscounts(final ShoppingCart cart) {
        cart.getItems().forEach(this::calculateItemSubTotal);
    }

    /**
     * calculate item subtotal.
     *
     * @param skuItem - accept a single skuitem
     */
    private void calculateItemSubTotal(final SKUItem skuItem) {
        discountList.forEach(discount -> {
                    if (discount.getRewardType().equals(RewardType.FiXED_SINGLE)) {
                        Integer qtyToBeDiscounted = discount.getSkuQtyRelation().get(skuItem.getSkuID());
                        if (!Objects.isNull(qtyToBeDiscounted)) {
                            int discountedTimes = skuItem.getQuantity() / qtyToBeDiscounted;
                            int nonDiscountedQty = skuItem.getQuantity() % qtyToBeDiscounted;
                            skuItem.setDiscounted(Boolean.TRUE);
                            skuItem.setItemSubTotal(discount.getRewardAmount().multiply(BigDecimal.valueOf(discountedTimes))
                                    .add(skuItem.getItemPrice().multiply(BigDecimal.valueOf(nonDiscountedQty))));
                        }
                    }
                }
        );

        if (!skuItem.isDiscounted()) {
            skuItem.setItemSubTotal(skuItem.getItemPrice().multiply(BigDecimal.valueOf(skuItem.getQuantity())));
        }
        System.out.println(skuItem);
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
