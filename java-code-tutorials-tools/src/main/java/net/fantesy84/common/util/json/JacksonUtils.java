/**
 * File: JsonUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TypeName: JsonUtils
 * <p>
 * 实现或继承{@link FilterMixIn}接口可针对不同的场景定制化JSON
 * </p>
 * 首先确定在固定情景下要个性化的方式,可以使用Jackson的各种注解
 * <blockquote>
 * <pre class="code">
 *         <tt>@</tt>JsonFilter("testDTO")
 *         public interface MyFilterMixIn extends FilterMixIn {
 *             //序列化时忽略id属性
 *             <tt>@</tt>JsonIgnore Long getId();
 *             //序列化时将属性userName的key映射为name
 *             <tt>@</tt>JsonProperty(value="name") String getUserName();
 *         }
 * </pre>
 * </blockquote>
 * 个性化接口创建好后,即可使用toJson方法进行个性化转换,例如:
 * <blockquote>
 * <pre>{@code
 *         String json = JSONUtils.getInstance().toJson(obj, new SimpleDateFormat("yyyy-MM-dd"), true, false, MyFilterMixIn.class, new String[]{"xxx","xxx"});
 *     }</pre>
 * </blockquote>
 * <p>CreateTime: 2015年10月17日</p>
 * <p>
 * UpdateTime: 2015年10月18日<br/>
 * 增加toJsonExcludeProperty,toJsonIncludeProperty方法以丰富多种个性化需求.<br/>
 * </p>
 * <p>
 * UpdateTime: 2018年3月21日<br/>
 * 增加toList,getValues,toMap三个方法.<br/>
 * </p>
 * <p>
 * UpdateTime: 2018年8月29日<br/>
 * 新增toJsonExcludeProperty,toJsonIncludeProperty重载方法.<br/>
 * </p>
 *
 * @author junjie.ge
 * @see #toJsonExcludeProperty(Object, Class, String...)
 * @see #toJsonExcludeProperty(Object, String...)
 * @see #toJsonIncludeProperty(Object, Class, String...)
 * @see #toJsonIncludeProperty(Object, String...)
 */
public final class JacksonUtils {
    private static final Logger LOGGER = Logger.getLogger(JacksonUtils.class.getName());
    private final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String DEFAULT_TIMEZONE = "GMT+8:00";
    private final static DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
    private static JacksonUtils obj;
    @Getter
    private final ObjectMapper mapper;
    @Setter
    private FilterProvider filterProvider;
    private String timeZone = DEFAULT_TIMEZONE;

    private JacksonUtils() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static JacksonUtils getInstance() {
        if (obj == null) {
            synchronized (JacksonUtils.class) {
                if (obj == null) {
                    obj = new JacksonUtils();
                }
            }
        }
        return obj;
    }

    private static void exceptionHandler(Exception e) {
        IllegalStateException ex = new IllegalStateException(e.getMessage(), e);
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        throw ex;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private JavaType getListType(Class<?> elementClasses) {
        return mapper.getTypeFactory().constructParametricType(List.class, elementClasses);
    }

    /**
     * 将JAVA对象序列化为JSON字符串.此为原始方法
     * <P>本方法可设置时间格式,<code>null</code>值过滤开关,包含/排除开关,包含/排除的属性数组
     *
     * @param value       被序列化的对象
     * @param dateFormat  时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
     * @param nullskip    是否跳过<code>null</code>值属性,默认为不跳过
     * @param isInclude   包含/排除开关,默认为排除
     * @param mixInClass  MixIn接口(子接口,实现类)Class对象
     * @param filteFields 被包含/排除的属性名称数组
     * @return JSON字符串
     */
    public String toJson(Object value, DateFormat dateFormat, boolean nullskip, boolean isInclude, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
        if (nullskip) {
            mapper.setSerializationInclusion(Include.NON_NULL);
            LOGGER.log(Level.FINEST, "JSON - 序列化时跳过空值属性");
        }
        if (dateFormat != null) {
            if (timeZone != null) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                LOGGER.log(Level.FINEST, "JSON-设置时区为:" + timeZone);
            }
            if (dateFormat instanceof SimpleDateFormat) {
                SimpleDateFormat f = (SimpleDateFormat) dateFormat;
                LOGGER.log(Level.FINEST, "JSON-设置时间格式为:" + f.toPattern());
            } else if (dateFormat instanceof StdDateFormat) {
                LOGGER.log(Level.FINEST, "JSON-设置时间格式为:" + "yyyy-MM-ddThh:mm:ssZ");
            }
            mapper.setDateFormat(dateFormat);
        }
        /*ObjectWriter writer = mapper.writer();*/
        if (filteFields != null && filteFields.length > 0) {
            String filterId = mixInClass.getAnnotation(JsonFilter.class).value();
            FilterProvider filters;
            if (isInclude) {
                filters = new SimpleFilterProvider().addFilter(filterId,
                        SimpleBeanPropertyFilter.filterOutAllExcept(filteFields));
                LOGGER.log(Level.FINEST, "JSON-设置属性过滤规则:仅包含!" + Arrays.toString(filteFields));
            } else {
                filters = new SimpleFilterProvider().addFilter(filterId,
                        SimpleBeanPropertyFilter.serializeAllExcept(filteFields));
                LOGGER.log(Level.FINEST, "JSON-设置属性过滤规则:仅排除!" + Arrays.toString(filteFields));
            }
            /*writer.with(filters);*/
            mapper.setFilterProvider(filters);
        }
        if (mixInClass != null) {
            String filterId = mixInClass.getAnnotation(JsonFilter.class).value();
            filterProvider = new SimpleFilterProvider().addFilter(filterId, SimpleBeanPropertyFilter.serializeAll());
            /*writer.with(new SimpleFilterProvider().addFilter(filterId, SimpleBeanPropertyFilter.serializeAll()));*/
            mapper.setFilterProvider(filterProvider);
            mapper.addMixIn(value.getClass(), mixInClass);
        }
        String result = null;
        try {
            /*result = writer.writeValueAsString(value);*/
            result = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            exceptionHandler(e);
        }
        return result;
    }


