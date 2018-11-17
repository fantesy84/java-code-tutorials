package net.fantesy84.common.util.excel.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.common.util.ArrayUtils;
import net.fantesy84.common.util.excel.annotations.ExcelColumn;

/**
 * 默认表头处理器
 * @author junjie.ge
 *
 */
public class ExcelHeaderRowHandlerImpl implements ExcelRowHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelHeaderRowHandlerImpl.class);

	@Override
	public Row createRow(Class<?> clazz, ExcelColumn[] annotations, int rowIndex, Sheet sheet) throws Exception {
		if (rowIndex != 0) {
			Exception e =  new Exception("请使用非表头行处理器实现类!");
			LOG.error(e.getMessage(), new IllegalStateException(e));
			throw e;
		}
		Row header = sheet.createRow(rowIndex);
		if (annotations.length > 0) {
			for (int i = 0; i < annotations.length; i++) {
				int cellIndex = annotations[i].index();
				Cell headerCell = null;
				if (cellIndex != -1) {
					headerCell = header.createCell(cellIndex);
				} else {
					headerCell = header.createCell(i);
				}
				headerCell.setCellValue(annotations[i].name());
			}
		} else {
			LOG.info("类:[{}]中无标注[ExcelColumn]的属性,将把所有的属性(忽略序列号)按照属性名称作为表头制作excel文件", clazz.getName());
			Field[] fields = clazz.getDeclaredFields();
			Field[] temp = new Field[fields.length];
			for (int i = 0; i < fields.length; i++) {
				if (Modifier.PROTECTED < fields[i].getModifiers()) {
					continue;
				}
				temp[i] = fields[i];
			}
			Field[] headerColumnFields = ArrayUtils.trim(temp).toArray(new Field[0]);
			for (int i = 0; i < headerColumnFields.length; i++) {
				Cell headerCell = header.createCell(i);
				headerCell.setCellValue(headerColumnFields[i].getName());
			}
		}
		return header;
	}

}
