/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer
 * 文件名: MotherBoard.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public abstract class MotherBoard implements Serializable {
	private static final long serialVersionUID = -5137046198178695141L;
	private String brand;
	private String family;
	private String model;
	private String mainChipsetName;
	private Double price;
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}
	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the mainChipsetName
	 */
	public String getMainChipsetName() {
		return mainChipsetName;
	}
	/**
	 * @param mainChipsetName the mainChipsetName to set
	 */
	public void setMainChipsetName(String mainChipsetName) {
		this.mainChipsetName = mainChipsetName;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
