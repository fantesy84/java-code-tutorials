/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.user.domain
 * 文件名: UserReq.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.user.domain;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
public class UserReq implements Serializable {
	private static final long serialVersionUID = -4184545468904735L;
	private String account;
	private String password;
	private String publicKey;
	private String privateKey;
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}
	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
}
