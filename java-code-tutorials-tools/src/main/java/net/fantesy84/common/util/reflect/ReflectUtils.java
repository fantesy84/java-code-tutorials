/**
 * Project gb-platform-sys-domain
 * File: ReflectUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
			logger.error(e.getMessage(), new IllegalStateException(e));
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
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
			constructor.setAccessible(true);
			return getInstance(className, args);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
			return getInstance(className);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return obj;
	}
	
	public static Field[] searchFields(Object instance, String regex){
		Field[] fields = instance.getClass().getDeclaredFields();
		return getFieldsByRegex(regex, fields);
	}
	
	public static Field[] searchFields(String className, String regex){
		Field[] fields = getClass(className).getDeclaredFields();
		return getFieldsByRegex(regex, fields);
	}
	
	public static Field[] searchFields(Object instance, Class<?> fieldType) {
		Field[] fields = instance.getClass().getDeclaredFields();
		return getFieldsByType(fieldType, fields);
	}
	
	public static Field[] searchFields(String className, Class<?> fieldType) {
		Field[] fields = getClass(className).getDeclaredFields();
		return getFieldsByType(fieldType, fields);
	}
	
	public static Field searchField(String className, String fieldName){
		Field f = null;
		try {
			f = getClass(className).getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (SecurityException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return f;
	}
	
	public static Field searchField(Object instance, String fieldName){
		Field f = null;
		try {
			f = instance.getClass().getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (SecurityException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return f;
	}
	
	public static Method[] searchMethods(Object instance, Class<?> returnType, Class<?>[] exceptions, String regex) {
		if (instance == null) {
			logger.error("反射的目标对象不能为空!", new IllegalArgumentException());
		}
		Method[] methods = instance.getClass().getDeclaredMethods();
		List<Method> result = new ArrayList<Method>(0);
		List<Method> fetchByReturnType = new ArrayList<Method>(0);
		for (Method m : methods) {
			if (returnType != null) {
				if (returnType == m.getReturnType()) {
					fetchByReturnType.add(m);
				}
				continue;
			} else {
				if (m.getReturnType() == null) {
					fetchByReturnType.add(m);
				}
			}
		}
		return result.toArray(new Method[result.size()]);
	}
	
	public static Method[] searchMethods(Object instance, String regex) {
		if (instance == null) {
			logger.error("反射的目标对象不能为空!", new IllegalArgumentException());
		}
		Method[] methods = instance.getClass().getDeclaredMethods();
		return getMethodsByRegex(regex, methods);
	}
	
	public static Method[] searchMethods(String className, String regex) {
		Method[] methods = getClass(className).getDeclaredMethods();
		return getMethodsByRegex(regex, methods);
	}

	/**
	 * @param regex
	 * @param methods
	 * @return
	 */
	private static Method[] getMethodsByRegex(String regex, Method[] methods) {
		List<Method> result = new ArrayList<Method>(0);
		for (Method m : methods) {
			if (m.getName().matches(regex)) {
				result.add(m);
			}
		}
		return result.toArray(new Method[result.size()]);
	}
	
	/**
	 * @param regex
	 * @param fields
	 * @return
	 */
	private static Field[] getFieldsByRegex(String regex, Field[] fields) {
		List<Field> result = new ArrayList<Field>(0);
		for (Field f : fields) {
			if (f.getName().matches(regex)) {
				result.add(f);
			}
		}
		return result.toArray(new Field[result.size()]);
	}
	
	/**
	 * @param fieldType
	 * @param fields
	 * @return
	 */
	private static Field[] getFieldsByType(Class<?> fieldType, Field[] fields) {
		List<Field> result = new ArrayList<Field>(0);
		for (Field f : fields) {
			if (f.getType() == fieldType) {
				result.add(f);
			}
		}
		return result.toArray(new Field[result.size()]);
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
