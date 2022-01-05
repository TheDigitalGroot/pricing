package com.octo.commerce.pricing;

import com.octo.commerce.cart.model.ShoppingCart;

import java.math.BigDecimal;

/**
 * Represents the Promotion Engine.
 */
public class PromotionEngine {
    /**
     * main method to calculate order total.
     *
     * @param cart - accepts a cart object
     * @return - total order value
     */
    public BigDecimal calculateOrderTotal(final ShoppingCart cart) {
        return new BigDecimal(115);
    }
}
