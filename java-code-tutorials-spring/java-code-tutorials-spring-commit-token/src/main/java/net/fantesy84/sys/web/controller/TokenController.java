/**
 * Project java-code-tutorials-spring-commit-token
 * File: BaseController.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.sys.web.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fantesy84.sys.web.constants.WebConstants;

/**
 * TypeName: BaseController
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月23日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
@RestController
@RequestMapping("/token")
public class TokenController implements Serializable {
	private static final long serialVersionUID = -8612413390853464038L;
	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
	
	@RequestMapping("/generate")
	public String generateToken(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
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
		if (token != null && token.length() > 0) {
			session.setAttribute(WebConstants.REQUEST_SUBMIT_TOKEN, token);
		}
		return token;
	}
}
