/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer
 * 文件名: CPU.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public abstract class CPU implements Serializable {
	private static final long serialVersionUID = -8544236415659076823L;
	private String brand;
	private String family;
	private String generation;
	private String model;
	private String mainFrequency;
	private Integer coreNumber;
	private Integer threadNumber;
	private Double price;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CPU: " + brand + " " + family + " " + generation + " " + model + " (" + coreNumber + "核" + threadNumber + "线程)" +" @ " + mainFrequency;
	}
	
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
	 * @return the generation
	 */
	public String getGeneration() {
		return generation;
	}
	/**
	 * @param generation the generation to set
	 */
	public void setGeneration(String generation) {
		this.generation = generation;
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
	 * @return the mainFrequency
	 */
	public String getMainFrequency() {
		return mainFrequency;
	}
	/**
	 * @param mainFrequency the mainFrequency to set
	 */
	public void setMainFrequency(String mainFrequency) {
		this.mainFrequency = mainFrequency;
	}
	/**
	 * @return the coreNumber
	 */
	public Integer getCoreNumber() {
		return coreNumber;
	}
	/**
	 * @param coreNumber the coreNumber to set
	 */
	public void setCoreNumber(Integer coreNumber) {
		this.coreNumber = coreNumber;
	}
	/**
	 * @return the threadNumber
	 */
	public Integer getThreadNumber() {
		return threadNumber;
	}
	/**
	 * @param threadNumber the threadNumber to set
	 */
	public void setThreadNumber(Integer threadNumber) {
		this.threadNumber = threadNumber;
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
