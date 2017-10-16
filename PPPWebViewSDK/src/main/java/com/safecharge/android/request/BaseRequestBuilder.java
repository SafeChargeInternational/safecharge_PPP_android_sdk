package com.safecharge.android.request;


import java.util.Calendar;
import java.util.List;

import android.util.Log;

import com.safecharge.android.request.models.PPItemDetails;
import com.safecharge.android.util.Constants;
import com.safecharge.android.util.Util;

/**
 * Encapsulate common builder's logic, all builders should extend this class
 * @author Bozhidar
 *
 */
 abstract class BaseRequestBuilder {
	private static final String TAG = "RequestBuilder";
	private PPRequest request;
	private String secretKey;
	
	private IEncoder checkSumEncoder;
	private ICheckSumBuilder checkSumBuilder;

	protected String requestVersion;

	abstract protected String getDefaultUrl();
	
	abstract protected String[] getRequiredFilds();
	
	abstract protected String getRequestVersion();
	
	protected BaseRequestBuilder(IEncoder checkSumEncoder, ICheckSumBuilder checkSumBuilder) {
		this.checkSumBuilder = checkSumBuilder;
		this.checkSumEncoder = checkSumEncoder;
		this.request = new PPRequest();
	}
	
	protected void putParam(String key, String value) {
		request.putParam(key, value);
	}
	
	protected String getParam(String key) {
		return request.getParam(key);
	}
	
	protected void addItem(PPItemDetails item) {
		request.addItem(item);
	}
	
	protected List<PPItemDetails> getItems() {
		return request.getItems();
	}
	
	protected void setUrl(String url) {
		request.setUrl(url);
	}
	
	protected String getUrl() {
		return request.getUrl();
	}
	
	protected void setRequestSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	protected String getRequestSecretKey() {
		return this.secretKey;
	}
	
	protected void setCheckSumEncoderImp(IEncoder checkSumEncoder) {
		this.checkSumEncoder = checkSumEncoder;
	}

	/**
	 * Mandatory if there is no CheckSum. 
	 * @param checkSumBuilder
	 * @return
	 */
	protected void setCheckSumBuilderImp(ICheckSumBuilder checkSumBuilder) {
		this.checkSumBuilder = checkSumBuilder;
	}
	
	public PPRequest buildRequest() {
			
		String timeStamp = request.getParam(Constants.RequestParams.TIME_STAMP);
		if (timeStamp == null) {
			request.putParam(Constants.RequestParams.TIME_STAMP, Util.toPPPTimeStamp(Calendar.getInstance().getTime()));
		}
			
		String version = getParam(Constants.RequestParams.VERSION);
		if (version == null) {
			putParam(Constants.RequestParams.VERSION, getRequestVersion());
		}
		
		String url = getUrl();
		if (url == null) {
			setUrl(getDefaultUrl());
		}
		
		validateRequest();

		request.addItemsDetais();

		String checkSum = request.getParam(Constants.RequestParams.CHECKSUM);
		if (checkSum == null) {
			checkSum = checkSumBuilder.buildCheckSum(request, secretKey);
				 
			if (checkSumEncoder != null) {
				checkSum = checkSumEncoder.encode(checkSum);
			} else {
				Log.w(TAG, "CheckSumEncoder is null!");
			}
			if (checkSum != null) {
				request.putParam(Constants.RequestParams.CHECKSUM, checkSum);
			} else {
				throw new IllegalStateException("CheckSumBuilder can't return null!");
			}
		}
		
		return new PPRequest(request);
	}
	
	/**
	 * Validate request
	 */
	protected void validateRequest()  {
		
		String [] requiredFileds = getRequiredFilds();
		if (requiredFileds != null) {
			for (String  requiredField : requiredFileds) {
				String value = request.getParam(requiredField);
				if (value == null) {
					throw new IllegalStateException("Missing mandatory pram: " + requiredField);
				}
			}
		}
		
		String url = getUrl();
		if (url == null) {
			throw new IllegalStateException("Missing request URL");
		}
		
		String checkSum = request.getParam(Constants.RequestParams.CHECKSUM);
		
		if (checkSum == null) {
			if (secretKey == null) {
				throw new IllegalStateException("Missing CheckSum and SecretKey for creating one");
			}
			
			if (checkSumBuilder == null) {
				throw new IllegalStateException("Missing CheckSum and CheckSumBuilder for creating one!");
			}
		}
	}
}
