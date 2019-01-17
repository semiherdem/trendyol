package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcarts.types.enums.DiscountType;

public class Campaign {
	
	private String campaignTitle;
	
	private Category category;
	
	private double discountAmount;
	
	private int itemCount;
	
	private DiscountType discountType;

	public String getCampaignTitle() {
		return campaignTitle;
	}

	public void setCampaignTitle(String campaignTitle) {
		this.campaignTitle = campaignTitle;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public Campaign(Category category, double discountAmount, int itemCount, DiscountType discountType) {
		this.setCategory(category);
		this.discountAmount = discountAmount;
		this.itemCount = itemCount;
		this.setDiscountType(discountType);
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
