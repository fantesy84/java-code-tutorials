/**
 * Project: java-code-tutorials-tools
 * Created: 2016年7月7日
 * ©gopay.com Inc.
 */
package net.fantesy84.test.util.poi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.junit.Before;
import org.junit.Test;

import net.fantesy84.common.util.poi.DefaultFirstRowHandlerImpl;
import net.fantesy84.common.util.poi.IExcelDataRowsHandler;
import net.fantesy84.common.util.poi.IExcelFirstRowHandler;
import net.fantesy84.common.util.poi.PoiUtils;
import net.fantesy84.common.util.reflect.ReflectUtils;
import net.fantesy84.common.util.resource.ResourceUtil;

/**
 * Description:
 * <P>
 * @author junjie.ge
 * @since JDK1.7
 */
public class PoiUtilsTest {
	private List<ExamplePOJO> children;
	private IExcelFirstRowHandler titleHandler;
	private IExcelDataRowsHandler rowsHandler;

	@Before
	public void init(){
		titleHandler = new DefaultFirstRowHandlerImpl();
		rowsHandler = new ExamplePOJOListHandler();
		children = new ArrayList<ExamplePOJO>();
		for (int i = 0; i < 10; i++) {
			ExamplePOJO child = new ExamplePOJO();
			child.setId(new Long(i + 1));
			child.setName("学生" + (i+1) + "号");
			child.setSex("男");
			child.setBirthday(new Date((long)(Math.random() * 1000000000)));
			child.setIsBadChild(Boolean.FALSE);
			children.add(child);
		}
	}
	@Test
	public void test1() throws Exception{
		PoiUtils instance = PoiUtils.getInstance();
		instance.setDataRowsHandler(rowsHandler);
		instance.setFirstRowHandler(titleHandler);
		String path = ResourceUtil.getURL("classpath:children.xlsx").getPath();
		System.out.println(path);
		instance.buildExcelFromTemplate(children, path);
	}

	private class ExamplePOJOListHandler implements IExcelDataRowsHandler {

		/* (non-Javadoc)
		 * @see net.fantesy84.common.util.poi.IExcelDataRowsHandler#dataRowsProcess(org.apache.poi.ss.usermodel.Sheet, java.util.List, java.util.List)
		 */
		@Override
		public <T extends Serializable> void dataRowsProcess(Sheet sheet, List<String> columnNames, List<T> data)
				throws Exception {
			int j = 1;
			for (int i = 0; i < data.size(); i++) {
				Row row = sheet.getRow(j) == null ? sheet.createRow(j) : sheet.getRow(j);
				j++;
				T child = data.get(i);
				for (int k = 0; k < columnNames.size(); k++) {
					String fieldname = columnNames.get(k);
					System.out.println("columnName: " + fieldname);
					Object childProperty = ReflectUtils.getter(child, fieldname, ReflectUtils.searchField(child, fieldname).getType());
					System.out.println("property-value: " + childProperty);
					Cell cell = row.getCell(k) == null ? row.createCell(k) : row.getCell(k);
					String cellValueString = null;
					if (childProperty instanceof Date) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						cellValueString = format.format((Date)childProperty);
					} else {
						cellValueString = String.valueOf(childProperty == null ? "" : childProperty);
					}
					cell.setCellValue(new XSSFRichTextString(cellValueString));
				}
			}
		}

	}
}
