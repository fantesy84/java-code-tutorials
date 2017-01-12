package net.fantesy84.common.util.excel.core;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import net.fantesy84.common.util.excel.annotations.ExcelColumn;
import net.fantesy84.common.util.excel.annotations.ExcelSheet;

/**
 * 行处理器
 * @author junjie.ge
 *
 */
public interface ExcelRowHandler {
	ExcelColumn[] EMPTY_EXCEL_COLUMN_ANNO_ARRAY = new ExcelColumn[]{};
	/**
	 * 创建数据行(仅限 MS excel 2007及以上版本)
	 * @param clazz 用于创建数据表的POJO类Class,该类必须包含一个{@link ExcelSheet}注解,不能为null
	 * @param annotations 所有已扫描到的列注解,请参见{@link ExcelColumn},不能为null
	 * @param rowIndex 行位置,从0开始,0为表头行
	 * @param sheet POI扩展包数据表对象,请参见{@link Sheet}
	 * @return POI扩展包行对象,请参见{@link Row}
	 * @throws Exception 处理表头时,如果发现<code>rowIndex</code>不是0,则会抛出异常
	 */
	Row createRow(Class<?> clazz, ExcelColumn[] annotations, int rowIndex, Sheet sheet) throws Exception;
}
