package com.trendyol.shoppingcart.business;

import java.util.ArrayList;
import java.util.List;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.service.DeliveryCostCalculatorService;
import com.trendyol.shoppingcart.service.ShoppingCartService;
import com.trendyol.shoppingcart.service.impl.DeliveryCostCalculator;
import com.trendyol.shoppingcart.service.impl.ShoppingCart;
import com.trendyol.shoppingcarts.types.enums.DiscountType;

public class Infrastructure {

	public static void main(String[] args) {

		ShoppingCartService cart = new ShoppingCart();

		Category category = new Category("Clothes");

		Product coat = new Product("Coat", 699.90, category);
		Product sweatshirt = new Product("Sweatshirt", 100.90, category);
		Product pants = new Product("Pants", 299.90, category);
		Product boots = new Product("Boots", 499.90, category);
		Product glove = new Product("Glove", 49.90, category);

		cart.addItem(coat, 1);
		cart.addItem(sweatshirt, 1);
		cart.addItem(pants, 1);
		cart.addItem(boots, 1);
		cart.addItem(glove, 2);

		Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.RATE);
		Campaign campaign2 = new Campaign(category, 30.0, 5, DiscountType.RATE);
		Campaign campaign3 = new Campaign(category, 5.0, 1, DiscountType.AMOUNT);

		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		if (cart.getProductList() != null && !cart.getProductList().isEmpty())
			cart.applyDiscount(campaignList);

		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
		if (cart.getProductList() != null && !cart.getProductList().isEmpty())
			cart.applyCoupon(coupon);

		cart.print();
	}

}
