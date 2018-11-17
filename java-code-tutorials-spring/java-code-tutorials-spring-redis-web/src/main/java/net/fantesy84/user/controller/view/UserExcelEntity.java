/**
 * Created: 2016-12-05
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.user.controller.view;

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
@ExcelSheet
public class UserExcelEntity implements Serializable {
	private static final long serialVersionUID = 1111317764302417026L;

	@ExcelColumn(name="用户ID", index=0)
	private Long id;
	@ExcelColumn(name="用户名", index=2)
	private String username;
	@ExcelColumn(name="密码", index=1)
	private String password;
	@ExcelColumn(name="出生日期", cellType=CellType.DATE)
	private Date birthday;
	@ExcelColumn(name="银行账户余额", formatterPattern="#,##0.00")
	private BigDecimal bankAccountBalance;
	@ExcelColumn(name="已失效", cellType=CellType.BOOLEAN)
	private Boolean delFlag;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
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
	 * @return the bankAccountBalance
	 */
	public BigDecimal getBankAccountBalance() {
		return bankAccountBalance;
	}
	/**
	 * @param bankAccountBalance the bankAccountBalance to set
	 */
	public void setBankAccountBalance(BigDecimal bankAccountBalance) {
		this.bankAccountBalance = bankAccountBalance;
	}
	/**
	 * @return the delFlag
	 */
	public Boolean getDelFlag() {
		return delFlag;
	}
	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

}
