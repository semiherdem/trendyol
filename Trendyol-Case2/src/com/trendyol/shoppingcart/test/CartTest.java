package com.trendyol.shoppingcart.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.service.ShoppingCartService;
import com.trendyol.shoppingcart.service.impl.ShoppingCart;
import com.trendyol.shoppingcarts.types.enums.DiscountType;

public class CartTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void addProductToCartTest() throws Exception {

		ShoppingCartService cart = new ShoppingCart();

		Category category = new Category("Clothes");

		Product prod1 = new Product("Prod1", 699.90, category);
		Product prod2 = new Product("Prod2", 100.90, category);

		cart.addItem(prod1, 1);
		cart.addItem(prod2, 2);

		assertEquals(3, cart.getChartProductSize());

	}

	@Test
	public void applyProperCampaignToToCartTest() throws Exception {

		ShoppingCartService cart = new ShoppingCart();

		Category category = new Category("Clothes");

		Product prod1 = new Product("Prod1", 699.90, category);
		Product prod2 = new Product("Prod2", 100.90, category);

		cart.addItem(prod1, 1);
		cart.addItem(prod2, 2);

		Campaign campaign1 = new Campaign(category, 20.0, 2, DiscountType.RATE);

		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign1);

		cart.applyDiscount(campaignList);

		assertNotEquals(cart.getTotalPrice(), cart.getTotalAmountAfterDiscounts());

	}

	@Test
	public void applyProperCouponToCartTest() throws Exception {

		ShoppingCartService cart = new ShoppingCart();

		Category category = new Category("Clothes");

		Product prod1 = new Product("Prod1", 699.90, category);
		Product prod2 = new Product("Prod2", 100.90, category);

		cart.addItem(prod1, 1);
		cart.addItem(prod2, 2);

		Campaign campaign1 = new Campaign(category, 20.0, 2, DiscountType.RATE);

		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign1);

		cart.applyDiscount(campaignList);

		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
		cart.applyCoupon(coupon);

		assertNotEquals(cart.getTotalAmountAfterDiscounts(), cart.getCouponDiscount());

	}

}
