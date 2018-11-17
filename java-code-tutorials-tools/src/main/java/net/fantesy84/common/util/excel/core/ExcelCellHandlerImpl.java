package net.fantesy84.common.util.excel.core;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.common.util.excel.annotations.ExcelColumn;
import net.fantesy84.common.util.excel.annotations.ExcelColumn.CellType;

/**
 * 默认单元格处理器
 * @author junjie.ge
 *
 */
public class ExcelCellHandlerImpl implements ExcelCellHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelCellHandlerImpl.class);

	@Override
	public Cell createCell(Object originalValue, int cellIndex, Row row, ExcelColumn columnAnnotation, Workbook wb) throws Exception {
		if (wb == null) {
			throw new Exception("工作表对象不可为空!");
		}
		Cell cell = row.createCell(cellIndex);
		//默认的空值填充,自定义格式化字符串和单元格数据类型
		String defaultNullValue = "-";
		String pattern = "";
		CellType type = CellType.BLANK;
		if (columnAnnotation != null) {
			defaultNullValue = columnAnnotation.defaultNullValue();
			pattern = columnAnnotation.formatterPattern();
			//处理自定义单元格格式
			if (pattern.trim().length() > 0) {
				short format = wb.createDataFormat().getFormat(pattern);
				CellStyle style = wb.createCellStyle();
				style.setDataFormat(format);
				cell.setCellStyle(style);
			}
			type = columnAnnotation.cellType();
		}
		if (originalValue == null) {
			cell.setCellValue(new XSSFRichTextString(defaultNullValue));
			return cell;
		}
		Class<?> valueType = originalValue.getClass();
		switch (type) {
		case STRING:
			cell.setCellValue(new XSSFRichTextString((String) originalValue));
			break;
		case NUMERIC:
			cell.setCellValue((double) originalValue);
			break;
		case FORMULA:
			cell.setCellFormula((String) originalValue);
			break;
		case BOOLEAN:
			cell.setCellValue((boolean) originalValue);
			break;
		case DATE:
			cell.setCellValue((Date) originalValue);
			break;
		case CALENDAR:
			cell.setCellValue((Calendar) originalValue);
			break;

		default:
			if (valueType.isAssignableFrom(String.class)) {
				cell.setCellValue(new XSSFRichTextString((String) originalValue));
			} else if (valueType.isAssignableFrom(Integer.class)) {
				cell.setCellValue(((Integer)originalValue).doubleValue());
			} else if (valueType.isAssignableFrom(Long.class)) {
				cell.setCellValue(((Long)originalValue).doubleValue());
			} else if (valueType.isAssignableFrom(Double.class)) {
				cell.setCellValue(((Double)originalValue).doubleValue());
			} else if (valueType.isAssignableFrom(Short.class)) {
				cell.setCellValue(((Short)originalValue).doubleValue());
			} else if (valueType.isAssignableFrom(Float.class)) {
				cell.setCellValue(((Float)originalValue).doubleValue());
			} else if (valueType.isAssignableFrom(BigDecimal.class)) {
				BigDecimal value = (BigDecimal)originalValue;
				cell.setCellValue(value.doubleValue());
			} else if (valueType.isAssignableFrom(Date.class)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cell.setCellValue(dateFormat.format((Date) originalValue));
			} else if (valueType.isAssignableFrom(Boolean.class)) {
				cell.setCellValue(((Boolean) originalValue).booleanValue());
			} else if (valueType.isAssignableFrom(Character.class)) {
				Character value = (Character)originalValue;
				cell.setCellValue(new XSSFRichTextString(value.toString()));
			} else if (valueType.isAssignableFrom(Byte.class)) {
				Byte value = (Byte)originalValue;
				cell.setCellValue(new XSSFRichTextString(new String(new byte[]{value})));
			} else {
				Class<?>[] classes = { String.class, Integer.class, Long.class, Double.class,
						Short.class, Float.class, BigDecimal.class, Date.class, Boolean.class,
						Character.class, Byte.class };
				Exception e = new Exception("Original value's type [" + valueType.getName() + "] is undefined! Please use these type:" + Arrays.toString(classes));
				LOG.error(e.getMessage(), new IllegalStateException(e));
				throw e;
			}
			break;
		}
		return cell;
	}

}
