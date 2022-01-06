package com.octo.commerce.pricing;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.model.Discount;
import com.octo.commerce.pricing.model.SKUItem;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        priceCartItems(cart);
        return priceOrder(cart);
    }

    /**
     * Calculator the total order value.
     *
     * @param cart - accept a cart
     * @return cartValue
     */
    private BigDecimal priceOrder(final ShoppingCart cart) {
        Optional<BigDecimal> cartValue = cart.getItems().stream().map(SKUItem::getItemSubTotal).reduce(BigDecimal::add);
        return cartValue.orElse(BigDecimal.valueOf(0)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Method to calculate the discounts.
     *
     * @param cart - accepts a cart
     */
    private void priceCartItems(final ShoppingCart cart) {
        calculateItemSubTotalForSingleDiscounts(cart);
        calculateItemSubTotalForComboDiscounts(cart);
        calculateItemSubTotalForNoDiscounts(cart);
        logItems(cart.getItems());
    }

    private void logItems(final List<SKUItem> items) {
        items.forEach(skuItem -> log.info(String.valueOf(skuItem)));
    }

    /**
     * calculate item subtotal for combinations sku discounts.
     *
     * @param cart
     */
    private void calculateItemSubTotalForComboDiscounts(final ShoppingCart cart) {
        DiscountCalculator fixedPriceDiscountCalculator = new FixedPriceComboDiscountCalculatorImpl();
        fixedPriceDiscountCalculator.priceItems(discountList, cart.getItems());
    }

    /**
     * calculate item subtotal for non discounted skus.
     *
     * @param cart - accepts a cart
     */
    private void calculateItemSubTotalForNoDiscounts(final ShoppingCart cart) {
        cart.getItems().stream().filter(skuItem -> !skuItem.isDiscounted()).forEach(skuItem -> {
            skuItem.setItemSubTotal(skuItem.getItemPrice().multiply(BigDecimal.valueOf(skuItem.getQuantity())));
        });
    }

    /**
     * calculate item subtotal for single sku discounts.
     *
     * @param cart - accept a shopping cart
     */
    private void calculateItemSubTotalForSingleDiscounts(final ShoppingCart cart) {
        DiscountCalculator fixedPriceDiscountCalculator = new FixedPriceDiscountCalculatorImpl();
        fixedPriceDiscountCalculator.priceItems(discountList, cart.getItems());

        DiscountCalculator percentDiscountCalculator = new PercentDiscountCalculatorImpl();
        percentDiscountCalculator.priceItems(discountList, cart.getItems());
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
