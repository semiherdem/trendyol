package com.trendyol.shoppingcarts.types.enums;

public enum DiscountType {
	
	RATE("Rate"),
	AMOUNT("Request");

	private String value;

	DiscountType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
