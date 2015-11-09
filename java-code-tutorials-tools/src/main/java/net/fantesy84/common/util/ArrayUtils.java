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
