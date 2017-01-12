/**
 * Created: 2017-01-04
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.domain;

import java.io.Serializable;

/**
 * @author junjie.ge
 *
 */
public class ValidationResult implements Serializable {
	private static final long serialVersionUID = -6894779457094980281L;
	private Boolean isSuccess;
	private String msg;
	/**
	 * @return the isSuccess
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
