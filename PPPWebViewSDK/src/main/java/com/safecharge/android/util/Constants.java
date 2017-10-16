package com.safecharge.android.util;

import java.text.DecimalFormat;

/**
 * Holder for all constants 
 * @author Bozhidar
 *
 */
public class Constants {
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###.##");
	public static final String UTF_8 = "UTF-8";
	public static final String STATUS_APPROVED = "APPROVED";
	public static final String VERSION_3_0_0 = "3.0.0";
	public static final String VERSION_4_0_0 = "4.0.0";
	
	public static final String UNDEFINED = "undefined";
	
	public static final String URL = "https://secure.safecharge.com/ppp/purchase.do?";
	public static final String WITHDRAWAL_URL = "https://secure.safecharge.com/ppp/withdrawal/withdraw.do?";
	
	public static String [] REQUIRED_FIELDS = {RequestParams.MERCHANT_ID, RequestParams.MERCHANT_SITE_ID, RequestParams.USER_TOKEN_ID, RequestParams.TOTAL_AMOUNT, RequestParams.CURRENCY, RequestParams.VERSION};
	
	public static String [] REQUIRED_WITHDRAWAL_FIELDS = {RequestParams.MERCHANT_ID, RequestParams.MERCHANT_SITE_ID, RequestParams.USER_TOKEN, RequestParams.WD_AMOUNT, RequestParams.WD_CURRENCY,RequestParams.WD_MIN_AMOUNT, RequestParams.WD_MAX_AMOUNT, RequestParams.USER_TOKEN_ID, RequestParams.VERSION};
	
	private Constants(){
	};
	
	public enum UserToken{
		REGISTER, AUTO, READONLY
	}
	
	public static final class RequestParams {
		public static final String ITEM_NAME = "item_name_";
		public static final String ITEM_NUMBER = "item_number_";
		public static final String ITEM_AMOUNT = "item_amount_";
		public static final String ITEM_SHIPPING = "item_shipping_";
		public static final String ITEM_HANDLING = "item_handling_";
		public static final String ITEM_DISCOUNT = "item_discount_";
		public static final String ITEM_QUANTITY = "item_quantity_";
		
		/*Basic Parameters*/
		public static final String MERCHANT_ID = "merchant_id";
		public static final String MERCHANT_SITE_ID = "merchant_site_id";
		public static final String VERSION  = "version";
		public static final String CHECKSUM = "checksum";
		public static final String TIME_STAMP = "time_stamp";
		public static final String CURRENCY = "currency";
		public static final String INVOICE_ID = "invoice_id";
		public static final String PAYMENT_METHOD = "payment_method";
		public static final String MERCHANT_LOCALE = "merchantLocale";
		public static final String USER_ID  = "userid";
		public static final String CUSTOM_DATA = "customData";
		public static final String MERCHANT_UNIQUE_ID = "merchant_unique_id";
		public static final String ENCODING = "encoding";
		public static final String WEB_MASTER_ID = "webMasterId";
		
		/*Item Details*/
		public static final String ITEM_OPEN_AMOUNT_1 = "item_open_amount_1";
		public static final String ITEM_MIN_AMOUNT_1 = "item_min_amount_1";
		public static final String ITEM_MAX_AMOUNT_1 = "item_max_amount_1";
		public static final String NUMBER_OF_ITEMS ="numberofitems";
		
		public static final String DISCOUNT = "discount";
		public static final String SHIPPING = "shipping";
		public static final String HANDLING = "handling";
		public static final String TOTAL_AMOUNT = "total_amount";
		public static final String TOTAL_TAX = "total_tax";
		public static final String PRODUCT_ID = "productId";
		
		/*User Token*/
		public static final String USER_TOKEN = "user_token";
		public static final String USER_TOKEN_ID = "user_token_id";
		
		/*Credit Card Details*/
		public static final String CC_CARD_NUMBER = "cc_card_number";
		public static final String CC_NAME_ON_CARD = "cc_name_on_card";
		public static final String CC_EXP_YEAR = "cc_exp_year";
		public static final String CC_EXP_MONTH = "cc_exp_month";
		public static final String CC_CVV2 = "cc_cvv2";
		public static final String DC_START_MONTH = "dc_start_month";
		public static final String DC_START_YEAR = "dc_start_year";
		public static final String DC_ISSUE_NUMBER = "dc_issue_number";
		
