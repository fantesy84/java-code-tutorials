/**
 * 项目名: java-code-tutorials-design-pattern-simplefactory
 * 包名:  net.fantesy84.simple.factory.food
 * 文件名: Cake.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.food;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class Cake implements Serializable {
	private static final long serialVersionUID = -967513436023154431L;
	private String name;
	private String serialNumber;
	private String desc;
	private Date productionDate;
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
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the productionDate
	 */
	public Date getProductionDate() {
		return productionDate;
	}
	/**
	 * @param productionDate the productionDate to set
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
}
