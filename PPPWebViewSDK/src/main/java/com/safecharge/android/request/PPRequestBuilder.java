package com.safecharge.android.request;


import java.util.Date;
import android.annotation.SuppressLint;
import com.safecharge.android.request.models.PPItemDetails;
import com.safecharge.android.util.Constants;
import com.safecharge.android.util.Constants.UserToken;
import com.safecharge.android.util.Util;

/**
 * Utilize building process of {@link PPRequest} with type safe setters
 * @author Bozhidar
 *
 */
public class PPRequestBuilder extends BaseRequestBuilder{
	
	public PPRequestBuilder() {

		super(new PPMD5Encoder(), new PPPCheckSumBuilder());
		this.requestVersion = Constants.VERSION_3_0_0;
	}

	public PPRequestBuilder(boolean useVersion40) {
		super( new PPMD5Encoder(), useVersion40 == true ? new PPPCheckSumBuilder() : new PPPCheckSumBuilder40() );
		if( useVersion40) {
			this.requestVersion = Constants.VERSION_4_0_0;
		} else {
			this.requestVersion = Constants.VERSION_3_0_0;
		}
	}
	
	@Override
	protected String getDefaultUrl() {
		return Constants.URL;
	}


	@Override
	protected String[] getRequiredFilds() {
		return Constants.REQUIRED_FIELDS;
	}


	@Override
	protected String getRequestVersion() {
		return this.requestVersion;
	}
	
	public PPRequestBuilder setItemsDetails(PPItemDetails... items) {
		if (items != null) {
			for (PPItemDetails itemDetais : items) {
				addItem(itemDetais);
			}
		}
	
		return this;
	}
	
	public PPRequestBuilder setMerchantId(long merchantId) {
		putParam(Constants.RequestParams.MERCHANT_ID, String.valueOf(merchantId));
		return this;
	}
	
	public PPRequestBuilder setMerchantSiteId(long merchantSiteId) {
		putParam(Constants.RequestParams.MERCHANT_SITE_ID, String.valueOf(merchantSiteId));
		return this;
	}
	
	public PPRequestBuilder setMerchantUniqueId(String uniqueId) {
		if (uniqueId != null && uniqueId.length() > 64) {
			throw new IllegalArgumentException("MerchantUniqueId cannot contain more than 64 characters");
		}
		putParam(Constants.RequestParams.MERCHANT_UNIQUE_ID, uniqueId); 
		return this;
	}
	
//	private PPPRequestBuilder setVersion(String version) {
//		putParam(Constants.RequestParams.VERSION, version);
//		return this;
//	}
	
	public void setPPPUrl(String url) {
		setUrl(url);
	}
	
	public PPRequestBuilder setTimeStamp(Date date) {
		if (date != null) {
			putParam(Constants.RequestParams.TIME_STAMP, Util.toPPPTimeStamp(date));
		}
		return this;
	}
	
	public PPRequestBuilder setCurrency(String currency) {
		if (currency != null && currency.length() > 3) {
			throw new IllegalArgumentException("Currency cannot contain more than 3 characters");
		}
		
		putParam(Constants.RequestParams.CURRENCY, currency);
		return this;
	}
	
	public PPRequestBuilder setInvoiceId(String invoiceId) {
		if (invoiceId != null && invoiceId.length() > 400) {
			throw new IllegalArgumentException("InvoiceId cannot contain more than 400 characters");
		}
		putParam(Constants.RequestParams.INVOICE_ID, invoiceId);
		return this;
	}
	
	public PPRequestBuilder setPaymentMethod(String paymentMethod) {
		if (paymentMethod != null && paymentMethod.length() > 256) {
			throw new IllegalArgumentException("PaymentMethod cannot contain more than 256 characters");
		}
		putParam(Constants.RequestParams.PAYMENT_METHOD, paymentMethod);
		return this;
	}
	
	public PPRequestBuilder setMerchantLocale(String merchantLocale) {
		if (merchantLocale != null && merchantLocale.length() > 5) {
			throw new IllegalArgumentException("MerchantLocale cannot contain more than 5 characters");
		}
		putParam(Constants.RequestParams.MERCHANT_LOCALE, merchantLocale);
		return this;
	}
	
	public PPRequestBuilder setUserid(String userid) {
		if (userid != null && userid.length() > 50) {
			throw new IllegalArgumentException("Userid cannot contain more than 50 characters");
		}
		putParam(Constants.RequestParams.USER_ID, userid);
		return this;
	}
	
