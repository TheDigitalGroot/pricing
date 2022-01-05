package com.octo.commerce.pricing;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.model.SKUItem;

import java.math.BigDecimal;
import java.util.Optional;

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
    public Optional<BigDecimal> calculateOrderTotal(final ShoppingCart cart) {
        return cart.getItems().stream().map(SKUItem::getSkuPrice).reduce(BigDecimal::add);
    }
}
