package com.octo.commerce;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.PromotionEngine;
import com.octo.commerce.pricing.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Unit tests for Promotion Engine
 */
public class PromotionEngineTest {
    /**
     * Scenario 0: Simply adding all skus when no discounts are in picture
     */
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


    /**
     * Scenario A: Simply adding three skus to the cart and getting the total when discounts dont match
     */
    @Test
    void calculateTotalOrderValueWithoutMatchingDiscounts() {
        SKUItem skuItemA = new SKUItem("A", new BigDecimal(50), 1);
        SKUItem skuItemB = new SKUItem("B", new BigDecimal(30), 1);
        SKUItem skuItemC = new SKUItem("C", new BigDecimal(20), 1);

        ShoppingCart cart = new ShoppingCart();
        cart.add(skuItemA);
        cart.add(skuItemB);
        cart.add(skuItemC);

        PromotionEngine promotionEngine = new PromotionEngine();
        Map<String, Integer> discountCriteriaA = Collections.singletonMap("A", 3);
        BuyXforAmt buyXFixedPriceDiscountA = new BuyXforAmt(discountCriteriaA, RewardType.FiXED_SINGLE, new BigDecimal(130), "FiXED_SINGLE_A");
        promotionEngine.addDiscount(buyXFixedPriceDiscountA);


        Map<String, Integer> discountCriteriaB = Collections.singletonMap("B", 2);
        BuyXforAmt buyXFixedPriceDiscountB = new BuyXforAmt(discountCriteriaB, RewardType.FiXED_SINGLE, new BigDecimal(45), "FiXED_SINGLE_B");
        promotionEngine.addDiscount(buyXFixedPriceDiscountB);

        Map<String, Integer> discountCriteriaCD = new HashMap<>();
        discountCriteriaCD.put("C", 1);
        discountCriteriaCD.put("D", 1);
        BuyXandYforAmt buyXandYforAmt = new BuyXandYforAmt(discountCriteriaCD, RewardType.FiXED_COMBO, new BigDecimal(30), "FIXED_COMBINED");
        promotionEngine.addDiscount(buyXandYforAmt);

        Optional<BigDecimal> orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(100), orderTotal.get());
    }


    /**
     * Scenario B: Simply adding three skus to the cart and getting the total when discounts match
     */
    @Test
    void calculateTotalOrderValueWithMatchingDiscountsforSingleItems() {
        SKUItem skuItemA = new SKUItem("A", new BigDecimal(50), 5);
        SKUItem skuItemB = new SKUItem("B", new BigDecimal(30), 5);
        SKUItem skuItemC = new SKUItem("C", new BigDecimal(20), 1);

        ShoppingCart cart = new ShoppingCart();
        cart.add(skuItemA);
        cart.add(skuItemB);
        cart.add(skuItemC);

        PromotionEngine promotionEngine = new PromotionEngine();
        Map<String, Integer> discountCriteriaA = Collections.singletonMap("A", 3);
        BuyXforAmt buyXFixedPriceDiscountA = new BuyXforAmt(discountCriteriaA, RewardType.FiXED_SINGLE, new BigDecimal(130), "FiXED_SINGLE_A");
        promotionEngine.addDiscount(buyXFixedPriceDiscountA);


        Map<String, Integer> discountCriteriaB = Collections.singletonMap("B", 2);
        BuyXforAmt buyXFixedPriceDiscountB = new BuyXforAmt(discountCriteriaB, RewardType.FiXED_SINGLE, new BigDecimal(45), "FiXED_SINGLE_B");
        promotionEngine.addDiscount(buyXFixedPriceDiscountB);

        Map<String, Integer> discountCriteriaCD = new HashMap<>();
        discountCriteriaCD.put("C", 1);
        discountCriteriaCD.put("D", 1);
        BuyXandYforAmt buyXandYforAmt = new BuyXandYforAmt(discountCriteriaCD, RewardType.FiXED_COMBO, new BigDecimal(30), "FIXED_COMBINED");
        promotionEngine.addDiscount(buyXandYforAmt);

        Optional<BigDecimal> orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(370), orderTotal.get());
    }
}
