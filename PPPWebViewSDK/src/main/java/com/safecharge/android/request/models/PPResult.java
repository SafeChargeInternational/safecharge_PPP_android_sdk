package com.safecharge.android.request.models;

import java.util.List;

/**
 * Holder for base ppp respose params. {@see Util}
 * @author Bozhidar
 *
 */
public class PPResult {
	private String status;
	private String clientUniqueID;
	private String authCode;
	private String reason;
	private String token;
	private String responseCheckSum;
	private String advanceResponseChecksum;
	private String eci;
	private String nameOnCard;
	private String currency;
	private String customData;
	private String merchantUniqueId;
	private String requestVersion;
	private String message;
	private String error;
	private String instantDmnStatus;
	private String userID;
	private String productID;
	private String pppStatus;
	private String merchantLocale;
	private String unknownParameters;
	private String webMasterId;
	
	private long transactionId;
	private long merchantSiteId;
	private long merchantId;
	private long pppTransactionID;
	
	private int errCode;
	private int exErrCode;
	private int reasonCode;
	
	private double totalAmount;
	private double totalDiscount;
	private double totalHandling;
	private double totalShipping;
	private double totalTax;
	
	private List<BaseItemDetais> items;
	private List<String> customFilds;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public String getClientUniqueID() {
		return clientUniqueID;
	}
	public void setClientUniqueID(String clientUniqueID) {
		this.clientUniqueID = clientUniqueID;
	}
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public int getExErrCode() {
		return exErrCode;
	}
	public void setExErrCode(int exErrCode) {
		this.exErrCode = exErrCode;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
	public String getResponseCheckSum() {
		return responseCheckSum;
	}
	public void setResponseCheckSum(String responseCheckSum) {
		this.responseCheckSum = responseCheckSum;
	}
	public String getAdvanceResponseChecksum() {
		return advanceResponseChecksum;
	}
	public void setAdvanceResponseChecksum(String advanceResponseChecksum) {
		this.advanceResponseChecksum = advanceResponseChecksum;
	}
	public String getEci() {
		return eci;
	}
	public void setEci(String eci) {
		this.eci = eci;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public double getTotalHandling() {
		return totalHandling;
	}
	public void setTotalHandling(double totalHandling) {
		this.totalHandling = totalHandling;
	}
	public double getTotalShipping() {
		return totalShipping;
	}
	public void setTotalShipping(double totalShipping) {
		this.totalShipping = totalShipping;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public String getCustomData() {
		return customData;
	}
	public void setCustomData(String customData) {
		this.customData = customData;
	}
	public String getMerchantUniqueId() {
		return merchantUniqueId;
	}
	public void setMerchantUniqueId(String merchantUniqueId) {
		this.merchantUniqueId = merchantUniqueId;
	}
	public long getMerchantSiteId() {
		return merchantSiteId;
	}
	public void setMerchantSiteId(long merchantSiteId) {
		this.merchantSiteId = merchantSiteId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getRequestVersion() {
		return requestVersion;
	}
	public void setRequestVersion(String requestVersion) {
		this.requestVersion = requestVersion;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getInstantDmnStatus() {
		return instantDmnStatus;
	}
	public void setInstantDmnStatus(String instantDmnStatus) {
		this.instantDmnStatus = instantDmnStatus;
	}
	public long getPppTransactionID() {
		return pppTransactionID;
	}
	public void setPppTransactionID(long pppTransactionID) {
		this.pppTransactionID = pppTransactionID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getPppStatus() {
		return pppStatus;
	}
	public void setPppStatus(String pppStatus) {
		this.pppStatus = pppStatus;
	}
	public String getMerchantLocale() {
		return merchantLocale;
	}
	public void setMerchantLocale(String merchantLocale) {
		this.merchantLocale = merchantLocale;
	}
	public String getUnknownParameters() {
		return unknownParameters;
	}
	public void setUnknownParameters(String unknownParameters) {
		this.unknownParameters = unknownParameters;
	}
	public String getWebMasterId() {
		return webMasterId;
	}
	public void setWebMasterId(String webMasterId) {
		this.webMasterId = webMasterId;
	}
	public List<BaseItemDetais> getItems() {
		return items;
	}
	public void setItems(List<BaseItemDetais> items) {
		this.items = items;
	}
	public List<String> getCustomFilds() {
		return customFilds;
	}
	public void setCustomFilds(List<String> customFilds) {
		this.customFilds = customFilds;
	}
	
	
}
