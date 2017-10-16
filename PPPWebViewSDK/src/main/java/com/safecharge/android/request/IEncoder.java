package com.safecharge.android.request;

/**
 * Abstraction for encoding
 * @author Bozhidar
 *
 */
public interface IEncoder {
	
	/**
	 * Encode passed string
	 * @param request
	 * @return
	 */
	String encode(String request);
}
