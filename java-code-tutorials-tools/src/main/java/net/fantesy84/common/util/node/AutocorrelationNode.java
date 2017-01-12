/**
 * Created: 2017-01-03
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.common.util.node;

import java.util.Collection;

/**
 * @author junjie.ge
 *
 */
public class AutocorrelationNode<T> implements Node<T> {
	private String key;
	private T val;
	private AutocorrelationNode<T> parentNode;
	private Collection<AutocorrelationNode<T>> childNodes;
	private int deep;

	public AutocorrelationNode() {
	}

	public AutocorrelationNode(String key, T val) {
		super();
		this.key = key;
		this.val = val;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.fantesy84.common.util.node.Node#value()
	 */
	@Override
	public T value() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.fantesy84.common.util.node.Node#parent()
	 */
	@Override
	public Node<T> parent() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.fantesy84.common.util.node.Node#children()
	 */
	@Override
	public Collection<Node<T>> children() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the val
	 */
	public T getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(T val) {
		this.val = val;
	}

	/**
	 * @return the parentNode
	 */
	public AutocorrelationNode<T> getParentNode() {
		return parentNode;
	}

	/**
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(AutocorrelationNode<T> parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * @return the childNodes
	 */
	public Collection<AutocorrelationNode<T>> getChildNodes() {
		return childNodes;
	}

	/**
	 * @param childNodes the childNodes to set
	 */
	public void setChildNodes(Collection<AutocorrelationNode<T>> childNodes) {
		this.childNodes = childNodes;
	}

	/**
	 * @return the deep
	 */
	public int getDeep() {
		return deep;
	}

	/**
	 * @param deep the deep to set
	 */
	public void setDeep(int deep) {
		this.deep = deep;
	}
}
