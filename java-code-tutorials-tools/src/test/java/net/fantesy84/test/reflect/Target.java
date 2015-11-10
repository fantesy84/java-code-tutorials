/**
 * Project java-code-tutorials-tools
 * File: Target.java
 * CreateTime: 2015年11月8日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.test.reflect;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TypeName: Target
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月8日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
@XmlRootElement
public class Target extends BaseDTO{
	private static final long serialVersionUID = 4425453200333362740L;
	private Integer id;
	private int intId;
	private String name;
	private Character sex;
	private char charSex;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date birthday;
	/**
	 * @return the id
	 */
	@JsonProperty(value="MyID",index=1)
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
	 * @return the intId
	 */
	public int getIntId() {
		return intId;
	}
	/**
	 * @param intId the intId to set
	 */
	public void setIntId(int intId) {
		this.intId = intId;
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
	public Character getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Character sex) {
		this.sex = sex;
	}
	/**
	 * @return the charSex
	 */
	public char getCharSex() {
		return charSex;
	}
	/**
	 * @param charSex the charSex to set
	 */
	public void setCharSex(char charSex) {
		this.charSex = charSex;
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
	
	public String eat(String foodName) throws Exception{
		if (foodName == null) {
			throw new NullPointerException();
		}
		return this.name + "is eating " + foodName;
	}
}
