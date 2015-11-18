/**
 * 项目名: java-tutorials-tools
 * 包名:  net.fantesy84.util
 * 文件名: CollectionUtils.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月5日
 */
package net.fantesy84.common.util;

import java.util.Collection;

/**
 * @author Andronicus
 * @since 2015年11月5日
 */
public abstract class CollectionUtils {
	
	public static boolean isNullOrEmptyCollection(Collection<?> collection){
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}
	
}
