/**
 * Project binary-sort-tree
 * File: Tree.java
 * CreateTime: 2015年12月13日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.bt;

import java.io.Serializable;

/**
 * TypeName: Tree
 * <P>TODO
 *
 * <P>CreateTime: 2015年12月13日
 * <P>UpdateTime:
 * @author junjie.ge
 *
 */
public final class Tree implements Serializable{
	private static final long serialVersionUID = 1L;
	private Tree leftNode;
	private Tree rightNode;
	private Serializable data;

	public Tree(Serializable data) {
		this.leftNode = null;
		this.rightNode = null;
		this.data = data;
	}

	public int left() {
		return leftdepth(this);
	}

	public int right() {
		return rightdepth(this);
	}

	private int leftdepth(Tree t) {
		int i = 0;
		if (t.getLeftNode() == null) {
			return i;
		} else {
			i++;
			i = i + leftdepth(t.getLeftNode());
		}
		return i;
	}

	private int rightdepth(Tree t) {
		int i = 0;
		if (t.getRightNode() == null) {
			return i;
		} else {
			i++;
			i = i + rightdepth(t.getRightNode());
		}
		return i;
	}

	/**
	 * @return the leftNode
	 */
	public Tree getLeftNode() {
		return leftNode;
	}
	/**
	 * @param leftNode the leftNode to set
	 */
	public void setLeftNode(Tree leftNode) {
		this.leftNode = leftNode;
	}
	/**
	 * @return the rightNode
	 */
	public Tree getRightNode() {
		return rightNode;
	}
	/**
	 * @param rightNode the rightNode to set
	 */
	public void setRightNode(Tree rightNode) {
		this.rightNode = rightNode;
	}
	/**
	 * @return the data
	 */
	public Serializable getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(java.io.Serializable data) {
		this.data = data;
	}

}
