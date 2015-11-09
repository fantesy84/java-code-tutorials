/**
 * 项目名: java-tutorials-tools
 * 包名:  net.fantesy84.util
 * 文件名: CollectionUtils.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月5日
 */
package net.fantesy84.common.util;

/**
 * @author Andronicus
 * @since 2015年11月5日
 */
public class CollectionUtils {
	public static boolean notNullAndEmptyArray(Object[] args){
		boolean result = false;
		if (args != null && args.length > 0) {
			result = true;
		}
		return result;
	}
}
