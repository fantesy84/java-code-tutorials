/**
 * Project java-code-tutorials-spring-commit-token
 * File: WebRequestTokenInterceptor.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.sys.web.interceptor;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
	private static final int SESSION_SCOPE = RequestAttributes.SCOPE_SESSION;
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
        	boolean reset = validateAnnotation.reset();
        	if (reset) {
        		Boolean resetMarker = (Boolean) RequestContextHolder.getRequestAttributes().getAttribute(WebConstants.RESET_SUBMIT_TOKEN, SESSION_SCOPE);
        		if (resetMarker.booleanValue()) {
        			logger.info(">>>>>>>> Submit token has been reset! As same the first submit! <<<<<<<<");
        			RequestContextHolder.getRequestAttributes().setAttribute(WebConstants.RESET_SUBMIT_TOKEN, Boolean.FALSE, SESSION_SCOPE);
        			return true;
        		}
			}
        	logger.info(">>>>>>>> This method: [{}] will validate token! <<<<<<<<", method.getName());
        	boolean avoidDuplicateSubmit = validateAnnotation.avoidDuplicateSubmit();
        	if (avoidDuplicateSubmit) {
        		if (isRepeatSubmit(request)) {
        			logger.warn("******** Please don't repeat submit! URI:[{}] ********", request.getServletPath());
        			return false;
        		} else {
        			RequestContextHolder.getRequestAttributes().removeAttribute(WebConstants.REQUEST_SUBMIT_TOKEN, SESSION_SCOPE);
        			
        			logger.debug(">>>>>>>> Remove the current token complete! Avoid duplicate submit is working. <<<<<<<<");
        		}
			}
        	logger.info(">>>>>>>> Pass the validation! <<<<<<<<");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null) {
			super.afterCompletion(request, response, handler, ex);
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        DuplicateSubmitValidate validateAnnotation = method.getAnnotation(DuplicateSubmitValidate.class);
        if (validateAnnotation != null) {
        	boolean reset = validateAnnotation.reset();
        	if (reset) {
        		long resetTime = validateAnnotation.resetTime();
        		if (resetTime > 0) {
        			logger.info(">>>>>>>> Duplicate submit validate will disable {} ms after. <<<<<<<<", resetTime);
        			final HttpSession session = request.getSession();
        			Timer t = new Timer(tokenName, true);
        			TimerTask task = new TimerTask() {
        				@Override
        				public void run() {
        					session.setAttribute(WebConstants.RESET_SUBMIT_TOKEN, Boolean.TRUE);
        					logger.debug(">>>>>>>> Duplicate submit validation token has been removed from session! Validate reset! <<<<<<<<");
        				}
        			};
        			t.schedule(task, resetTime);
				}
			}
		}
	}

	protected boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) RequestContextHolder.getRequestAttributes().getAttribute(WebConstants.REQUEST_SUBMIT_TOKEN, RequestAttributes.SCOPE_SESSION);
		logger.debug(">>>>>>>> Value of server token: {} <<<<<<<<", serverToken);
    	if (serverToken == null) {
    		logger.info(">>>>>>>> Server side token is null! It's duplicate submit. <<<<<<<<");
			return true;
		}
    	String clientToken = request.getParameter(tokenName);
    	if (clientToken == null) {
    		logger.debug(">>>>>>>> Could not find parameter's key [{}] in request. <<<<<<<<", tokenName);
    		clientToken = request.getHeader(tokenName);
		}
    	logger.debug(">>>>>>>> Value of client token: {} <<<<<<<<", clientToken);
    	if (clientToken == null) {
    		logger.info(">>>>>>>> Client side token is null! It's duplicate submit. <<<<<<<<");
    		return true;
    	}
    	if (!serverToken.equals(clientToken)) {
    		logger.info(">>>>>>>> Server side token is not equals client token! It's duplicate submit. <<<<<<<<");
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
