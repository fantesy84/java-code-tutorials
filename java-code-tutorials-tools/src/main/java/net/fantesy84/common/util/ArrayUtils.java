/**
 * Project java-code-tutorials-tools
 * File: ArrayUtils.java
 * CreateTime: 2015年11月8日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util;

/**
 * TypeName: ArrayUtils
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月8日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class ArrayUtils {
	private ArrayUtils(){
		
	}
	
	public static String array2String(Object[] array){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(",").append(array[i].toString());
		}
		return buffer.toString().replaceFirst(",", "");
	}
}