	public PPRequestBuilder setCustomData(String customData) {
		if (customData != null && customData.length() > 255) {
			throw new IllegalArgumentException("CustomData cannot contain more than 255 characters");
		}
		putParam(Constants.RequestParams.CUSTOM_DATA, customData);
		return this;
	}
	
	public PPRequestBuilder setEncoding(String encoding) {
		if (encoding != null && encoding.length() > 20) {
			throw new IllegalArgumentException("Encoding cannot contain more than 20 characters");
		}
		putParam(Constants.RequestParams.ENCODING, encoding);
		return this;
	}
	
	public PPRequestBuilder setWebMasterId(String webMasterId) {
		if (webMasterId != null && webMasterId.length() > 255) {
			throw new IllegalArgumentException("WebMasterId cannot contain more than 255 characters");
		}
		putParam(Constants.RequestParams.WEB_MASTER_ID, webMasterId);
		return this;
	}
	
	public PPRequestBuilder setItemOpenAmount1(boolean itemOpenAmount1) {
		putParam(Constants.RequestParams.ITEM_OPEN_AMOUNT_1, String.valueOf(itemOpenAmount1));
		return this;
	}
	
	public PPRequestBuilder setItemMinAmount1(double itemMinAmount1) {
		putParam(Constants.RequestParams.ITEM_MIN_AMOUNT_1, Constants.DECIMAL_FORMAT.format(itemMinAmount1));
		return this;
	}
	
	public PPRequestBuilder setItemMaxAmount1(double itemMaxAmount) {
		putParam(Constants.RequestParams.ITEM_MAX_AMOUNT_1, Constants.DECIMAL_FORMAT.format(itemMaxAmount));
		return this;
	}
	
	public PPRequestBuilder setNumberOfItems(int numberofitems) {
		putParam(Constants.RequestParams.NUMBER_OF_ITEMS, String.valueOf(numberofitems));
		return this;
	}
	
	public PPRequestBuilder setDiscount(double discount) {
		putParam(Constants.RequestParams.DISCOUNT, Constants.DECIMAL_FORMAT.format(discount));
		return this;
	}
	
	public PPRequestBuilder setShipping(double shipping) {
		putParam(Constants.RequestParams.SHIPPING, Constants.DECIMAL_FORMAT.format(shipping));
		return this;
	}
	
	public PPRequestBuilder setHandling(double handling) {
		putParam(Constants.RequestParams.HANDLING, Constants.DECIMAL_FORMAT.format(handling));
		return this;
	}
	
	public PPRequestBuilder setTotalAmount(double totalAmount) {
		putParam(Constants.RequestParams.TOTAL_AMOUNT,  Constants.DECIMAL_FORMAT.format(totalAmount));
		return this;
	}
	
	public PPRequestBuilder setTotalTax(double totalTax) {
		putParam(Constants.RequestParams.TOTAL_TAX, Constants.DECIMAL_FORMAT.format(totalTax));
		return this;
	}
	
	public PPRequestBuilder setProductId(String productId) {
		if (productId != null && productId.length() > 50) {
			throw new IllegalArgumentException("ProductId cannot contain more than 50 characters");
		}
		putParam(Constants.RequestParams.PRODUCT_ID, productId);
		return this;
	}
	
	@SuppressLint("DefaultLocale")
	public PPRequestBuilder setUserToken(UserToken userToken) {
		if (userToken != null) {
			putParam(Constants.RequestParams.USER_TOKEN, userToken.name().toLowerCase());
		}
		return this;
	}
	
	public PPRequestBuilder setUserTokenId(String userTokenId) {
		if (userTokenId != null && userTokenId.length() > 255) {
			throw new IllegalArgumentException("UserTokenId cannot contain more than 255 characters");
		}
		putParam(Constants.RequestParams.USER_TOKEN_ID, userTokenId);
		return this;
	}
	
	public PPRequestBuilder setCCCardNumber(String cardNumber) {
		if (cardNumber != null && cardNumber.length() > 20) {
			throw new IllegalArgumentException("CardNumber cannot contain more than 20 characters");
		}
		putParam(Constants.RequestParams.CC_CARD_NUMBER, cardNumber);
		return this;
	}
	
	public PPRequestBuilder setNameOnCard(String name) {
		if (name != null && name.length() > 70) {
			throw new IllegalArgumentException("NameOnCard cannot contain more than 70 characters");
		}
		putParam(Constants.RequestParams.CC_NAME_ON_CARD, name);
		return this;
	}
	
	public PPRequestBuilder setCCExpYear(int year) {
		putParam(Constants.RequestParams.CC_EXP_YEAR, String.valueOf(year));
		return this;
	}
	
