package com.safecharge.android.request;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.safecharge.android.util.Constants;

/**
 * Default MD5 implementation of {@link IEncoder} for {@link PPRequest}
 * @author Bozhidar
 *
 */
public class PPMD5Encoder implements IEncoder {
	private static final char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	
	@Override
	public String encode(String checkSum) {
		return getHash(checkSum, Constants.UTF_8);
	}
	
	private String getHash(String text, String charset) {
		if (text == null) {
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException nsae) {
			System.err.println("MD5 implementation not found: " + nsae.getMessage());
			return null;
		}

		CharsetEncoder encoder = Charset.forName(charset).newEncoder();

		ByteBuffer encoded = null;
		try {
			encoded = encoder.encode(CharBuffer.wrap(text));
		} catch (CharacterCodingException e) {
			System.err.println("Cannot encode text into bytes: " + e.getMessage());
			return null;
		}

		byte[] inbytes = null;

		inbytes = new byte[encoded.remaining()];
		encoded.get(inbytes, 0, inbytes.length);
		byte[] bytes = md.digest(inbytes);

		// Output the bytes of the hash as a String (text/plain)
		StringBuilder sb = new StringBuilder(2 * bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			int low = (bytes[i] & 0x0f);
			int high = ((bytes[i] & 0xf0) >> 4);
			sb.append(HEXADECIMAL[high]);
			sb.append(HEXADECIMAL[low]);
		}

		return sb.toString();
	}
}
