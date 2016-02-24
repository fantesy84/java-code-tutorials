/**
 * 项目名: school-common
 * 包名:  com.shangde.school.common.poi
 * 文件名: IExcelDataRowsHandler.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月2日
 */
package net.fantesy84.common.util.poi;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Andronicus
 * @since 2015年12月2日
 */
public interface IExcelDataRowsHandler {
	<T extends Serializable> void dataRowsProcess(Sheet sheet, List<String> columnNames, List<T> data) throws Exception;
}
