package com.safecharge.android.request.models;

/**
 * Holder for request item details
 * @author Bozhidar
 *
 */
public class PPItemDetails extends BaseItemDetais {
	private String itemName;

	public PPItemDetails(String itemName, Double itemAmount) {
		super(itemAmount);

		if (itemName == null) {
			throw new IllegalArgumentException("item's name can't be null !");
		} else if(itemName.length() > 400) {
			throw new IllegalArgumentException("ItemNmae cannot contain more than 400 characters");
		}

		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String name) {
		this.itemName = name;
	}
}
