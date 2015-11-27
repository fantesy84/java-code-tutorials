/**
 * 项目名: java-code-tutorials-design-pattern-composite
 * 包名:  net.fantesy84.tree
 * 文件名: Tree.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.tree;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class Tree {
	private final static Logger logger = LoggerFactory.getLogger(Tree.class);
	private TreeNode root;

	/**
	 * 
	 */
	public Tree(Integer id, String name) {
		root = new TreeNode(id, name);
	}

	public static void main(String[] args) throws JsonProcessingException {
		Tree tree = new Tree(1, "A");
		TreeNode node1 = new TreeNode(2,"A1");
		TreeNode node2 = new TreeNode(3,"A2");
		
		node1.addChild(node2);
		tree.root.addChild(node1);
		System.out.println("Build tree finish!");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(tree.root);
		logger.debug("生成的树为:{}", json);
	}
}
