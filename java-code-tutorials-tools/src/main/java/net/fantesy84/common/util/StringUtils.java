/**
 * Project gb-platform-sys-domain
 * File: StringUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util;

/**
 * TypeName: StringUtils
 * <P>TODO
 * 
 * <P>CreateTime: 2015年10月17日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class StringUtils {
	public static boolean isNullOrEmpty(String src){
		boolean isNullOrEmpty = false;
		if (src == null || src.isEmpty()) {
			isNullOrEmpty = true;
		}
		return isNullOrEmpty;
	}
}
