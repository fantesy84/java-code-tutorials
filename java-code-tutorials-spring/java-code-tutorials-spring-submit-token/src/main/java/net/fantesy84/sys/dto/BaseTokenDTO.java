/**
 * 项目名: java-code-tutorials-spring-submit-token
 * 包名:  net.fantesy84.user.domain
 * 文件名: BaseTokenDTO.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月25日
 */
package net.fantesy84.sys.dto;

/**
 * @author Andronicus
 * @since 2015年12月25日
 */
public class BaseTokenDTO extends BaseDTO {
	private static final long serialVersionUID = -469121462422696075L;
	private Boolean result;
	private String token;
	
	/**
	 * @return the result
	 */
	public Boolean getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