	public PPRequestBuilder setCCExpMonth(int month) {
		putParam(Constants.RequestParams.CC_EXP_MONTH, String.valueOf(month));
		return this;
	}
	
	public PPRequestBuilder setCVV2(int cvv2) {
		if (cvv2 > 999) {
			throw new IllegalArgumentException("cvv2 must be 3 digits long");
		}
		putParam(Constants.RequestParams.CC_CVV2, String.valueOf(cvv2));
		return this;
	}
	
	public PPRequestBuilder setDCStartMonth(int month) {
		if (month > 12) {
			throw new IllegalArgumentException("Invalid month");
		}
		putParam(Constants.RequestParams.DC_START_MONTH, String.valueOf(month));
		return this;
	}
	
	public PPRequestBuilder setDCStartYear(int year) {
		putParam(Constants.RequestParams.DC_START_YEAR, String.valueOf(year));
		return this;
	}
	
	public PPRequestBuilder setDCIssueNumber(int number) {
		putParam(Constants.RequestParams.DC_START_YEAR, String.valueOf(number));
		return this;
	}
	
	public PPRequestBuilder setFirstName(String firstName) {
		if (firstName != null && firstName.length() > 30) {
			throw new IllegalArgumentException("FirstName cannot contain more than 30 characters");
		}
		putParam(Constants.RequestParams.FIRST_NAME, firstName);
		return this;
	}
	
	public PPRequestBuilder setLastName(String lastName) {
		if (lastName != null && lastName.length() > 40) {
			throw new IllegalArgumentException("LastName cannot contain more than 40 characters");
		}
		putParam(Constants.RequestParams.LAST_NAME, lastName);
		return this;
	}
	
	public PPRequestBuilder setEmail(String email) {
		if (email != null && email.length() > 100) {
			throw new IllegalArgumentException("Email cannot contain more than 100 characters");
		}
		putParam(Constants.RequestParams.EMAIL, email);
		return this;
	}
	
	public PPRequestBuilder setAddress1(String address1) {
		if (address1 != null && address1.length() > 60) {
			throw new IllegalArgumentException("Address1 cannot contain more than 60 characters");
		}
		putParam(Constants.RequestParams.ADDRESS1, address1);
		return this;
	}

	public PPRequestBuilder setAddress2(String address2) {
		if (address2 != null && address2.length() > 60) {
			throw new IllegalArgumentException("Address2 cannot contain more than 60 characters");
		}
		putParam(Constants.RequestParams.ADDRESS2, address2);
		return this;
	}
	
	public PPRequestBuilder setCity(String city) {
		if (city != null && city.length() > 30) {
			throw new IllegalArgumentException("City cannot contain more than 30 characters");
		}
		putParam(Constants.RequestParams.CITY, city);
		return this;
	}
	
	public PPRequestBuilder setCountry(String country) {
		if (country != null && country.length() > 20) {
			throw new IllegalArgumentException("Country cannot contain more than 20 characters");
		}
		putParam(Constants.RequestParams.COUNTRY, country);
		return this;
	}
	
	public PPRequestBuilder setState(String state) {
		if (state != null && state.length() > 20) {
			throw new IllegalArgumentException("State cannot contain more than 20 characters");
		}
		putParam(Constants.RequestParams.STATE, state);
		return this;
	}
	
	public PPRequestBuilder setZip(String zip) {
		if (zip != null && zip.length() > 10) {
			throw new IllegalArgumentException("ZIP cannot contain more than 10 characters");
		}
		putParam(Constants.RequestParams.ZIP, zip);
		return this;
	}
	
	public PPRequestBuilder setPhone1(String phone1) {
		if (phone1 != null && phone1.length() > 18) {
			throw new IllegalArgumentException("Phone1 cannot contain more than 18 characters");
		}
		putParam(Constants.RequestParams.PHONE1, phone1);
		return this;
	}
	
	public PPRequestBuilder setPhone2(String phone2) {
		if (phone2 != null && phone2.length() > 18) {
			throw new IllegalArgumentException("Phone2 cannot contain more than 18 characters");
		}
		putParam(Constants.RequestParams.PHONE2, phone2);
		return this;
	}
	
	public PPRequestBuilder setPhone3(String phone3) {
		if (phone3 != null && phone3.length() > 18) {
			throw new IllegalArgumentException("Phone3 cannot contain more than 18 characters");
		}
		putParam(Constants.RequestParams.PHONE3, phone3);
		return this;
	}
	
	public PPRequestBuilder setSkipBillingTab(boolean skip) {
		putParam(Constants.RequestParams.SKIP_BILLING_TAB, String.valueOf(skip));
		return this;
	}
	
