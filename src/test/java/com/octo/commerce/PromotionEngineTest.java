package com.octo.commerce;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.PromotionEngine;
import com.octo.commerce.pricing.model.BuyXandYforAmt;
import com.octo.commerce.pricing.model.BuyXforAmt;
import com.octo.commerce.pricing.model.BuyXforPercent;
import com.octo.commerce.pricing.model.RewardType;
import com.octo.commerce.pricing.model.SKUItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        BigDecimal orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(115).setScale(2, RoundingMode.HALF_UP), orderTotal);
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

        BigDecimal orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP), orderTotal);
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

        BigDecimal orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(370).setScale(2, RoundingMode.HALF_UP), orderTotal);
    }

    /**
     * Scenario C: Adding four skus to the cart and getting the total when discounts match
     */
    @Test
    void calculateTotalOrderValueWithMatchingDiscountsforAndComboItems() {
        SKUItem skuItemA = new SKUItem("A", new BigDecimal(50), 3);
        SKUItem skuItemB = new SKUItem("B", new BigDecimal(30), 5);
        SKUItem skuItemC = new SKUItem("C", new BigDecimal(20), 1);
        SKUItem skuItemD = new SKUItem("D", new BigDecimal(15), 1);

        ShoppingCart cart = new ShoppingCart();
        cart.add(skuItemA);
        cart.add(skuItemB);
        cart.add(skuItemC);
        cart.add(skuItemD);

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

        BigDecimal orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(280).setScale(2, RoundingMode.HALF_UP), orderTotal);
    }


    /**
     * Scenario D: Adding four skus to the cart and getting the total when discounts match
     */
    @Test
    void calculateTotalOrderValueWithMatchingDiscountsforSingleAndUnEqualComboItems() {
        SKUItem skuItemA = new SKUItem("A", new BigDecimal(50), 3);
        SKUItem skuItemB = new SKUItem("B", new BigDecimal(30), 5);
        SKUItem skuItemC = new SKUItem("C", new BigDecimal(20), 2);
        SKUItem skuItemD = new SKUItem("D", new BigDecimal(15), 1);

        ShoppingCart cart = new ShoppingCart();
        cart.add(skuItemA);
        cart.add(skuItemB);
        cart.add(skuItemC);
        cart.add(skuItemD);

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

        BigDecimal orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(300).setScale(2, RoundingMode.HALF_UP), orderTotal);
    }


    /**
     * Scenario E: Adding four skus to the cart and getting the total when discounts match based on percentage
     */
    @Test
    void calculateTotalOrderValueWithMatchingDiscountsforSingleAndUnEqualComboItemsWithPercent() {
        SKUItem skuItemA = new SKUItem("A", new BigDecimal(50), 3);
        SKUItem skuItemB = new SKUItem("B", new BigDecimal(30), 5);
        SKUItem skuItemC = new SKUItem("C", new BigDecimal(20), 2);
        SKUItem skuItemD = new SKUItem("D", new BigDecimal(15), 1);

        ShoppingCart cart = new ShoppingCart();
        cart.add(skuItemA);
        cart.add(skuItemB);
        cart.add(skuItemC);
        cart.add(skuItemD);

        PromotionEngine promotionEngine = new PromotionEngine();
        Map<String, Integer> discountCriteriaA = Collections.singletonMap("A", 3);
        BuyXforPercent buyXforPercentA = new BuyXforPercent(discountCriteriaA, RewardType.PERCENT_SINGLE,
                new BigDecimal(40), "PERCENT_SINGLE_A");
        promotionEngine.addDiscount(buyXforPercentA);


        Map<String, Integer> discountCriteriaB = Collections.singletonMap("B", 2);
        BuyXforAmt buyXFixedPriceDiscountB = new BuyXforAmt(discountCriteriaB, RewardType.FiXED_SINGLE, new BigDecimal(45), "FiXED_SINGLE_B");
        promotionEngine.addDiscount(buyXFixedPriceDiscountB);

        Map<String, Integer> discountCriteriaCD = new HashMap<>();
        discountCriteriaCD.put("C", 1);
        discountCriteriaCD.put("D", 1);
        BuyXandYforAmt buyXandYforAmt = new BuyXandYforAmt(discountCriteriaCD, RewardType.FiXED_COMBO, new BigDecimal(30), "FIXED_COMBINED");
        promotionEngine.addDiscount(buyXandYforAmt);

        BigDecimal orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(230).setScale(2, RoundingMode.HALF_UP), orderTotal);
    }

    /**
     * Scenario F: Adding four skus to the cart and getting the total when discounts match based on percentage
     */
    @Test
    void calculateTotalOrderValueWithMatchingDiscountsforSingleAndUnEqualComboItemsWithPercentDup() {
        SKUItem skuItemA = new SKUItem("A", new BigDecimal(50), 3);
        SKUItem skuItemB = new SKUItem("B", new BigDecimal(30), 5);
        SKUItem skuItemC = new SKUItem("C", new BigDecimal(20), 2);
        SKUItem skuItemD = new SKUItem("D", new BigDecimal(15), 1);

        ShoppingCart cart = new ShoppingCart();
        cart.add(skuItemA);
        cart.add(skuItemB);
        cart.add(skuItemC);
        cart.add(skuItemD);

        PromotionEngine promotionEngine = new PromotionEngine();
        Map<String, Integer> discountCriteriaAFixed = Collections.singletonMap("A", 2);
        BuyXforAmt buyXFixedPriceDiscountAFixed = new BuyXforAmt(discountCriteriaAFixed, RewardType.FiXED_SINGLE,
                new BigDecimal(30), "FiXED_SINGLE_A");
        promotionEngine.addDiscount(buyXFixedPriceDiscountAFixed);

        //perent discount never gets applied
        Map<String, Integer> discountCriteriaAPct = Collections.singletonMap("A", 1);
        BuyXforPercent buyXforPercentAPct = new BuyXforPercent(discountCriteriaAPct, RewardType.PERCENT_SINGLE,
                new BigDecimal(40), "PERCENT_SINGLE_A");
        promotionEngine.addDiscount(buyXforPercentAPct);


        Map<String, Integer> discountCriteriaB = Collections.singletonMap("B", 2);
        BuyXforAmt buyXFixedPriceDiscountB = new BuyXforAmt(discountCriteriaB, RewardType.FiXED_SINGLE, new BigDecimal(45), "FiXED_SINGLE_B");
        promotionEngine.addDiscount(buyXFixedPriceDiscountB);

        Map<String, Integer> discountCriteriaCD = new HashMap<>();
        discountCriteriaCD.put("C", 1);
        discountCriteriaCD.put("D", 1);
        BuyXandYforAmt buyXandYforAmt = new BuyXandYforAmt(discountCriteriaCD, RewardType.FiXED_COMBO, new BigDecimal(30), "FIXED_COMBINED");
        promotionEngine.addDiscount(buyXandYforAmt);

        BigDecimal orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(250).setScale(2, RoundingMode.HALF_UP), orderTotal);
    }
}
