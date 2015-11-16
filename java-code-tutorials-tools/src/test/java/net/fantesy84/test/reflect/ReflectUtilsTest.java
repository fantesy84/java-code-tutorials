/**
 * Project java-code-tutorials-tools
 * File: ReflectUtilsTest.java
 * CreateTime: 2015年11月8日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fantesy84.common.util.reflect.ReflectUtils;

/**
 * TypeName: ReflectUtilsTest
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月8日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class ReflectUtilsTest {
	private Target t;
	private BaseDTO dto;
	private final String T_CLASSNAME = "net.fantesy84.test.reflect.Target";
	private final String B_CLASSNAME = "net.fantesy84.test.reflect.BaseDTO";
	
	@Before
	public void init(){
		t = new Target();
		t.setId(new Integer(1));
		t.setIntId(1);
		t.setName("test");
		t.setSex(new Character('1'));
		t.setCharSex('1');
		t.setBirthday(new Date());
		dto = t;
	}
	
	@Test
	public void test1(){
		System.out.println(ReflectUtils.getClass(B_CLASSNAME));
		System.out.println(ReflectUtils.getClass(T_CLASSNAME));
	}
	
	@Test
	public void test2(){
		Target instance = (Target) ReflectUtils.getInstance(T_CLASSNAME);
		Assert.assertNull(instance.getId());
	}
	@Test
	public void test3(){
		BaseDTO instance = (Target) ReflectUtils.getInstance(B_CLASSNAME);
		Assert.assertNotNull(instance);
	}
	@Test
	public void test4() throws IllegalArgumentException, IllegalAccessException{
		Field[] fs = ReflectUtils.searchFields(dto, "name");
		System.out.println(fs[0].getType());
		Field f = ReflectUtils.searchField(t, "name");
		f.setAccessible(true);
		System.out.println(f.get(t));
		fs = ReflectUtils.searchFields(dto, "s.*");
		System.out.println(fs.length);
	}
	
	@Test
	public void test5() throws NoSuchMethodException, SecurityException{
		Class<Target> clazz = Target.class;
		Method sexSetter = clazz.getMethod("setSex", Character.class);
		System.out.println(sexSetter.getReturnType());
	}
	
	@Test
	public void test6(){
		List<String> list1 = new ArrayList<String>(0);
		List<String> list2 = new ArrayList<String>(0);
		List<String> list3 = new ArrayList<String>(0);
		list1.add("A");
		list1.add("B");
		list1.add("C");
		list2.add("B");
		list2.add("C");
		list2.add("D");
		list3.add("C");
		list3.add("D");
		list3.add("E");
		list1.retainAll(list2);
		list1.retainAll(list3);
		System.out.println(list1);
	}
	
	@Test
	public void test7(){
		Method[] voids = ReflectUtils.searchMethods(t, void.class);
		for (Method method : voids) {
			System.out.println(method.getName());
		}
		Method[] getI = ReflectUtils.searchMethods(t, "getI.*");
		for (Method method : getI) {
			System.out.println(method.getName());
		}
		Method[] getEx = ReflectUtils.searchMethods(t, new Class<?>[]{Exception.class});
		for (Method method : getEx) {
			System.out.println(method.getName());
		}
	}
	
	@Test
	public void test8(){
		Field[] fields = ReflectUtils.searchFieldsByAnnotation(t, JsonFormat.class);
		for (Field f : fields) {
			System.out.println(f.getName());
		}
	}
	@Test
	public void test9() throws JsonProcessingException{
		Method[] methods = ReflectUtils.searchMethodsByAnnotation(t, JsonProperty.class);
		for (Method m : methods) {
			System.out.println(m.getName());
			JsonProperty jsonProperty = m.getAnnotation(JsonProperty.class);
			System.out.println(jsonProperty.value());
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(t);
		System.out.println(json);
	}
}
