/**
 * 项目名: java-code-tutorials-design-pattern-composite
 * 包名:  net.fantesy84.tree
 * 文件名: TreeNode.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class TreeNode {
	private Integer id;
	private String name;
	private TreeNode parent;
	private List<TreeNode> children = new ArrayList<TreeNode>();
	
	/**
	 * 
	 */
	public TreeNode() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 */
	public TreeNode(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void addChild(TreeNode node) {
		children.add(node);
	}
	
	public void removeChild(TreeNode node) {
		children.remove(node);
	}
	
	public TreeNode[] catchChildren() {
		return children.toArray(new TreeNode[children.size()]);
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}
