package com.safecharge.android.request;


/**
 * Default implementation of {@link ICheckSumBuilder} for Withdrawal request, used to construct checksum for {@link PPRequest}
 * @author Bozhidar
 *
 */
public class PPWithdrawalCheckSumBuilder implements ICheckSumBuilder{

	@Override
	public String buildCheckSum(PPRequest request, String secretKey) {
		if (secretKey == null) {
			throw new IllegalStateException("SecretKey can't be null !");
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(secretKey);
		
		for (String paramsKey : request.getParamsKeys()) {
			builder.append(request.getParam(paramsKey));
		}
		
		return builder.toString();
	}
}
