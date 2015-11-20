/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer
 * 文件名: HardDisk.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public abstract class HardDisk implements Serializable {
	private static final long serialVersionUID = -6876621157539951869L;
	private String brand;
	private String generation;
	private String model;
	private String capacity;
	private Integer speed;
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
	 * @return the speed
	 */
	public Integer getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
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
	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
}
