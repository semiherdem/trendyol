package com.trendyol.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.CartItem;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.service.DeliveryCostCalculatorService;
import com.trendyol.shoppingcart.service.ShoppingCartService;
import com.trendyol.shoppingcarts.types.enums.DiscountType;
import static java.util.stream.Collectors.groupingBy;


public class ShoppingCart implements ShoppingCartService {

	private List<CartItem> productList;

	private double totalPrice = 0.0;

	private double priceAfterDiscount;

	private double priceAfterCouponDiscount;

	private Coupon coupon;

	private int chartProductSize = 0;

	private List<Campaign> appliedCampaignList;

	public ShoppingCart() {
		setAppliedCampaignList(new ArrayList<>());
		setProductList(new ArrayList<>());
	}

	public void addItem(Product product, int quantity) {
		CartItem cartItem = new CartItem(product, quantity);
		getProductList().add(cartItem);
		this.setTotalPrice(this.getTotalPrice() + (product.getPrice() * quantity));
		this.setChartProductSize(getChartProductSize() + quantity);
	}

	public List<CartItem> getCartList() {
		return getProductList();
	}

	public double getCampaignDiscount(Campaign campaign) {
		if (campaign != null && this.getChartProductSize() > campaign.getItemCount())
			return calculatePrice(this.getTotalPrice(), campaign.getDiscountAmount(), campaign.getDiscountType());
		else
			return 0.0;
	}

	public double getDeliveryCost() {
		return 0.0;
	}
	

	public void print() {
		
//		Group by product
						
		this.getProductList().forEach(item -> {
			System.out.println(item.getProduct().getCategory().getTitle() + " , " + item.getProduct().getTitle() + " , "
					+ item.getQuantity() + " , " + item.getProduct().getPrice() + " , "
					+ item.getProduct().getPrice() * item.getQuantity() + " , "
					+ (calculatePrice((item.getProduct().getPrice() * item.getQuantity()),
							this.getBestCampaign().getDiscountAmount(), this.getBestCampaign().getDiscountType())
							- (this.getCoupon().getDiscountAmount() / this.getProductList().size()) ));
		});

		System.out.println("Total Amount : " + this.getTotalAmountAfterDiscounts());
		System.out.println("Discount After Coupon : " + this.getCouponDiscount());

		DeliveryCostCalculatorService deliveryCostCalculator = new DeliveryCostCalculator(2.0, 3.0, 2.99);
		System.out.println("Delivery Cost Calculator Result : " + deliveryCostCalculator.calculateFor(this));
	}

	public void applyDiscount(List<Campaign> campaignList) {
		final List<Double> afterDiscountPriceList = new ArrayList<Double>();
		if (campaignList != null && !campaignList.isEmpty()) {
			campaignList.stream().filter(campaign -> this.getChartProductSize() > campaign.getItemCount()
					&& campaign.getDiscountType() == DiscountType.RATE).forEach(item -> {
						afterDiscountPriceList
								.add(calculatePrice(this.getTotalPrice(), item.getDiscountAmount(), DiscountType.RATE));
						appliedCampaign(item);
					});
			campaignList.stream().filter(campaign -> this.getChartProductSize() > campaign.getItemCount()
					&& campaign.getDiscountType() == DiscountType.AMOUNT).forEach(item -> {
						afterDiscountPriceList.add(
								calculatePrice(this.getTotalPrice(), item.getDiscountAmount(), DiscountType.AMOUNT));
						appliedCampaign(item);
					});

			Optional<Double> min = afterDiscountPriceList.stream().min((o1, o2) -> Double.compare(o1, o2));

			if (!afterDiscountPriceList.isEmpty())
				setPriceAfterDiscount(min.get().doubleValue());
		}
	}

	public void applyCoupon(Coupon coupon) {
		this.setCoupon(coupon);
		if (this.getCoupon() != null) {
			if (getTotalPrice() > coupon.getMinPurchaseAmount()) {
				setPriceAfterCouponDiscount(
						calculatePrice(this.priceAfterDiscount, coupon.getDiscountAmount(), DiscountType.RATE));
			}
		}
	}

	private double calculatePrice(double totalPrice, double discountAmount, DiscountType rate) {
		if (rate == DiscountType.RATE)
			return totalPrice - (totalPrice * discountAmount / 100);
		else if (rate == DiscountType.AMOUNT)
			return totalPrice - discountAmount;
		else
			return totalPrice;
	}

	public List<CartItem> getProductList() {
		return productList;
	}

	public void setProductList(List<CartItem> productList) {
		this.productList = productList;
	}

	@Override
	public double getCouponDiscount() {
		return this.priceAfterCouponDiscount;
	}

	@Override
	public double getTotalAmountAfterDiscounts() {
		return priceAfterDiscount;
	}

	public void setPriceAfterDiscount(double priceAfterDiscount) {
		this.priceAfterDiscount = priceAfterDiscount;
	}

	public void setPriceAfterCouponDiscount(double priceAfterCouponDiscount) {
		this.priceAfterCouponDiscount = priceAfterCouponDiscount;
	}

	private void appliedCampaign(Campaign appliedCampaign) {
		if (appliedCampaign != null)
			this.getAppliedCampaignList().add(appliedCampaign);
	}

	public List<Campaign> getAppliedCampaignList() {
		return appliedCampaignList;
	}

	public void setAppliedCampaignList(List<Campaign> appliedCampaignList) {
		this.appliedCampaignList = appliedCampaignList;
	}

	public int getChartProductSize() {
		return chartProductSize;
	}

	public void setChartProductSize(int chartProductSize) {
		this.chartProductSize = chartProductSize;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Campaign getBestCampaign() {
		List<Campaign> list = this.appliedCampaignList;
		double minPrice = Double.MAX_VALUE;
		Campaign campaign = null;
		for (Campaign item : list) {
			double price = calculatePrice(this.totalPrice, item.getDiscountAmount(), item.getDiscountType());
			if (price < minPrice) {
				minPrice = price;
				campaign = item;
			}
		}

		return campaign;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

}
