/**
 * 项目名: school-common
 * 包名:  com.shangde.school.common.poi
 * 文件名: DefaultFirstRowHandlerImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月2日
 */
package net.fantesy84.common.util.poi;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Andronicus
 * @since 2015年12月2日
 */
public class DefaultFirstRowHandlerImpl implements IExcelFirstRowHandler {

	/* (non-Javadoc)
	 * @see com.shangde.school.common.poi.IExcelFirstRowHandler#firstRowProcesser(org.apache.poi.ss.usermodel.Sheet, int)
	 */
	@Override
	public List<String> firstRowProcess(Sheet sheet) throws Exception {
		Row reportHead = sheet.getRow(0);
		List<String> columnNames = new ArrayList<String>(0);
		for (Cell cell : reportHead) {
			Comment comment = cell.getCellComment();
			if (comment != null) {
				columnNames.add(comment.getString().getString());
			}
		}
		return columnNames;
	}

}
