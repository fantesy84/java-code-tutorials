/**
 * Project java-code-tutorials-tools
 * File: ArrayUtils.java
 * CreateTime: 2015年11月8日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeName: ArrayUtils
 * <P>TODO
 *
 * <P>CreateTime: 2015年11月8日
 * <P>UpdateTime:
 * @author junjie.ge
 *
 */
public abstract class ArrayUtils {

	public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[] {};
	public static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};
	public static final String[] EMPTY_STRING_ARRAY = new String[] {};
	public static final Integer[] EMPTY_INTEGER_ARRAY = new Integer[] {};
	public static final int[] EMPTY_INT_ARRAY = new int[] {};
	public static final Double[] EMPTY_DOUBLE_ARRAY = new Double[] {};
	public static final double[] EMPTY_RAW_DOUBLE_ARRAY = new double[] {};
	public static final Float[] EMPTY_FLOAT_ARRAY = new Float[] {};
	public static final float[] EMPTY_RAW_FLOAT_ARRAY = new float[] {};
	public static final Character[] EMPTY_CHARACTER_ARRAY = new Character[] {};
	public static final char[] EMPTY_CHAR_ARRAY = new char[] {};
	public static final Byte[] EMPTY_BYTE_ARRAY = new Byte[] {};
	public static final byte[] EMPTY_RAW_BYTE_ARRAY = new byte[] {};

	public static String array2String(String[] array){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(",").append(array[i]);
		}
		return buffer.toString().replaceFirst(",", "");
	}

	public static boolean isEmpty(Object[] args){
		if (args == null || args.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 去除数组中的null值
	 * <p>[1,5,null,3,2,null] --> [1,5,3,2]</p>
	 * <p>[null,null] --> []</p>
	 * @param arr 数组
	 * @return 该数组元素数据类型的集合,如果数组为null,则返回null
	 */
	public static <T> List<T> trim(T[] arr) {
		if (arr == null) {
			return null;
		}
		List<T> temp = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				temp.add(arr[i]);
			}
		}
		return temp;
	}
}
