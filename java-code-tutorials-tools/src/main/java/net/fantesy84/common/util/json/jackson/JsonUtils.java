/**
 * Project gb-platform-sys-domain
 * File: JsonUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util.json.jackson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import net.fantesy84.common.util.ArrayUtils;

/**
 * TypeName: JsonUtils
 * <P>
 * 实现或继承<code>net.fantesy84.common.util.FilterMixIn</code>接口可针对不同的场景定制化JSON,使用本工具类的toJsonExcludeProperty或toJsonIncludeProperty方法可动态过滤对象属性.</br>
 * 例如:
 * <blockquote>
 * <code>
 * @JsonFilter("testDTO")</br>
 * public interface MyFilterMixIn extends FilterMixIn {</br>
 *		<code>&nbsp;&nbsp;&nbsp;&nbsp;@JsonIgnore Long getId();<code></br>
 *		<code>&nbsp;&nbsp;&nbsp;&nbsp;@JsonProperty(value="name") String getUserName();<code></br>
 * }</br>
 * 然后调用toJson(sub, new SimpleDateFormat("yyyy-MM-dd"), true, false, MyFilterMixIn.class, new String[]{"xxx","xxx"})方法动态过滤属性
 * </code>
 * </blockquote>
 * <P>
 * CreateTime: 2015年10月17日
 * <P>
 * UpdateTime: 2015年10月18日</br>
 * 增加2个方法以方便过滤属性和使用MixIn模板接口:</br>
 * 1.toJsonExcludeProperty</br>
 * 2.toJsonIncludeProperty</br>
 * @author junjie.ge
 *
 */
public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class.getName());
	private static ObjectMapper mapper;
	private static JsonUtils obj = new JsonUtils();
	private final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";
	private final static DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	private final static String DEFAULT_TIMEZONE = "GMT+8:00";
	private String timeZone = DEFAULT_TIMEZONE;
	
	private JsonUtils(){
		mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public static JsonUtils getInstance() {
		synchronized (obj) {
			if (obj == null) {
				return new JsonUtils();
			}
		}
		return obj;
	}
	/**
	 * 将JAVA对象序列化为JSON字符串.此为原始方法
	 * <P>本方法可设置时间格式,<code>null</code>值过滤开关,包含/排除开关,包含/排除的属性数组
	 * @param value 被序列化的对象
	 * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
	 * @param nullskip 是否跳过<code>null</code>值属性,默认为不跳过
	 * @param isInclude 包含/排除开关,默认为排除
	 * @param mixInClass MixIn接口(子接口,实现类)Class对象
	 * @param filteFields 被包含/排除的属性名称数组
	 * @return JSON字符串
	 */
	public String toJson(Object value, DateFormat dateFormat, boolean nullskip, boolean isInclude, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
		if (nullskip) {
			mapper.setSerializationInclusion(Include.NON_NULL);
			logger.debug("JSON - 序列化时跳过空值属性");
		}
		if (dateFormat != null) {
			if (timeZone != null) {
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
				logger.debug("JSON-设置时区为:{}", timeZone);
			}
			mapper.setDateFormat(dateFormat);
			if (dateFormat instanceof SimpleDateFormat) {
				SimpleDateFormat f = (SimpleDateFormat) dateFormat;
				logger.debug("JSON-设置时间格式为:{}", f.toPattern());
			} else if (dateFormat instanceof ISO8601DateFormat) {
				logger.debug("JSON-设置时间格式为:{}", "yyyy-MM-ddThh:mm:ssZ");
			}
		}
		if (filteFields != null && filteFields.length > 0) {
			String filterId = mixInClass.getAnnotation(JsonFilter.class).value();
			FilterProvider filters = null;
			if (isInclude) {
				filters = new SimpleFilterProvider().addFilter(filterId,
						SimpleBeanPropertyFilter.filterOutAllExcept(filteFields));
				logger.debug("JSON-设置属性过滤规则:仅包含![{}]", ArrayUtils.array2String(filteFields));
			} else {
				filters = new SimpleFilterProvider().addFilter(filterId,
						SimpleBeanPropertyFilter.serializeAllExcept(filteFields));
				logger.debug("JSON-设置属性过滤规则:仅排除![{}]", ArrayUtils.array2String(filteFields));
			}
			mapper.setFilterProvider(filters);
		}
		if (mixInClass != null) {
			mapper.addMixIn(value.getClass(), mixInClass);
		}
		String result = null;
		try {
			result = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	
	public String toJsonExcludeProperty(Object value, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
		return toJson(value, DEFAULT_DATEFORMAT, false, false, mixInClass, filteFields);
	}
	
	
	public String toJsonIncludeProperty(Object value, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
		return toJson(value, DEFAULT_DATEFORMAT, false, true, mixInClass, filteFields);
	}
	
	
	public String toJson(Object value, Class<? extends FilterMixIn> mixInClass) {
		return toJson(value, DEFAULT_DATEFORMAT, false, false, mixInClass, new String[]{});
	}
	
	/**
	 * 将JAVA对象序列化为JSON字符串,重载方法1
	 * @param value 被序列化的对象
	 * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
	 * @param nullskip 是否跳过<code>null</code>值属性,默认为不跳过
	 * @return JSON字符串
	 * @see net.fantesy84.common.util.json.jackson.JsonUtils#toJson(Object, DateFormat, boolean, boolean, Class, String...)
	 */
	public String toJson(Object value, DateFormat dateFormat, boolean nullskip) {
		return toJson(value, dateFormat, nullskip, false, null, new String[]{});
	}

	/**
	 * 将JAVA对象序列化为JSON字符串,重载方法2
	 * @param value 被序列化的对象
	 * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
	 * @return JSON字符串
	 * @see net.fantesy84.common.util.json.jackson.JsonUtils#toJson(Object, DateFormat, boolean)
	 */
	public String toJson(Object value, DateFormat dateFormat) {
		return toJson(value, dateFormat, false);
	}
	
	/**
	 * 将JAVA对象序列化为JSON字符串,重载方法3
	 * @param value 被序列化的对象
	 * @return JSON字符串
	 * @see net.fantesy84.common.util.json.jackson.JsonUtils#toJson(Object, DateFormat)
	 */
	public String toJson(Object value) {
		return toJson(value, DEFAULT_DATEFORMAT);
	}

	/**
	 * 将JSON字符串反序列化为JAVA对象
	 * @param json JSON字符串
	 * @param javaType JAVA对象类型
	 * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
	 * @return 指定类型的JAVA对象
	 */
	public <T> T toBean(String json, Class<T> javaType, DateFormat dateFormat) {
		if (dateFormat != null) {
			if (timeZone != null) {
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
			}
			mapper.setDateFormat(dateFormat);
		}
		T t = null;
		try {
			t = mapper.readValue(json, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 将JSON字符串反序列化为JAVA对象
	 * @param json JSON字符串
	 * @param javaType JAVA对象类型
	 * @return 指定类型的JAVA对象
	 * @see net.fantesy84.common.util.json.jackson.JsonUtils#toBean(String, Class, DateFormat)
	 */
	public <T> T toBean(String json, Class<T> javaType) {
		return toBean(json, javaType, DEFAULT_DATEFORMAT);
	}
}
