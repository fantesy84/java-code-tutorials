package net.fantesy84.common.util.excel.core;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.common.util.excel.annotations.ExcelColumn;

/**
 * 默认数据行处理器
 * @author junjie.ge
 *
 */
public class ExcelDataRowHandlerImpl implements ExcelRowHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ExcelDataRowHandlerImpl.class);
	@Override
	public Row createRow(Class<?> clazz, ExcelColumn[] annotations, int rowIndex, Sheet sheet) throws Exception {
		if (rowIndex == 0) {
			Exception e =  new Exception("请使用表头行处理器实现类!");
			LOG.error(e.getMessage(), new IllegalStateException(e));
			throw e;
		}
		return sheet.createRow(rowIndex);
	}

}
