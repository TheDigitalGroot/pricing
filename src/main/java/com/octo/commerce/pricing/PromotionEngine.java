package com.octo.commerce.pricing;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.model.PriceDiscount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the Promotion Engine.
 */
public class PromotionEngine {
    /**
     * list of stored discounts.
     */
    private final List<PriceDiscount> priceDiscountList = new ArrayList<>();

    /**
     * main method to calculate order total.
     *
     * @param cart - accepts a cart object
     * @return - total order value
     */
    public Optional<BigDecimal> calculateOrderTotal(final ShoppingCart cart) {
        applyDiscounts(cart);
        return cart.getItems().stream().map(a -> a.getSalesPrice().multiply(BigDecimal.valueOf(a.getQuantity()))).reduce(BigDecimal::add);
    }

    /**
     * Method to calculate the discounts.
     *
     * @param cart - accepts a cart
     */
    private void applyDiscounts(final ShoppingCart cart) {
        cart.getItems().forEach(e -> e.setSalesPrice(e.getItemPrice()));
    }

    /**
     * add a discount to local storage.
     *
     * @param discount - add a discount
     */
    public void addDiscount(final PriceDiscount discount) {
        priceDiscountList.add(discount);
    }
}
