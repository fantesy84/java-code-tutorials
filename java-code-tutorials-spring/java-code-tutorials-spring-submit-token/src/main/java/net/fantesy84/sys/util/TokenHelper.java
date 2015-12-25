/**
 * Project java-code-tutorials-spring-commit-token
 * File: TokenHelper.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.sys.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TypeName: TokenHelper
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月23日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class TokenHelper {
	private static final Logger logger = LoggerFactory.getLogger(TokenHelper.class);
	private static TokenHelper instance;
	
	private TokenHelper() {
		
	}
	
	public static TokenHelper getInstance() {
		if (instance == null) {
			synchronized (TokenHelper.class) {
				if (instance == null) {
					instance = new TokenHelper();
				}
			}
		}
		return instance;
	}
	
	public String generateToken() {
		byte now[] = new Long(System.currentTimeMillis()).toString().getBytes();
		MessageDigest md = null;
		String token = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(now);
			byte[] digest = md.digest();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				int val = ((int)digest[i]) & 0xff;
				if (val < 16) {
					builder.append("0");
				}
				builder.append(Integer.toHexString(val));
			}
			token = builder.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return token;
	}
	
	public String generateToken(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		byte id[] = session.getId().getBytes();
		byte now[] = new Long(System.currentTimeMillis()).toString().getBytes();
		MessageDigest md = null;
		String token = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			byte[] digest = md.digest();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				int val = ((int)digest[i]) & 0xff;
				if (val < 16) {
					builder.append("0");
				}
				builder.append(Integer.toHexString(val));
			}
			token = builder.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return token;
	}
	
}
