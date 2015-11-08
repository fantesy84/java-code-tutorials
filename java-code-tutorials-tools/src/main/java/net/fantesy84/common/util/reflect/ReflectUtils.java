/**
 * Project gb-platform-sys-domain
 * File: ReflectUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TypeName: ReflectUtils
 * <P>TODO
 * 
 * <P>CreateTime: 2015年10月17日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class ReflectUtils {
	private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);
	public static Class<?> getClass(String className){
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	public static Object getInstance(String className){
		Object obj = null;
		try {
			obj = getClass(className).newInstance();
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return obj;
	}
	
	public static Object getInstance(String className, Object...args){
		Class<?> clazz = getClass(className);
		Object obj = null;
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getConstructor(ReflectEntry.getParameterTypes(args));
			obj = constructor.newInstance(args);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (SecurityException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
			constructor.setAccessible(true);
			return getInstance(className, args);
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
			return getInstance(className);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
			return getInstance(className);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return obj;
	}
	
	private static class ReflectEntry{
		static Class<?>[] getParameterTypes(Object...args){
			List<Class<?>> types = new ArrayList<Class<?>>(0);
			for (int i = 0; i < args.length; i++) {
				types.add(args[i] == null? null : args[i].getClass());
			}
			return types.toArray(new Class<?>[types.size()]);
		}
	}
}
