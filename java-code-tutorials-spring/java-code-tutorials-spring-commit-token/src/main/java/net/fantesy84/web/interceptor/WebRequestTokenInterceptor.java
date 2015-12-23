/**
 * Project java-code-tutorials-spring-commit-token
 * File: WebRequestTokenInterceptor.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.web.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.fantesy84.sys.util.TokenHelper;
import net.fantesy84.sys.web.annotation.DuplicateSubmitValidate;
import net.fantesy84.sys.web.constants.WebConstants;

/**
 * TypeName: WebRequestTokenInterceptor
 * 
 * <P>CreateTime: 2015年12月23日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class WebRequestTokenInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(WebRequestTokenInterceptor.class);
	private static final String DEFAULT_TOKEN_NAME = "token";
	private String tokenName = DEFAULT_TOKEN_NAME;
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        DuplicateSubmitValidate validateAnnotation = method.getAnnotation(DuplicateSubmitValidate.class);
        if (validateAnnotation != null) {
        	logger.info("This method: [{}] will validate token!", method.getName());
        	boolean needRemove = validateAnnotation.removeToken();
        	if (needRemove) {
        		if (isRepeatSubmit(request)) {
        			logger.warn("Please don't repeat submit! URI:[{}]", request.getServletPath());
        			return false;
        		}
        		request.getSession(false).removeAttribute(WebConstants.REQUEST_SUBMIT_TOKEN);
        		logger.debug("Pass the validation!");
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        DuplicateSubmitValidate validateAnnotation = method.getAnnotation(DuplicateSubmitValidate.class);
        if (validateAnnotation != null) {
        	boolean needSave = validateAnnotation.saveToken();
        	if (needSave) {
        		String token = TokenHelper.getInstance().generateToken(request);
				request.getSession(false).setAttribute(WebConstants.REQUEST_SUBMIT_TOKEN, token);
				logger.debug("New token:[{}] has been saved in session with key:[{}]!", token, WebConstants.REQUEST_SUBMIT_TOKEN);
			}
		}
	}

	protected boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession().getAttribute(WebConstants.REQUEST_SUBMIT_TOKEN);
    	if (serverToken == null) {
			return true;
		}
    	String clientToken = request.getParameter(tokenName);
    	if (clientToken == null) {
    		logger.debug("In request's parameters have no one named {}", tokenName);
    		clientToken = request.getHeader(tokenName);
    		if (clientToken == null) {
    			return true;
			}
		}
    	logger.debug("Value of server token: {}", serverToken);
    	logger.debug("Value of client token: {}", clientToken);
    	if (!serverToken.equals(clientToken)) {
			return true;
		}
    	return false;
	}
	
	
	/**
	 * @param tokenName the tokenName to set
	 */
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	
}
