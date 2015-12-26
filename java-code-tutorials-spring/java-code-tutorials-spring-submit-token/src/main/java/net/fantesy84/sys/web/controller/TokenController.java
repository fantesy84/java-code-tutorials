/**
 * Project java-code-tutorials-spring-commit-token
 * File: BaseController.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.sys.web.controller;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import net.fantesy84.sys.dto.BaseTokenDTO;
import net.fantesy84.sys.util.TokenHelper;
import net.fantesy84.sys.web.constants.WebConstants;

/**
 * TypeName: BaseController
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
	public BaseTokenDTO getStoreToken(HttpServletRequest request, HttpServletResponse response) {
		BaseTokenDTO dto = new BaseTokenDTO();
		try {
			HttpSession session = request.getSession(false);
			if (session == null) {
    			logger.debug(">>>>>>>> New connector! Pass! <<<<<<<<");
    			session = request.getSession(true);
			}
			String token = TokenHelper.getInstance().generateToken(request);
			if (token != null && token.length() > 0) {
				dto.setResult(Boolean.TRUE);
				dto.setToken(token);
				RequestContextHolder.getRequestAttributes().setAttribute(WebConstants.REQUEST_SUBMIT_TOKEN, token, RequestAttributes.SCOPE_SESSION);
				logger.info(">>>>>>>> Stored token:[{}] <<<<<<<<", token);
				RequestContextHolder.getRequestAttributes().setAttribute(WebConstants.RESET_SUBMIT_TOKEN, Boolean.FALSE, RequestAttributes.SCOPE_SESSION);
				logger.info(">>>>>>>> Set reset marker [FALSE] <<<<<<<<");
			} else {
				dto.setResult(Boolean.FALSE);
			}
			response.setHeader("Last-Modified", new Date().toString());
			response.setDateHeader("Etag", System.currentTimeMillis());
		} catch (Exception e) {
			dto.setResult(Boolean.FALSE);
		}
		return dto;
	}
}
