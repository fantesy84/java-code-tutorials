/**
 * Project gb-platform-sys-domain
 * File: DateUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.common.util.StringUtils;

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
	private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	public static final String FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String SHORT = "yyyyMMdd";
	public static final String DATE = "yyyy-MM-dd";
	public static final String TIME = "HH:mm:ss";
	private static final int CURRENT = 0;
	private static final int AGO = -1;
	private static final int FEATRUE = 1;
	
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
	public DateFormat getDateFormat(String pattern){
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
	public DateFormat getDefaultDateFormat(){
		return getDateFormat(null);
	}
	/**
	 * 将指定日期字符串转换为指定格式的{@link java.util.Date}对象
	 * @param src 日期字符串
	 * @param pattern 日期格式(本工具类提供了FULL,SHORT,DATE,TIME 4个静态常量日期格式)
	 * @return {@link java.util.Date}对象
	 */
	public Date str2date(String src, String pattern) {
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
	public Date str2date(String src) {
		return str2date(src, null);
	}
	
	/**
	 * 将指定的{@link java.util.Date}对象转换为指定日期格式的日期字符串
	 * @param date {@link java.util.Date}对象
	 * @param pattern 日期格式(本工具类提供了FULL,SHORT,DATE,TIME 4个静态常量日期格式)
	 * @return 日期字符串
	 */
	public String date2str(Date date, String pattern) {
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
	public String date2str(Date date) {
		return date2str(date, null);
	}
	
	public String getAgeByBirthday(Date birthday) {
		Date now = new Date();
		long day=(now.getTime()-birthday.getTime())/(24*60*60*1000) + 1;
		String year=new java.text.DecimalFormat("#.00").format(day/365f);
		return year;
	}
	
	public int compareWithTwoDate(Date specifiedTime, Date time) {
		if (time == null) {
			throw new IllegalArgumentException("Parameter 'time' could not be null!");
		}
		long[] points = null;
		if (specifiedTime != null) {
			points = getPointOfDate(specifiedTime);
		} else {
			points = getPointOfDate();
		}
		if (time.getTime() >= points[0] && time.getTime() <= points[1]) {
			return CURRENT;
		} else if (time.getTime() < points[0]) {
			return AGO;
		} else {
			return FEATRUE;
		}
	}
	
	public boolean isToday(Date time) {
		if (compareWithTwoDate(null, time) == CURRENT) {
			return true;
		}
		return false;
	}
	
	public boolean isBefore(Date time) {
		if (compareWithTwoDate(null, time) == AGO) {
			return true;
		}
		return false;
	}
	
	public boolean isFeatrue(Date time) {
		if (compareWithTwoDate(null, time) == FEATRUE) {
			return true;
		}
		return false;
	}
	
	private long[] getPointOfDate() {
		return getPointOfDate(null);
	}
	
	private long[] getPointOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long beginPoint = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		long endPoint = c.getTimeInMillis();
		return new long[]{beginPoint, endPoint};
	}
	
	/**
	 * 获取当周第一天和最后一天
	 * 
	 * @author rxg
	 * @param day
	 * @return
	 */
	public Date[] getWeekStartAndEndDate(Date day) {
		int mondayPlus = 0;
		Calendar cd = Calendar.getInstance();
		cd.setTime(day);
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) {
			mondayPlus = 0;
		} else if(dayOfWeek == 0){
			mondayPlus = -6;
		}else {
			mondayPlus = 1 - dayOfWeek;
		}
		cd.add(GregorianCalendar.DATE, mondayPlus);
		Date firstDay = cd.getTime();// 本周第一天
		cd.add(Calendar.DAY_OF_WEEK, 6);
		Date lastDay = cd.getTime();// 本周最后一天
		Date[] dates = new Date[2];
		dates[0]=firstDay;
		dates[1]=lastDay;
		return dates;
	}
	/**
	 * 获取当月第一天和最后一天
	 * 
	 * @author rxg
	 * @param day
	 * @return
	 */
	public Date[] getMonthStartAndEndDate(Date day) {
		 //获取当前月第一天：
        Calendar c = Calendar.getInstance(); 
        c.setTime(day);
        c.set(Calendar.DAY_OF_MONTH, c     
                .getActualMinimum(Calendar.DAY_OF_MONTH));  
        Date firstDay = c.getTime();
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();  
        ca.setTime(day);
        ca.set(Calendar.DAY_OF_MONTH, ca     
                .getActualMaximum(Calendar.DAY_OF_MONTH));     
        Date lastDay = ca.getTime();
		Date[] dates = new Date[2];
		dates[0]=firstDay;
		dates[1]=lastDay;
		return dates;
	}
	
	public String[] getDayStartAndEndTimePointStr(Date day){
		String[] dayStrs = new String[2];
		String dayStr = formatDateToStr(day);
		String startTimePoint = new StringBuffer().append(dayStr).append(" ").append("00:00:00").toString();
		String endTimePoint = new StringBuffer().append(dayStr).append(" ").append("23:59:59").toString();
		dayStrs[0] = startTimePoint;
		dayStrs[1] = endTimePoint;
		return dayStrs;
	}
	
	public String formatDateToStr(Date day){
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(day);
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}
	
	public Date dateAddOrSubtract(Date day,int size){
		 Calendar calendar = new GregorianCalendar(); 
	     calendar.setTime(day); 
	     calendar.add(Calendar.DATE,size);
	     return calendar.getTime();
	}
	
	public List<Date> getPeriodOfTime(Date startDay,Date endDay){
		List<Date> dayList = new ArrayList<Date>();
		dayList.add(startDay);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间  
        calBegin.setTime(startDay);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间  
        calEnd.setTime(endDay);
        // 测试此日期是否在指定日期之后  
        while (endDay.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            dayList.add(calBegin.getTime());
        }
        return dayList;
	}
	
	public Date secondsBeforeNow(int seconds) {
		if (!(seconds >= 0 && seconds < 60)) {
			logger.warn("秒数不推荐为负数或者超过60秒,负数请使用'secondsAfterNow',超过60秒请使用'minutesBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND) - seconds);
		return calendar.getTime();
	}
	
	public Date secondsAfterNow(int seconds) {
		if (!(seconds >= 0 && seconds < 60)) {
			logger.warn("秒数不推荐为负数或者超过60秒,负数请使用'secondsBeforeNow',超过60秒请使用'minutesAfterNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND) + seconds);
		return calendar.getTime();
	}
	
	public Date minutesBeforeNow(int min){
		if (!(min >= 0 && min < 1440)) {
			logger.warn("分钟数不推荐为负数或者超过1天,负数请使用'minuteAfterNow',超过1天请使用'daysBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE) - min);
		return calendar.getTime();
	}
	
	public Date minutesAfterNow(int min){
		if (!(min >= 0 && min < 1440)) {
			logger.warn("分钟数不推荐为负数或者超过1天,负数请使用'minuteBeforeNow',超过1天请使用'daysBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE) + min);
		return calendar.getTime();
	}
	
	public Date hoursBeforeNow(int hour){
		if (!(hour >= 0 && hour < 24)) {
			logger.warn("小时数不推荐为负数或者超过1天,负数请使用'hoursAfterNow',超过1天请使用'daysBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) - hour);
		return calendar.getTime();
	}
	
	public Date hoursAfterNow(int hour){
		if (!(hour >= 0 && hour < 24)) {
			logger.warn("小时数不推荐为负数或者超过1天,负数请使用'hoursBeforeNow',超过1天请使用'daysBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) + hour);
		return calendar.getTime();
	}
	
	public Date daysBeforeNow(int day){
		if (!(day >= 0 && day < 365)) {
			logger.warn("天数不推荐为负数或者超过1年,负数请使用'daysAfterNow',超过1年请使用'yearsBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) - day);
		return calendar.getTime();
	}
	
	public Date daysAfterNow(int day){
		if (!(day >= 0 && day < 365)) {
			logger.warn("天数不推荐为负数或者超过1年,负数请使用'daysBeforeNow',超过1年请使用'yearsBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) + day);
		return calendar.getTime();
	}
	
	public Date yearsBeforeNow(int year){
		Calendar calendar = Calendar.getInstance();
		if (!(year >= 0 && calendar.get(Calendar.YEAR) - year > 1970)) {
			logger.warn("年数不推荐为负数或减去相应年数后小于1970年!负数请使用'yearsAfterNow'方法");
		}
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) - year);
		return calendar.getTime();
	}
	
	public Date yearsAfterNow(int year){
		if (!(year >= 0)) {
			logger.warn("年数不推荐为负数!负数请使用'yearsBeforeNow'方法");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) + year);
		return calendar.getTime();
	}
	
	public XMLGregorianCalendar converte2XmlDate(Date date){
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DatatypeFactory dtf = null;
        try {
            dtf = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
        	throw new IllegalStateException(e);
        }
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
        dateType.setYear(cal.get(Calendar.YEAR));
        // 由于Calendar.MONTH取值范围为0~11,需要加1
        dateType.setMonth(cal.get(Calendar.MONTH) + 1);
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
        dateType.setMinute(cal.get(Calendar.MINUTE));
        dateType.setSecond(cal.get(Calendar.SECOND));
        return dateType;
	}
}
