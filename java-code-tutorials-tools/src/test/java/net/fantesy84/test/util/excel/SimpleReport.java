/**
 * Created: 2016-11-28
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.util.excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.fantesy84.common.util.excel.annotations.ExcelColumn;
import net.fantesy84.common.util.excel.annotations.ExcelColumn.CellType;
import net.fantesy84.common.util.excel.annotations.ExcelSheet;

/**
 * @author junjie.ge
 *
 */
@ExcelSheet(name="简单报表")
public class SimpleReport implements Serializable {
	private static final long serialVersionUID = 4079981693437825040L;
	@ExcelColumn(name="用户名", index=2)
	private String username;
	@ExcelColumn(name="密码", index=0)
	private String password;
	@ExcelColumn(name="生日", index=1)
	private Date birthday;
	@ExcelColumn(name="银行账号")
	private String accountNum;
	@ExcelColumn(name="账号余额", formatterPattern="￥#,##0.00")
	private BigDecimal accountBalance;
	@ExcelColumn(name="是否有效", cellType=CellType.BOOLEAN)
	private Boolean deleteFlag;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the accountNum
	 */
	public String getAccountNum() {
		return accountNum;
	}
	/**
	 * @param accountNum the accountNum to set
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	/**
	 * @return the accountBalance
	 */
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	/**
	 * @return the deleteFlag
	 */
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
