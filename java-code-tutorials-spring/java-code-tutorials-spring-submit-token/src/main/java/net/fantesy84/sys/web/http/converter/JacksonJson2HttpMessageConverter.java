/**
 * 项目名: java-code-tutorials-spring-submit-token
 * 包名:  net.fantesy84.web.http.converter
 * 文件名: JacksonJson2HttpMessageConverter.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月25日
 */
package net.fantesy84.sys.web.http.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author Andronicus
 * @since 2015年12月25日
 */
public class JacksonJson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter#writeInternal(java.lang.Object, java.lang.reflect.Type, org.springframework.http.HttpOutputMessage)
	 */
	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
//		String token = (String) RequestContextHolder.getRequestAttributes().getAttribute(WebConstants.REQUEST_SUBMIT_TOKEN, RequestAttributes.SCOPE_SESSION);
//		if (object instanceof BaseTokenDTO) {
//			((BaseTokenDTO) object).setToken(token);
//			Method tokenWriter = null;
//			try {
//				tokenWriter = object.getClass().getDeclaredMethod("setToken", String.class);
//				tokenWriter.invoke(object, token);
//			} catch (Exception e) {
//				throw new IllegalStateException(e);
//			}
//		}
		outputMessage.getHeaders().set("token", "");
		super.writeInternal(object, type, outputMessage);
	}
	
}
