package com.trendyol.shoppingcart.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.service.DeliveryCostCalculatorService;
import com.trendyol.shoppingcart.service.ShoppingCartService;

public class DeliveryCostCalculator implements DeliveryCostCalculatorService{

	private double costPerDelivery;
	
	private double costPerProduct;

	private double fixedCost;
	
	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
		this.costPerDelivery = costPerDelivery;
		this.setCostPerProduct(costPerProduct);
		this.fixedCost = fixedCost;
	}

	@Override
	public double calculateFor(ShoppingCartService cart) {
//		calculate by number of distinct categories in the cart
		List<Campaign> appliedCampaignList = cart.getAppliedCampaignList();
		Set<String> distinctCategory = new HashSet<String>(); 
		appliedCampaignList.forEach(campaign ->{
			if(campaign.getCategory() != null) {
				distinctCategory.add(campaign.getCategory().getTitle());
			}
		});
		
		int numberOfDeliveries = distinctCategory.size();
		return (this.costPerDelivery * numberOfDeliveries) + (this.getCostPerProduct() * cart.getChartProductSize()) + this.fixedCost;
	}
	
	public double getCostPerDelivery() {
		return costPerDelivery;
	}

	public void setCostPerDelivery(double costPerDelivery) {
		this.costPerDelivery = costPerDelivery;
	}

	public double getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(double fixedCost) {
		this.fixedCost = fixedCost;
	}

	public double getCostPerProduct() {
		return costPerProduct;
	}

	public void setCostPerProduct(double costPerProduct) {
		this.costPerProduct = costPerProduct;
	}	

}
