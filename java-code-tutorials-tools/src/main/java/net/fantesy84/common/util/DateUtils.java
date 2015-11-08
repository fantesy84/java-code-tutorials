/**
 * Project gb-platform-sys-domain
 * File: DateUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TypeName: DateUtils
 * <P>TODO
 * 
 * <P>CreateTime: 2015年10月17日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class DateUtils {
	public static final String FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String SHORT = "yyyyMMdd";
	public static final String DATE = "yyyy-MM-dd";
	public static final String TIME = "HH:mm:ss";
	
	private static SimpleDateFormat format;
	private static volatile DateUtils instance;
	
	private DateUtils(){
		format = new SimpleDateFormat(FULL);
	}
	
	public static DateUtils getInstance(){
		if (instance == null) {
			synchronized (DateUtils.class) {
				if (instance == null) {
					instance = new DateUtils();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 获取指定日期格式的日期格式化对象
	 * @param pattern
	 * @return
	 */
	public static DateFormat getDateFormat(String pattern){
		if (format == null) {
			format = new SimpleDateFormat();
		}
		if (pattern != null && !pattern.isEmpty()) {
			format.applyPattern(pattern);
		}
		return format;
	}
	
	/**
	 * 获取默认<tt>yyyy-MM-dd HH:mm:ss</tt>(DateUtils.FULL)日期格式化对象
	 * @return {@link java.text.DateFormat}对象
	 */
	public static DateFormat getDefaultDateFormat(){
		return getDateFormat(null);
	}
	/**
	 * 将指定日期字符串转换为指定格式的{@link java.util.Date}对象
	 * @param src 日期字符串
	 * @param pattern 日期格式(本工具类提供了FULL,SHORT,DATE,TIME 4个静态常量日期格式)
	 * @return {@link java.util.Date}对象
	 */
	public static Date str2date(String src, String pattern) {
		if (StringUtils.isNullOrEmpty(src)) {
			throw new RuntimeException("用于转换的日期字符串不能为空!");
		}
		getDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(src);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将指定日期字符串转换为默认格式<tt>yyyy-MM-dd HH:mm:ss</tt>(DateUtils.FULL)的{@link java.util.Date}对象
	 * @param src 日期字符串
	 * @return 指定的日期字符串对应的{@link java.util.Date}对象
	 */
	public static Date str2date(String src) {
		return str2date(src, null);
	}
	
	/**
	 * 将指定的{@link java.util.Date}对象转换为指定日期格式的日期字符串
	 * @param date {@link java.util.Date}对象
	 * @param pattern 日期格式(本工具类提供了FULL,SHORT,DATE,TIME 4个静态常量日期格式)
	 * @return 日期字符串
	 */
	public static String date2str(Date date, String pattern) {
		if (date == null) {
			throw new RuntimeException("用于转换的日期不能为空!");
		}
		getDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 将指定的{@link java.util.Date}对象转换为默认日期格式<tt>yyyy-MM-dd HH:mm:ss</tt>(DateUtils.FULL)的日期字符串
	 * @param date {@link java.util.Date}对象
	 * @return 日期字符串
	 */
	public static String date2str(Date date) {
		return date2str(date, null);
	}
}