    public String toJsonExcludeProperty(Object value, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
        return toJson(value, DEFAULT_DATEFORMAT, false, false, mixInClass, filteFields);
    }

    public String toJsonExcludeProperty(Object value, String... filteFields) {
        return toJsonExcludeProperty(value, null, filteFields);
    }

    public String toJsonIncludeProperty(Object value, Class<? extends FilterMixIn> mixInClass, String... filteFields) {
        return toJson(value, DEFAULT_DATEFORMAT, false, true, mixInClass, filteFields);
    }

    public String toJsonIncludeProperty(Object value, String... filterFields) {
        return toJsonIncludeProperty(value, null, filterFields);
    }

    public String toJson(Object value, Class<? extends FilterMixIn> mixInClass) {
        return toJson(value, DEFAULT_DATEFORMAT, false, false, mixInClass);
    }

    /**
     * 将JAVA对象序列化为JSON字符串,重载方法1
     *
     * @param value      被序列化的对象
     * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
     * @param nullskip   是否跳过<code>null</code>值属性,默认为不跳过
     * @return JSON字符串
     */
    public String toJson(Object value, DateFormat dateFormat, boolean nullskip) {
        return toJson(value, dateFormat, nullskip, false, null);
    }

    /**
     * 将JAVA对象序列化为JSON字符串,重载方法2
     *
     * @param value      被序列化的对象
     * @param dateFormat 时间格式,默认格式为<tt>yyyy-MM-dd HH:mm:ss,SSS</tt>,默认时区为GMT+8
     * @return JSON字符串
     */
    public String toJson(Object value, DateFormat dateFormat) {
        return toJson(value, dateFormat, false);
    }

    /**
     * 将JAVA对象序列化为JSON字符串,重载方法3
     *
     * @param value 被序列化的对象
     * @return JSON字符串
     */
    public String toJson(Object value) {
        return toJson(value, DEFAULT_DATEFORMAT);
    }

    /**
     * 将JSON字符串反序列化为JAVA对象
     *
     * @param json       JSON字符串
     * @param javaType   JAVA对象类型
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
        T t;
        try {
            t = mapper.readValue(json, javaType);
            return t;
        } catch (IOException e) {
            exceptionHandler(e);
        }
        return null;
    }

    /**
     * 将JSON字符串反序列化为JAVA对象
     *
     * @param json     JSON字符串
     * @param javaType JAVA对象类型
     * @return 指定类型的JAVA对象
     */
    public <T> T toBean(String json, Class<T> javaType) {
        return toBean(json, javaType, DEFAULT_DATEFORMAT);
    }

    /**
     * 将JSON字符串反序列化为集合
     *
     * @param json    JSON字符串
     * @param eleType 集合的元素类型
     * @param <E>     实现了序列化接口的Java类
     * @return {@link List}集合
     */
    public <E> List<E> toList(String json, Class<E> eleType) {
        List<E> list = null;
        try {
            list = mapper.readValue(json, getListType(eleType));
        } catch (Exception e) {
            exceptionHandler(e);
        }
        return list;
    }

    /**
     * 获取JSON字符串中指定key对应的value
     *
     * @param json json字符串
     * @param key  想查找的关键字.可能存在多个重复的关键字
     * @return 指定关键字对应的值.如果该关键字在json串中有多个, 将返回多个值
     */
    public List<String> getValues(String json, String key) {
        List<String> values = null;
        try {
            JsonNode root = mapper.readTree(json);
            values = root.findValuesAsText(key);
        } catch (IOException e) {
            exceptionHandler(e);
        }
        return values;
    }

    /**
     * 将JSON字符串反序列化为Map
     *
     * @param json json字符串
     * @return JSON字符串对应的映射.在出现异常时, 将返回null
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> toMap(String json) {
        Map<String, String> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (Exception e) {
            exceptionHandler(e);
        }
        return map;
    }


}