	public PPRequestBuilder setSkipReviewTab(boolean skip) {
		putParam(Constants.RequestParams.SKIP_REVIEW_TAB, String.valueOf(skip));
		return this;
	}
	
	public PPRequestBuilder setSuccessUrl(String url) {
		if (url != null && url.length() > 2048) {
			throw new IllegalArgumentException("SuccessUrl cannot contain more than 2048 characters");
		}
		putParam(Constants.RequestParams.SUCCESS_URL, url);
		return this;
	}
	
	public PPRequestBuilder setErrorUrl(String url) {
		if (url != null && url.length() > 2048) {
			throw new IllegalArgumentException("ErrorUrl cannot contain more than 2048 characters");
		}
		putParam(Constants.RequestParams.ERROR_URL, url);
		return this;
	}
	
	public PPRequestBuilder setPendingUrl(String url) {
		if (url != null && url.length() > 2048) {
			throw new IllegalArgumentException("PendingUrl cannot contain more than 2048 characters");
		}
		putParam(Constants.RequestParams.PENDING_URL, url);
		return this;
	}
	public PPRequestBuilder setBackUrl(String url) {
		if (url != null && url.length() > 2048) {
			throw new IllegalArgumentException("BackUrl cannot contain more than 2048 characters");
		}
		putParam(Constants.RequestParams.BACK_URL, url);
		return this;
	}
	
	public PPRequestBuilder setNotifyUrl(String url) {
		if (url != null && url.length() > 2048) {
			throw new IllegalArgumentException("NotifyUrl cannot contain more than 2048 characters");
		}
		putParam(Constants.RequestParams.NOTIFY_URL, url);
		return this;
	}
	
	public PPRequestBuilder setCustomSiteName(String siteName) {
		if (siteName != null && siteName.length() > 50) {
			throw new IllegalArgumentException("CustomSiteName cannot contain more than 50 characters");
		}
		putParam(Constants.RequestParams.CUSTOM_SITE_NAME, siteName);
		return this;
	}
	
	public PPRequestBuilder setCustomFields(String... filds) {
		if (filds != null ) {
			if (filds.length > 15) {
				throw new IllegalArgumentException("Custom fileds should be <= 15");
			}
			for (int i = 0; i < filds.length; i++) {
				String fieldValue = filds[i];
				if (fieldValue != null && fieldValue.length() > 64) {
					throw new IllegalArgumentException("CustomField: "+ fieldValue + " contain's more than 64 characters");
				}
				putParam(Constants.RequestParams.CUSTOM_FIELD_+i, filds[i]);
			}
		}
		
		return this;
	}

	public PPRequestBuilder setSecretKey(String key) {
		super.setRequestSecretKey(key);
		return this;
	}
	
	public PPRequestBuilder setCheckSumEncoder(IEncoder checkSumEncoder) {
		setCheckSumEncoderImp(checkSumEncoder);
		return this;
	}

	/**
	 * Mandatory if there is no CheckSum. 
	 * @param checkSumBuilder
	 * @return
	 */
	public PPRequestBuilder setCheckSumBuilder(ICheckSumBuilder checkSumBuilder) {
		setCheckSumBuilderImp(checkSumBuilder);
		return this;
	}
	
	/**
	 * 
	 * @param checkSum
	 * @param timeStamp - used for CheckSum calculation
	 * @return
	 */
	public PPRequestBuilder setCheckSum(String checkSum, String timeStamp) {
		this.putParam(Constants.RequestParams.CHECKSUM, checkSum);
		if (timeStamp != null) {
			putParam(Constants.RequestParams.TIME_STAMP, timeStamp);
		}
		return this;
	}

	
	/**
	 * Check for missing mandatory params. 
	 */
	protected void validateRequest() {
		if (getItems() == null || getItems().size() == 0) {
			throw new IllegalStateException("You should provide at least one ItemsDetails!");
		}
		String itemOpenAmount1 = getParam(Constants.RequestParams.ITEM_OPEN_AMOUNT_1);
		
		if (itemOpenAmount1 != null && itemOpenAmount1.equals(String.valueOf(true))) {
			String itemMaxAmount1 = getParam(Constants.RequestParams.ITEM_MAX_AMOUNT_1);
			String itemMinAmount1 = getParam(Constants.RequestParams.ITEM_MIN_AMOUNT_1);
			
			if (itemMaxAmount1 == null || itemMinAmount1 == null) {
				throw new IllegalStateException("item_max_amount_1 and item_min_amount_1 are mandatory if item_open_amount_1 == true");
			}
		}
		
		super.validateRequest();
	}
}
