package com.trendyol.shoppingcart.model;

public class Category {

	private String title;

	public Category(String title) {
		this.title = title;
	}
	
	private Category parentCategory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