		/*Customer Details*/
		public static final String FIRST_NAME = "first_name";
		public static final String LAST_NAME = "last_name";
		public static final String EMAIL = "email";
		public static final String ADDRESS1 = "address1";
		public static final String ADDRESS2 = "address2";
		public static final String CITY = "city";
		public static final String COUNTRY = "country";
	
		public static final String STATE = "state";
		public static final String ZIP = "zip";
		public static final String PHONE1 = "phone1";	
		public static final String PHONE2 = "phone2";
		public static final String PHONE3 = "phone3";
		
		/*Navigation Parameters*/
		public static final String SKIP_BILLING_TAB = "skip_billing_tab";
		public static final String SKIP_REVIEW_TAB ="skip_review_tab";
		public static final String SUCCESS_URL = "success_url";
		public static final String ERROR_URL = "error_url";
		public static final String PENDING_URL = "pending_url";
		public static final String BACK_URL = "back_url";
		public static final String NOTIFY_URL = "notify_url";
		
		/*Merchant Custom Fields*/
		public static final String CUSTOM_SITE_NAME = "customSiteName";
		public static final String CUSTOM_FIELD_ = "customField";
		
		/*Withdrawal*/
		public static final String WD_CURRENCY = "wd_currency";
		public static final String WD_AMOUNT = "wd_amount";
		public static final String WD_MIN_AMOUNT = "wd_min_amount";
		public static final String WD_MAX_AMOUNT = "wd_max_amount";
		
	}

	
	public static final class ResultParams {
		public static final String STATUS = "Status";
		public static final String TOTAL_AMOUNT = "totalAmount";
		public static final String TRANSACTION_ID = "TransactionID";
		public static final String CLIENT_UNIQUE_ID = "ClientUniqueID";
		public static final String ERR_CODE = "ErrCode";
		public static final String EX_ERR_CODE = "ExErrCode";
		public static final String AUTH_CODE = "AuthCode";
		public static final String REASON = "Reason";
		public static final String TOKEN = "Token";
		public static final String REASON_CODE = "ReasonCode";
		public static final String RESPONSE_CHECKSUM = "responsechecksum";
		public static final String ADVANCE_RESPONSE_CHECKSUM = "advanceResponseChecksum";
		public static final String ECI = "ECI";
		
		public static final String NAME_ON_CARD = "nameOnCard";
		public static final String CURRENCY = "currency";
		
		public static final String TOTAL_DISCOUNT = "total_discount";
		public static final String TOTAL_HANDLING = "total_handling";
		public static final String TOTAL_SHIPPING = "total_shipping";
		public static final String TOTAL_TAX = "total_tax";
		public static final String ITEM_NUMBER = "item_number_";
		public static final String ITEM_AMOUNT = "item_amount_";
		public static final String ITEM_SHIPPING = "item_shipping_";
		public static final String ITEM_HANDLING = "item_handling_";
		public static final String ITEM_DISCOUNT = "item_discount_";
		public static final String ITEM_QUANTITY = "item_quantity_";
		
		public static final String CUSTOM_DATA = "customData";
		public static final String MERCHANT_UNIQUE_ID = "merchant_unique_id";
		public static final String MERCHANT_SITE_ID = "merchant_site_id";
		public static final String MERCHANT_ID = "merchant_id";
		public static final String REQUEST_VERSION = "requestVersion";
		public static final String MESSAGE = "message";
		public static final String ERROR = "Error";
		public static final String INSTANT_DMN_STATUS = "instantDmnStatus";
		public static final String PPP_TRANSACTION_ID = "PPP_TransactionID";
		public static final String USER_ID = "UserID";
		public static final String PRODUCT_ID = "ProductID";
		public static final String PPP_STATUS = "ppp_status";
		public static final String MERCHANT_LOCALE = "merchantLocale";
		public static final String UNKNOWN_PARAMETERS = "unknownParameters";
		public static final String WEB_MASTER_ID = "webMasterId";
		public static final String CUSTOM_FIELD_N = "customFieldn";
		
	}
}
