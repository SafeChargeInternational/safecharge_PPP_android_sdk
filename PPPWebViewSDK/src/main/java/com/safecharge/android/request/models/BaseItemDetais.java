package com.safecharge.android.request.models;

/**
 * Base holder for item details
 * @author Bozhidar
 *
 */
public class BaseItemDetais {
	private String itemNumber;
	
	private Double itemAmount;
	private Double itemShipping;
	private Double itemHandling;
	private Double itemDiscount;
	
	private Long itemQuantity;
	
	public BaseItemDetais(Double itemAmount) {
		if (itemAmount == null) {
			throw new IllegalArgumentException("item's amount can't be null !");
		}
		
		this.itemAmount = itemAmount;
		this.itemQuantity = 1l;
	}
	
	public String getItemNumber() {
		return itemNumber;
	}
	
	public void setItemNumber(String itemNumber) {
		if (itemNumber != null && itemNumber.length() > 400) {
			throw new IllegalArgumentException("ItemNumber cannot contain more than 400 characters");
		}
		
		this.itemNumber = itemNumber;
	}
	
	public Double getItemAmount() {
		return itemAmount;
	}
	
	public Double getItemShipping() {
		return itemShipping;
	}
	
	public void setItemShipping(Double itemShipping) {
		this.itemShipping = itemShipping;
	}
	
	public Double getItemHandling() {
		return itemHandling;
	}
	
	public void setItemHandling(Double itemHandling) {
		this.itemHandling = itemHandling;
	}
	
	public Double getItemDiscount() {
		return itemDiscount;
	}
	
	public void setItemDiscount(Double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}
	
	public void setItemQuantity(Long quantity) {
		this.itemQuantity = quantity;
	}
	
	public Long getItemQuantity() {
		return itemQuantity;
	}
}
