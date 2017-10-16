package com.safecharge.android.request;

import com.safecharge.android.request.models.PPItemDetails;
import com.safecharge.android.util.Constants;

/**
 * Default implementation of {@link ICheckSumBuilder} used to construct checksum for {@link PPRequest}
 * @author Bozhidar
 *
 */
public class PPPCheckSumBuilder implements ICheckSumBuilder {
	
	
	@Override
	public String buildCheckSum(PPRequest request, String secretKey) {
		if (secretKey == null) {
			throw new IllegalStateException("SecretKey can't be null !");
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(secretKey);
		builder.append(request.getParam(Constants.RequestParams.MERCHANT_ID));
		builder.append(request.getParam(Constants.RequestParams.CURRENCY));
		builder.append(request.getParam(Constants.RequestParams.TOTAL_AMOUNT));
		
		for (int i = 0; i < request.getItems().size(); i++) {
			PPItemDetails item = request.getItems().get(i);
			builder.append(item.getItemName()).append(Constants.DECIMAL_FORMAT.format(item.getItemAmount())).append(String.valueOf(item.getItemQuantity()));
		}
		
		String itemOpenAmount = request.getParam(Constants.RequestParams.ITEM_OPEN_AMOUNT_1);
		if (itemOpenAmount != null) {
			builder.append(itemOpenAmount);
			builder.append(request.getParam(Constants.RequestParams.ITEM_MIN_AMOUNT_1));
			builder.append(request.getParam(Constants.RequestParams.ITEM_MAX_AMOUNT_1));
		}
		
		builder.append(request.getParam(Constants.RequestParams.USER_TOKEN_ID));
		builder.append(request.getParam(Constants.RequestParams.TIME_STAMP));
		
		return builder.toString();
	}
	
}
