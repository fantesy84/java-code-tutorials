/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util
 * 文件名: RegularExpressions.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月17日
 */
package net.fantesy84.common.util;

/**
 * @author Andronicus
 * @since 2015年11月17日
 */
public interface RegularExpressions {
	String IP_V4_REGEX = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}";
	String EMAIL_REGEX_V1 = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	String EMAIL_REGEX_V2 = "^\\w+@\\w+(\\.\\w+)+(\\,\\w+@\\w+(\\.\\w+)+)*$";
	String EMAIL_REGEX_V3 = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	String CELLPHONE_NUMBER_REGEX = "1((3[0-9]{1})|(4[5,7]{1})|(5[0-3,5-9]{1})|(7[0,5-8]{1})|(8[0-9]{1}))([0-9]{4,4})([0-9]{4,4})";
	String EMPTY_LINE_REGEX = "\\n[\\s| ]*\\r";
	String CONTAIN_CHINESE_REGEX = "[\u4e00-\u9fa5]";
	String ONLY_CHINESE_REGEX = "^[\u4e00-\u9fa5]{0,}$";
	String HTTP_OR_HTTPS_URI_REGEX = "^https?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
	/**
	 * 可以匹配的KEY形式为:<code>${xxx.xxx}</code>
	 */
	String FILE_INSTEAD_KEYS_REGEX = "\\$\\{{1,1}[a-zA-Z_]+[a-zA-Z0-9_\\.]*\\}{1,1}";
}
