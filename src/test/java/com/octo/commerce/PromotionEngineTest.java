package com.octo.commerce;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.PromotionEngine;
import com.octo.commerce.pricing.model.SKUItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Unit tests for Promotion Engine
 */
public class PromotionEngineTest {
    @Test
    void calculateTotalOrderValue() {
        SKUItem skuItemA = new SKUItem("A", new BigDecimal(50), 1);
        SKUItem skuItemB = new SKUItem("B", new BigDecimal(30), 1);
        SKUItem skuItemC = new SKUItem("C", new BigDecimal(20), 1);
        SKUItem skuItemD = new SKUItem("D", new BigDecimal(15), 1);

        ShoppingCart cart = new ShoppingCart();
        cart.add(skuItemA);
        cart.add(skuItemB);
        cart.add(skuItemC);
        cart.add(skuItemD);

        PromotionEngine promotionEngine = new PromotionEngine();
        Optional<BigDecimal> orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(115), orderTotal.get());

    }
}
