/**
 * Project: java-code-tutorials-tools
 * Created: 2016年7月7日
 * ©gopay.com Inc.
 */
package net.fantesy84.test.util.poi;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * <P>
 * @author junjie.ge
 * @since JDK1.7
 */
public class ExamplePOJO implements Serializable{
	private static final long serialVersionUID = -663999725220388378L;
	private Long id;
	private String name;
	private String sex;
	private Date birthday;
	private Boolean isBadChild;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the isBadChild
	 */
	public Boolean getIsBadChild() {
		return isBadChild;
	}
	/**
	 * @param isBadChild the isBadChild to set
	 */
	public void setIsBadChild(Boolean isBadChild) {
		this.isBadChild = isBadChild;
	}
	
}
