package net.fantesy84.common.util.excel.core;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import net.fantesy84.common.util.excel.annotations.ExcelColumn;

/**
 * 单元格处理器
 * @author junjie.ge
 *
 */
public interface ExcelCellHandler {

	/**
	 * 创建单元格数据(仅限 MS excel 2007及以上版本)
	 * @param originalValue POJO实例中属性的值,可为null
	 * @param cellIndex 单元格下标
	 * @param row POI行对象,请参见{@link Row}
	 * @param columnAnnotation 列注解,请参见{@link ExcelColumn},可为null
	 * @param wb POI工作表对象,用于创建自定义数据格式化对象{@link DataFormat},不可为null
	 * @return POI扩展包单元格对象,请参见{@link Cell}
	 * @throws Exception
	 */
	Cell createCell(Object originalValue, int cellIndex, Row row, ExcelColumn columnAnnotation, Workbook wb) throws Exception;
}
