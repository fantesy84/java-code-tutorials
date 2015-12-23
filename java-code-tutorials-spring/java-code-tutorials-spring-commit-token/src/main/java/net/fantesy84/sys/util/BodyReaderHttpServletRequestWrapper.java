/**
 * Project java-code-tutorials-spring-commit-token
 * File: BodyReaderHttpServletRequestWrapper.java
 * CreateTime: 2015年12月24日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.sys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * TypeName: BodyReaderHttpServletRequestWrapper
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月24日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private ServletInputStream bodyStream;
	/**
	 * @param request
	 */
	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			bodyStream = request.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getInputStream()
	 */
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return bodyStream;
	}
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getReader()
	 */
	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(bodyStream));
	}

}
