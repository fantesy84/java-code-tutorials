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

	public static Class<?> getClass(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return clazz;
	}

	public static Object getInstance(String className) {
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

	public static Object getInstance(String className, Object... args) {
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
		} catch (NoSuchFieldException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (SecurityException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return f;
	}

	public static Field searchField(Object instance, String fieldName) {
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

	public static Field[] searchFieldsByAnnotation(String className, Class<? extends Annotation> annotationClass) {
		Field[] fields = getClass(className).getDeclaredFields();
		return getFieldsByAnnotation(annotationClass, fields);
	}
	
	public static Field[] searchFieldsByAnnotation(Object instance, Class<? extends Annotation> annotationClass) {
		Field[] fields = instance.getClass().getDeclaredFields();
		return getFieldsByAnnotation(annotationClass, fields);
	}

	public static Method[] searchMethods(Object instance, String regex) {
		if (instance == null || StringUtils.isNullOrEmpty(regex)) {
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
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} catch (SecurityException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return target;
	}

	/**
	 * @param regex
	 * @param fields
	 * @return
	 */
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

	/**
	 * @param annotationClass
	 * @param fields
	 * @return
	 */
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
