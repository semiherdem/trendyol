package com.trendyol.shoppingcart.service;

import java.util.List;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.CartItem;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;

public interface ShoppingCartService {
	
	public void addItem(Product product, int quantity);

	public double getTotalAmountAfterDiscounts();
	
	public double getCouponDiscount();
	
	public double getCampaignDiscount(Campaign campaign);
		
	public void print();
	
	public void applyDiscount(List<Campaign> campaignList);

	public void applyCoupon(Coupon coupon);
	
	public List<CartItem> getProductList();
	
	public List<Campaign> getAppliedCampaignList();
	
	public int getChartProductSize();
	
	public double getTotalPrice();
			
	public Campaign getBestCampaign();
}
