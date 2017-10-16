package com.safecharge.android.request;

import java.util.Date;
import android.annotation.SuppressLint;
import com.safecharge.android.util.Constants;
import com.safecharge.android.util.Constants.UserToken;
import com.safecharge.android.util.Util;


/**
 * Utilize building process of {@link PPRequest} with type safe setters
 * @author Bozhidar
 *
 */
public class PPWithdrawalRequestBuilder extends BaseRequestBuilder{
	
	
	public PPWithdrawalRequestBuilder() {
		super(new PPMD5Encoder(), new PPWithdrawalCheckSumBuilder());
	}
	
	@Override
	protected String getDefaultUrl() {
		return Constants.WITHDRAWAL_URL;
	}

	@Override
	protected String[] getRequiredFilds() {
		return Constants.REQUIRED_WITHDRAWAL_FIELDS;
	}

	@Override
	protected String getRequestVersion() {
		return Constants.VERSION_4_0_0;
	}
	
	
	public PPWithdrawalRequestBuilder setMerchantId(long merchantId) {
		putParam(Constants.RequestParams.MERCHANT_ID, String.valueOf(merchantId));
		return this;
	}
	
	public PPWithdrawalRequestBuilder setMerchantSiteId(long merchantSiteId) {
		putParam(Constants.RequestParams.MERCHANT_SITE_ID, String.valueOf(merchantSiteId));
		return this;
	}
	
	@SuppressLint("DefaultLocale")
	public PPWithdrawalRequestBuilder setUserToken(UserToken userToken) {
		if (userToken != null && userToken == UserToken.READONLY) {
			throw new IllegalArgumentException("UserToken cannot be READONLY");
		}
		if (userToken != null) {
			putParam(Constants.RequestParams.USER_TOKEN, userToken.name().toLowerCase());
		}
		return this;
	}
	
	public PPWithdrawalRequestBuilder setMerchantLocale(String merchantLocale) {
		if (merchantLocale != null && merchantLocale.length() > 5) {
			throw new IllegalArgumentException("MerchantLocale cannot contain more than 5 characters");
		}
		putParam(Constants.RequestParams.MERCHANT_LOCALE, merchantLocale);
		return this;
	}
	
	public PPWithdrawalRequestBuilder setWithdrawalAmount(double amount) {
		putParam(Constants.RequestParams.WD_AMOUNT, String.valueOf(amount));
		return this;
	}
	
	public PPWithdrawalRequestBuilder setWithdrawalCurrency(String currency) {
		if (currency != null && currency.length() > 3) {
			throw new IllegalArgumentException("Currency cannot contain more than 3 characters");
		}
		
		putParam(Constants.RequestParams.WD_CURRENCY, currency);
		return this;
	}
	
	public PPWithdrawalRequestBuilder setCountry(String country) {
		if (country != null && country.length() > 20) {
			throw new IllegalArgumentException("Country cannot contain more than 20 characters");
		}
		putParam(Constants.RequestParams.COUNTRY, country);
		return this;
	}
	
	public PPWithdrawalRequestBuilder setWithdrawalMinAmount(double amount) {
		putParam(Constants.RequestParams.WD_MIN_AMOUNT, Constants.DECIMAL_FORMAT.format(amount));
		return this;
	}
	
	
	public PPWithdrawalRequestBuilder setWithdrawalMaxAmount(double amount) {
		putParam(Constants.RequestParams.WD_MAX_AMOUNT, Constants.DECIMAL_FORMAT.format(amount));
		return this;
	}
	
	public PPWithdrawalRequestBuilder setUserTokenId(String userTokenId) {
		if (userTokenId != null && userTokenId.length() > 255) {
			throw new IllegalArgumentException("UserTokenId cannot contain more than 255 characters");
		}
		putParam(Constants.RequestParams.USER_TOKEN_ID, userTokenId);
		return this;
	}
	
	public PPWithdrawalRequestBuilder setUserid(String userid) {
		if (userid != null && userid.length() > 50) {
			throw new IllegalArgumentException("Userid cannot contain more than 50 characters");
		}
		putParam(Constants.RequestParams.USER_ID, userid);
		return this;
	}
	
	public PPWithdrawalRequestBuilder setTimeStamp(Date date) {
		if (date != null) {
			putParam(Constants.RequestParams.TIME_STAMP, Util.toPPPTimeStamp(date));
		}
		return this;
	}
	
//	private PPPWithdrawalRequestBuilder setVersion(String version) {
//		putParam(Constants.RequestParams.VERSION, version);
//		return this;
//	}
	
	public void setPPPUrl(String url) {
		setUrl(url);
	}
	
	public PPWithdrawalRequestBuilder setSecretKey(String key) {
		super.setRequestSecretKey(key);
		return this;
	}
	
	public PPWithdrawalRequestBuilder setCheckSumEncoder(IEncoder checkSumEncoder) {
		setCheckSumEncoderImp(checkSumEncoder);
		return this;
	}

	/**
	 * Mandatory if there is no CheckSum. 
	 * @param checkSumBuilder
	 * @return
	 */
	public PPWithdrawalRequestBuilder setCheckSumBuilder(ICheckSumBuilder checkSumBuilder) {
		setCheckSumBuilderImp(checkSumBuilder);
		return this;
	}
	
	/**
	 * 
	 * @param checkSum
	 * @param timeStamp - used for CheckSum calculation
	 * @return
	 */
	public PPWithdrawalRequestBuilder setCheckSum(String checkSum, String timeStamp) {
		this.putParam(Constants.RequestParams.CHECKSUM, checkSum);
		if (timeStamp != null) {
			putParam(Constants.RequestParams.TIME_STAMP, timeStamp);
		}
		return this;
	}
	
	@Override
	public PPRequest buildRequest() {
		String merchantLocale = getParam(Constants.RequestParams.MERCHANT_LOCALE);
		if (merchantLocale == null) {
			putParam(Constants.RequestParams.MERCHANT_LOCALE, Constants.UNDEFINED);
		}
		return super.buildRequest();
	}
	
}
