package com.octo.commerce;

import com.octo.commerce.cart.model.ShoppingCart;
import com.octo.commerce.pricing.PromotionEngine;
import com.octo.commerce.pricing.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

        DiscountCriteria discountCriteriaA = new DiscountCriteria("A", 3);
        List<DiscountCriteria> discountCriteriasASingle = new ArrayList<>();
        discountCriteriasASingle.add(discountCriteriaA);
        BuyXforAmt buyXFixedPriceDiscountA = new BuyXforAmt(discountCriteriasASingle, RewardType.FiXED, new BigDecimal(130), "SINGLEA");
        promotionEngine.addDiscount(buyXFixedPriceDiscountA);


        DiscountCriteria discountCriteriaB = new DiscountCriteria("B", 2);
        List<DiscountCriteria> discountCriteriasSingleB = new ArrayList<>();
        discountCriteriasSingleB.add(discountCriteriaB);
        BuyXforAmt buyXFixedPriceDiscountB = new BuyXforAmt(discountCriteriasSingleB, RewardType.FiXED, new BigDecimal(45), "SINGLEB");
        promotionEngine.addDiscount(buyXFixedPriceDiscountB);

        DiscountCriteria discountCriteriaC = new DiscountCriteria("C", 1);
        DiscountCriteria discountCriteriaD = new DiscountCriteria("D", 1);
        List<DiscountCriteria> discountCriteriasCombined = new ArrayList<>();
        discountCriteriasCombined.add(discountCriteriaC);
        discountCriteriasCombined.add(discountCriteriaD);
        BuyXandYforAmt buyXandYforAmt = new BuyXandYforAmt(discountCriteriasCombined, RewardType.FiXED, new BigDecimal(30), "COMBINED");
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

        DiscountCriteria discountCriteriaA = new DiscountCriteria("A", 3);
        List<DiscountCriteria> discountCriteriasASingle = new ArrayList<>();
        discountCriteriasASingle.add(discountCriteriaA);
        BuyXforAmt buyXFixedPriceDiscountA = new BuyXforAmt(discountCriteriasASingle, RewardType.FiXED, new BigDecimal(130), "SINGLEA");
        promotionEngine.addDiscount(buyXFixedPriceDiscountA);


        DiscountCriteria discountCriteriaB = new DiscountCriteria("B", 2);
        List<DiscountCriteria> discountCriteriasSingleB = new ArrayList<>();
        discountCriteriasSingleB.add(discountCriteriaB);
        BuyXforAmt buyXFixedPriceDiscountB = new BuyXforAmt(discountCriteriasSingleB, RewardType.FiXED, new BigDecimal(45), "SINGLEB");
        promotionEngine.addDiscount(buyXFixedPriceDiscountB);

        DiscountCriteria discountCriteriaC = new DiscountCriteria("C", 1);
        DiscountCriteria discountCriteriaD = new DiscountCriteria("D", 1);
        List<DiscountCriteria> discountCriteriasCombined = new ArrayList<>();
        discountCriteriasCombined.add(discountCriteriaC);
        discountCriteriasCombined.add(discountCriteriaD);
        BuyXandYforAmt buyXandYforAmt = new BuyXandYforAmt(discountCriteriasCombined, RewardType.FiXED, new BigDecimal(30), "COMBINED");
        promotionEngine.addDiscount(buyXandYforAmt);

        Optional<BigDecimal> orderTotal = promotionEngine.calculateOrderTotal(cart);
        Assertions.assertEquals(new BigDecimal(370), orderTotal.get());
    }
}
