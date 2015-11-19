/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.web.util
 * 文件名: ResponseStatusCode.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.web.util;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
public enum ResponseStatusCode {
	SCCUESS(200,"成功"),
	ERROR(500,"服务器错误!");
	
	
	private int key;
	private String value;
	private ResponseStatusCode(int key, String value){
		this.key = key;
		this.value = value;
	}
	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
}
