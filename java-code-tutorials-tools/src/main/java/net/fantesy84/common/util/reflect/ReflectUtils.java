/**
 * Project gb-platform-sys-domain
 * File: ReflectUtils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.common.util.StringUtils;

/**
 * TypeName: ReflectUtils
 * <P>
 * TODO
 * 
 * <P>
 * CreateTime: 2015年10月17日
 * <P>
 * UpdateTime:
 * 
 * @author junjie.ge
 *
 */
public class ReflectUtils {
	private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);
	
	/**
	 * Handle the given reflection exception. Should only be called if no
	 * checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of an
	 * InvocationTargetException with such a root cause. Throws an
	 * IllegalStateException with an appropriate message else.
	 * @param ex the reflection exception to handle
	 */
	protected static void handleReflectionException(Exception ex) {
		if (ex instanceof ClassNotFoundException) {
			throw new IllegalStateException("Class path may be wrong! " + ex.getMessage());
		}
		if (ex instanceof InstantiationException) {
			throw new IllegalStateException("The specified class object cannot be instantiated! " + ex.getMessage());
		}
		if (ex instanceof NoSuchMethodException) {
			throw new IllegalStateException("Method not found: " + ex.getMessage());
		}
		if (ex instanceof IllegalAccessException) {
			throw new IllegalStateException("Could not access method: " + ex.getMessage());
		}
		if (ex instanceof InvocationTargetException) {
			handleInvocationTargetException((InvocationTargetException) ex);
		}
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		throw new UndeclaredThrowableException(ex);
	}
	
	/**
	 * Handle the given invocation target exception. Should only be called if no
	 * checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of such a root
	 * cause. Throws an IllegalStateException else.
	 * @param ex the invocation target exception to handle
	 */
	protected static void handleInvocationTargetException(InvocationTargetException ex) {
		rethrowRuntimeException(ex.getTargetException());
	}
	
	/**
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}. Should
	 * only be called if no checked exception is expected to be thrown by the
	 * target method.
	 * <p>Rethrows the underlying exception cast to an {@link RuntimeException} or
	 * {@link Error} if appropriate; otherwise, throws an
	 * {@link IllegalStateException}.
	 * @param ex the exception to rethrow
	 * @throws RuntimeException the rethrown exception
	 */
	protected static void rethrowRuntimeException(Throwable ex) {
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		throw new UndeclaredThrowableException(ex);
	}
	
	/**
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}. Should
	 * only be called if no checked exception is expected to be thrown by the
	 * target method.
	 * <p>Rethrows the underlying exception cast to an {@link Exception} or
	 * {@link Error} if appropriate; otherwise, throws an
	 * {@link IllegalStateException}.
	 * @param ex the exception to rethrow
	 * @throws Exception the rethrown exception (in case of a checked exception)
	 */
	protected static void rethrowException(Throwable ex) throws Exception {
		if (ex instanceof Exception) {
			throw (Exception) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		throw new UndeclaredThrowableException(ex);
	}
	
	/*==============================================reflect methods====================================================*/
	
	public static Class<?> getClass(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			handleReflectionException(e);
		}
		return clazz;
	}

	public static Object getInstance(String className) {
		Object obj = null;
		try {
			obj = getClass(className).newInstance();
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return obj;
	}

	public static Object getInstance(String className, Object... args) {
		Class<?> clazz = getClass(className);
		Object obj = null;
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getConstructor(ReflectEntry.getParameterTypes(args));
			obj = constructor.newInstance(args);
		}catch (IllegalAccessException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
			constructor.setAccessible(true);
			return getInstance(className, args);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
			return getInstance(className);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return obj;
	}

	public static Field[] searchFields(Object instance, String regex) {
		Field[] fields = instance.getClass().getDeclaredFields();
		return getFieldsByRegex(regex, fields);
	}

	public static Field[] searchFields(String className, String regex) {
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

	public static Field searchField(String className, String fieldName) {
		Field f = null;
		try {
			f = getClass(className).getDeclaredField(fieldName);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return f;
	}

	public static Field searchField(Object instance, String fieldName) {
		Field f = null;
		try {
			f = instance.getClass().getDeclaredField(fieldName);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return f;
	}

	public static Field[] searchFieldsByAnnotation(String className, Class<? extends Annotation> annotationClass) {
		Field[] fields = getClass(className).getDeclaredFields();
		return getFieldsByAnnotation(annotationClass, fields);
	}
	
	public static Field[] searchFieldsByAnnotation(Object instance, Class<? extends Annotation> annotationClass) {
		Field[] fields = instance.getClass().getDeclaredFields();
		return getFieldsByAnnotation(annotationClass, fields);
	}

	public static Method[] searchMethods(Object instance, String regex) {
		if (instance == null || StringUtils.isBlank(regex)) {
			logger.error("反射的目标对象和正则表达式不能为空!", new IllegalArgumentException());
		}
		Method[] methods = instance.getClass().getDeclaredMethods();
		List<Method> result = new ArrayList<Method>(0);
		for (Method method : methods) {
			if (method.getName().matches(regex)) {
				result.add(method);
				logger.debug("找到名称与表达式{}匹配的方法:{}", regex, method.getName());
			}
		}
		return result.toArray(new Method[result.size()]);
	}

	public static Method[] searchMethods(Object instance, Class<?> returnType) {
		if (instance == null || returnType == null) {
			logger.error("反射的目标对象和返回值类型不能为空!", new IllegalArgumentException());
		}
		Method[] methods = instance.getClass().getDeclaredMethods();
		List<Method> result = new ArrayList<Method>(0);
		for (Method method : methods) {
			if (method.getReturnType() == returnType) {
				result.add(method);
				logger.debug("找到返回类型为{}的方法:{}", returnType, method.getName());
			}
		}
		return result.toArray(new Method[result.size()]);
	}

	public static Method[] searchMethods(Object instance, Class<?>... exceptions) {
		if (instance == null) {
			logger.error("反射的目标对象不能为空!", new IllegalArgumentException());
		}
		Method[] methods = instance.getClass().getDeclaredMethods();
		List<Method> result = new ArrayList<Method>(0);
		for (Method method : methods) {
			if (exceptions != null && exceptions.length > 0) {
				Class<?>[] methodExceptions = method.getExceptionTypes();
				OK: for (int i = 0; i < exceptions.length; i++) {
					Class<?> targetException = exceptions[i];
					for (int j = 0; j < methodExceptions.length; j++) {
						if (methodExceptions[j] == targetException) {
							result.add(method);
							logger.debug("找到抛出的异常类型包含{}的方法:{}", targetException, method.getName());
							break OK;
						}
					}
				}
			}
		}
		return result.toArray(new Method[result.size()]);
	}

	public static Method[] searchMethodsByAnnotation(Object instance, Class<? extends Annotation> annotationClass) {
		Method[] methods = instance.getClass().getDeclaredMethods();
		List<Method> result = new ArrayList<Method>(0);
		if (methods != null && methods.length > 0) {
			for (int i = 0; i < methods.length; i++) {
				Annotation annotation = methods[i].getAnnotation(annotationClass);
				if (annotation != null) {
					result.add(methods[i]);
				}
			}
		}
		return result.toArray(new Method[result.size()]);
	}
	
	public static Method searchMethod(Object instance, String methodName, Class<?>... parameterTypes) {
		if (instance == null || methodName == null || methodName.isEmpty()) {
			logger.error("反射的目标对象,方法名不能为空!", new IllegalArgumentException());
		}
		Method target = null;
		try {
			target = instance.getClass().getMethod(methodName, parameterTypes);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return target;
	}
	
	public static void setter(Object targetObj, String fieldName, Object paramValue){
		Class<?> clazz = targetObj.getClass();
		String methodName = "set" + StringUtils.capitalize(fieldName);
		Method writeMethod = null;
		try {
			writeMethod = clazz.getDeclaredMethod(methodName, ReflectEntry.getParameterTypes(new Object[]{paramValue}));
			writeMethod.invoke(targetObj, new Object[]{paramValue});
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}
	
	public static <T> T getter(Object targetObj, String fieldName, Class<T> fieldType) {
		Class<?> clazz = targetObj.getClass();
		String methodName = "get" + StringUtils.capitalize(fieldName);
		Method readMethod = null;
		T result = null;
		try {
			readMethod = clazz.getDeclaredMethod(methodName, new Class<?>[]{});
			Object value = readMethod.invoke(targetObj, new Object[]{});
			result = fieldType.cast(value);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return result;
	}
	
	/*======================================================private class and methods========================================================*/
	
	private static Field[] getFieldsByRegex(String regex, Field[] fields) {
		List<Field> result = new ArrayList<Field>(0);
		for (Field f : fields) {
			logger.debug("Field {} will matches regex:{}", f.getName(), regex);
			if (f.getName().matches(regex)) {
				result.add(f);
			}
		}
		return result.toArray(new Field[result.size()]);
	}

	private static Field[] getFieldsByType(Class<?> fieldType, Field[] fields) {
		List<Field> result = new ArrayList<Field>(0);
		for (Field f : fields) {
			if (f.getType() == fieldType) {
				result.add(f);
			}
		}
		return result.toArray(new Field[result.size()]);
	}

	private static Field[] getFieldsByAnnotation(Class<? extends Annotation> annotationClass, Field[] fields) {
		List<Field> result = new ArrayList<Field>(0);
		for (int i = 0; i < fields.length; i++) {
			Annotation annotation = fields[i].getAnnotation(annotationClass);
			if (annotation != null) {
				result.add(fields[i]);
			}
		}
		return result.toArray(new Field[result.size()]);
	}
	
	private static class ReflectEntry {
		static Class<?>[] getParameterTypes(Object... args) {
			List<Class<?>> types = new ArrayList<Class<?>>(0);
			for (int i = 0; i < args.length; i++) {
				types.add(args[i] == null ? null : args[i].getClass());
			}
			return types.toArray(new Class<?>[types.size()]);
		}
	}
}
