package com.safecharge.android.request;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.safecharge.android.request.models.PPItemDetails;
import com.safecharge.android.util.Constants;

/**
 * Holder for all ppp request params.
 * @author Bozhidar
 *
 */
public class PPRequest {
	private static final Object EQUALS = "=";
	private static final String AMPERSAND = "&";
	
	private LinkedHashMap<String, String> params;
	private List<PPItemDetails> items;
	private String url;
	
	private String urlEncodedString;
	private boolean itemsAdded;
	
	PPRequest() {
		params = new LinkedHashMap<String, String>();
		items = new ArrayList<PPItemDetails>();
	}
	 
	PPRequest(PPRequest request) {
		 if (request == null) {
			throw new IllegalArgumentException("Request can't be null !");
		 }
		 
		 this.params = request.params;
		 this.items = request.items;
		 this.url = request.url;
		 this.itemsAdded = request.itemsAdded;
	 }
	
	public String getUrl() {
		return url;
	}

	public String getParam(String name) {
		return params.get(name);
	}
	
	public Set<String> getParamsKeys() {
		return params.keySet();
	}
	
	/**
	 * Returns copy of items details
	 * @return
	 */
	 public List<PPItemDetails> getItems() {
		 return  new ArrayList<PPItemDetails>(items); 
	}
	
	/**
	 * Calculate checksum and return URl encoded string of params.
	 * @return
	 */
	public String toUrlEncodedString() {

		if (urlEncodedString == null) {
			//addItemsDetais();
		
			urlEncodedString = encodeRequest();
		}
		
		return urlEncodedString;
	}

	/**
	 * Returns array list with all values in the request
	 * @return
	 */
	public ArrayList<String> getValuesList() {
		ArrayList<String> result = new ArrayList<String>();
		for(LinkedHashMap.Entry<String,String> iter : params.entrySet()) {
			result.add(iter.getValue());
		}
		return result;
	}
	
	
	void addItem(PPItemDetails item) {
		items.add(item);
	}
	
	void putParam(String name, String value) {
		params.put(name, value);
	}
	
	void setUrl(String url) {
		this.url = url;
	}
	
	private String encodeRequest() {
		StringBuilder builder = new StringBuilder();
			
		if (params != null) {
			boolean shouldAddAmpersant = false;
				
			for (String key : params.keySet()) {
				String value = params.get(key);
					
				if (shouldAddAmpersant) {
					builder.append(AMPERSAND);
				} else {
					shouldAddAmpersant = true;
				}
				
				builder.append(key).append(EQUALS).append(value);
			}
		}
		return builder.toString();
	}
	
	public void addItemsDetais() {
		if(itemsAdded) {
			throw new IllegalStateException("addItemsDetais can be called only once !");
		}

		for (int i = 0; i < getItems().size(); i++) {
			PPItemDetails item =getItems().get(i);
			int j = i+1;
			
			putParam(Constants.RequestParams.ITEM_NAME + j, item.getItemName());
			putParam(Constants.RequestParams.ITEM_AMOUNT + j, Constants.DECIMAL_FORMAT.format(item.getItemAmount()));
			putParam(Constants.RequestParams.ITEM_QUANTITY + j, String.valueOf(item.getItemQuantity()));
			
			Double shipping = item.getItemShipping();
			if (shipping != null) {
				putParam(Constants.RequestParams.ITEM_SHIPPING + j, shipping.toString());
			}
			
			Double handling = item.getItemHandling();
			if (handling != null) {
				putParam(Constants.RequestParams.ITEM_HANDLING + j, handling.toString());
			}
			
			Double discount = item.getItemDiscount();
			if (discount != null) {
				putParam(Constants.RequestParams.ITEM_DISCOUNT + j, discount.toString());
			}
		}

		itemsAdded = true;
	}
}
