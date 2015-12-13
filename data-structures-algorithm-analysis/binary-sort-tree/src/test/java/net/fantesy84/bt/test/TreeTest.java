/**
 * Project binary-sort-tree
 * File: TreeTest.java
 * CreateTime: 2015年12月13日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.bt.test;

import org.junit.Test;

import net.fantesy84.bt.Tree;

/**
 * TypeName: TreeTest
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月13日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class TreeTest {
	@Test
	public void test1(){
		Tree t1 = new Tree("1");
		Tree t2 = new Tree("2");
		Tree t3 = new Tree("3");
		Tree t4 = new Tree("4");
		Tree t5 = new Tree("5");
		Tree t6 = new Tree("6");
		Tree t7 = new Tree("7");
		
		t1.setLeftNode(t2);
		t1.setRightNode(t3);
		
		t2.setLeftNode(t4);
		t2.setRightNode(t5);
		
		t3.setLeftNode(t6);
		t3.setRightNode(t7);
		
		System.out.println(t1.right());
		System.out.println(t1.left());
	}
}
