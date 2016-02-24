package net.fantesy84.common.util;

import java.io.InputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	private JsonUtils(){}
	
	public static String toJson(Object obj) throws JsonProcessingException {
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper.writeValueAsString(obj);
	}
	
	public static String toJsonWithDatePattern(Object obj, String pattern) throws JsonProcessingException {
		mapper.setDateFormat(new SimpleDateFormat(pattern));
		return mapper.writeValueAsString(obj);
	}
	
	public static <T> T toBean(String json, Class<T> valueType) throws Exception {
		T bean = null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		bean = mapper.readValue(json, valueType);
		return bean;
	}
	
	public static <T> T toBean(InputStream in, Class<T> valueType) throws Exception {
		T bean = null;
		bean = mapper.readValue(in, valueType);
		return bean;
	}
	
	public static <T> T toBean(Reader reader, Class<T> valueType) throws Exception {
		T bean = null;
		bean = mapper.readValue(reader, valueType);
		return bean;
	}
}
