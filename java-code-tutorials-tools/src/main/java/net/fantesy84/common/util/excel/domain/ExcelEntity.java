/**
 * Project: util
 * Created: 2016年10月26日
 * Copyright ©2016 Andronicus.Ge. All rights reserved.
 */
package net.fantesy84.common.util.excel.domain;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author junjie.ge
 *
 */
public abstract class ExcelEntity<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 109045454038994773L;
	private Class<T> subClass;
	private List<T> excelData;

	@SuppressWarnings("unchecked")
	protected ExcelEntity() {
		super();
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.subClass = (Class<T>) type.getActualTypeArguments()[0];
	}

	/**
	 * @return the clazz
	 */
	public Class<T> getSubClass() {
		return subClass;
	}

	/**
	 * @return the excelData
	 */
	public List<T> getExcelData() {
		return excelData;
	}

	/**
	 * @param excelData the excelData to set
	 */
	public void setExcelData(List<T> excelData) {
		this.excelData = excelData;
	}

}
