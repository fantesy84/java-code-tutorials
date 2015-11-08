/**
 * Project java-code-tutorials-tools
 * File: ReflectUtilsTest.java
 * CreateTime: 2015年11月8日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.test.reflect;

import java.lang.reflect.Field;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		fs = ReflectUtils.searchFields(dto, "^sub....");
		System.out.println(fs.length);
	}
	
	@Test
	public void test5(){
		String[] strs = new String[]{"subClassPath","subType"};
		for (String s : strs) {
			if (s.matches("sub.*")) {
				System.out.println(s);
			} else {
				System.out.println("raw: " + s);
			}
		}
	}
}
