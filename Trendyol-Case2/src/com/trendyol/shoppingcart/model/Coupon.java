package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcarts.types.enums.DiscountType;

public class Coupon {
	
	private int minPurchaseAmount;
	
	private double discountAmount;

	private DiscountType discountType;

	public Coupon(int minPurchaseAmount, double discountAmount, DiscountType discountType) {
		this.setMinPurchaseAmount(minPurchaseAmount);
		this.setDiscountAmount(discountAmount);
		this.setDiscountType(discountType);
	}

	public int getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	public void setMinPurchaseAmount(int minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
}
