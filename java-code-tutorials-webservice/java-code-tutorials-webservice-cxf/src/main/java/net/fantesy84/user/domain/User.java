/**
 * 项目名: java-tutorials-webservice-cxf-restful
 * 包名:  net.fantesy84.user.domain
 * 文件名: User.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月4日
 */
package net.fantesy84.user.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Andronicus
 * @since 2015年11月4日
 */
@XmlRootElement
public class User implements Serializable {
	private static final long serialVersionUID = -7373866585364487197L;
	private Integer id;
	private String name;
	private String sex;
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
