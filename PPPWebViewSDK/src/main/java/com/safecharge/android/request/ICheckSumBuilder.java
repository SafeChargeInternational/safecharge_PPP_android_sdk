package com.safecharge.android.request;



/**
 * Abstraction for building checksum
 * @author Bozhidar
 *
 */
public interface ICheckSumBuilder {
	/**
	 * Build checksum string
	 * @param request
	 * @return
	 */
	String buildCheckSum(PPRequest request, String secretKey);
}
