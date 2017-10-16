package com.safecharge.android.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.safecharge.android.request.models.BaseItemDetais;
import com.safecharge.android.request.models.PPResult;

/**
 * Util class for handling common tasks
 * @author Bozhidar
 *
 */
public class Util {
	
	private static final String PPP_TIMESTAMP_PATTERN = "yyyy-MM-ddHH:mm:ss:SS";
	private static final String GMT = "GMT";

	/**
	 * Extract key/value map from passed url string
	 * @param url
	 * @return
	 */
	public static Map<String, String> getParamsMap(String url) {  
		int urlEnd = url.indexOf('?');
		Map<String, String> map = new HashMap<String, String>(); 
		String paramsString;
		
		if (urlEnd > 0 ) {
			paramsString = url.substring(urlEnd +1);
		} else {
			paramsString = url;
		}
		String[] params = paramsString.split("&");  
	    
		for (String param : params)  {  
	    	String[] pair = param.split("=");
	    	
	    	if (pair != null && pair.length == 2) {
	    		String name = pair[0];  
	 	        String value = pair[1];  
	 	        map.put(name, value);  
			}
		}
		
	    return map;  
	}
	
	/**
	 * Create and populate {@link PPResult} from passed urlParams
	 * @param urlParams
	 * @return
	 */
	public static PPResult toPPPResult(Map<String, String> urlParams) {
		PPResult result = new PPResult();
		
		result.setStatus(urlParams.get(Constants.ResultParams.STATUS));
		result.setClientUniqueID(urlParams.get(Constants.ResultParams.CLIENT_UNIQUE_ID));
		result.setAuthCode(urlParams.get(Constants.ResultParams.AUTH_CODE));
		result.setReason(urlParams.get(Constants.ResultParams.REASON));
		result.setToken(urlParams.get(Constants.ResultParams.TOKEN));
		result.setResponseCheckSum(urlParams.get(Constants.ResultParams.RESPONSE_CHECKSUM));
		result.setAdvanceResponseChecksum(urlParams.get(Constants.ResultParams.ADVANCE_RESPONSE_CHECKSUM));
		result.setEci(urlParams.get(Constants.ResultParams.ECI));
		result.setNameOnCard(urlParams.get(Constants.ResultParams.NAME_ON_CARD));
		result.setCurrency(urlParams.get(Constants.ResultParams.CURRENCY));
		result.setCustomData(urlParams.get(Constants.ResultParams.CUSTOM_DATA));
		result.setMerchantUniqueId(urlParams.get(Constants.ResultParams.MERCHANT_UNIQUE_ID));
		result.setRequestVersion(urlParams.get(Constants.ResultParams.REQUEST_VERSION));
		result.setMessage(urlParams.get(Constants.ResultParams.MESSAGE));
		result.setError(urlParams.get(Constants.ResultParams.ERROR));
		result.setInstantDmnStatus(urlParams.get(Constants.ResultParams.INSTANT_DMN_STATUS));
		result.setUserID(urlParams.get(Constants.ResultParams.USER_ID));
		result.setProductID(urlParams.get(Constants.ResultParams.PRODUCT_ID));
		result.setPppStatus(urlParams.get(Constants.ResultParams.PPP_STATUS));
		result.setMerchantLocale(urlParams.get(Constants.ResultParams.MERCHANT_LOCALE));
		result.setUnknownParameters(urlParams.get(Constants.ResultParams.UNKNOWN_PARAMETERS));
		result.setWebMasterId(urlParams.get(Constants.ResultParams.WEB_MASTER_ID));
		
		
		result.setTransactionId(tryToParseLong(urlParams.get(Constants.ResultParams.TRANSACTION_ID), 0));	
		result.setMerchantSiteId(tryToParseLong(urlParams.get(Constants.ResultParams.MERCHANT_SITE_ID), 0));
		result.setMerchantId(tryToParseLong(urlParams.get(Constants.ResultParams.MERCHANT_ID), 0));
		result.setPppTransactionID(tryToParseLong(urlParams.get(Constants.ResultParams.PPP_TRANSACTION_ID), 0));
		
		result.setErrCode(tryToParseInt(urlParams.get(Constants.ResultParams.ERR_CODE), 0));
		result.setExErrCode(tryToParseInt(urlParams.get(Constants.ResultParams.EX_ERR_CODE), 0));
		result.setReasonCode(tryToParseInt(urlParams.get(Constants.ResultParams.REASON_CODE), 0));
		
		result.setTotalAmount(tryToParseDouble(urlParams.get(Constants.ResultParams.TOTAL_AMOUNT), 0));
		result.setTotalDiscount(tryToParseDouble(urlParams.get(Constants.ResultParams.TOTAL_DISCOUNT), 0));
		result.setTotalHandling(tryToParseDouble(urlParams.get(Constants.ResultParams.TOTAL_HANDLING), 0));
		result.setTotalShipping(tryToParseDouble(urlParams.get(Constants.ResultParams.TOTAL_SHIPPING), 0));
		result.setTotalTax(tryToParseDouble(urlParams.get(Constants.ResultParams.TOTAL_TAX), 0));
		
		List<BaseItemDetais> items = new ArrayList<BaseItemDetais>();
		result.setItems(items);

		for(int i = 1;; i++) {
			String itemAmount= urlParams.get(Constants.ResultParams.ITEM_AMOUNT +i);
			if (itemAmount == null) {
				break;
			}
			
			BaseItemDetais item = new BaseItemDetais(tryToParseDouble(itemAmount, 0));
			item.setItemDiscount(tryToParseDouble(urlParams.get(Constants.ResultParams.ITEM_DISCOUNT + i), 0));
			item.setItemHandling(tryToParseDouble(urlParams.get(Constants.ResultParams.ITEM_HANDLING + i), 0));
			item.setItemNumber(urlParams.get(Constants.ResultParams.ITEM_NUMBER + i));
			item.setItemQuantity(tryToParseLong(urlParams.get(Constants.ResultParams.ITEM_QUANTITY + i), 0));
			item.setItemShipping(tryToParseDouble(urlParams.get(Constants.ResultParams.ITEM_SHIPPING + i), 0));
			
			items.add(item);
		}
		
		List<String> customFields = new ArrayList<String>();
		result.setCustomFilds(customFields);
		
		for(int i = 1;; i++) {
			String customField = urlParams.get(Constants.ResultParams.CUSTOM_FIELD_N +i);
		
			if (customField == null) {
				break;
			}
			customFields.add(customField);
		}
		return result;
	}
	
	
	/**
	 * Try to parse to int
	 * @param s
	 * @param defaultValue
	 * @return default value if fail
	 */
	public static int tryToParseInt(String s, int defaultValue) {
		if (s != null) {
			try {
				return Integer.parseInt(s);
			}catch(NumberFormatException e) {} 
		}
		return defaultValue;
	}
	
	
	/**
	 * Try to parse to long
	 * @param s
	 * @param defaultValue
	 * @return default value if fail
	 */
	public static long tryToParseLong(String s, long defaultValue) {
		if (s != null) {
			try {
				return Long.parseLong(s);
			}catch(NumberFormatException e) {} 
		}
		return defaultValue;
	}
	
	/**
	 * Try to parse to double
	 * @param s
	 * @param defaultValue
	 * @return default value if fail
	 */
	public static double tryToParseDouble(String s, double defaultValue) {
		if (s != null) {
			try {
				return Double.parseDouble(s);
			}catch(NumberFormatException e) {} 
		}
		return defaultValue;
	}
	
	public static String toPPPTimeStamp(Date time) {
		if (time == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(PPP_TIMESTAMP_PATTERN, Locale.getDefault());
		dateFormat.setTimeZone(TimeZone.getTimeZone(GMT));
		
		return dateFormat.format(time);
	}
}
