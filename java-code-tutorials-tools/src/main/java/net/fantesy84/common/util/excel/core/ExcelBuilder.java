package net.fantesy84.common.util.excel.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.common.util.ArrayUtils;
import net.fantesy84.common.util.excel.annotations.ExcelColumn;
import net.fantesy84.common.util.excel.annotations.ExcelSheet;
import net.fantesy84.common.util.excel.domain.ExcelEntity;

/**
 * Excel创建工具类.
 * <p>本类在未自定义表头处理器,行处理器,单元格处理器的前提下,使用默认的实现.
 * <p>如果需要自定义处理方式,请选择实现以下两个接口其中一个
 * <li> 行处理器接口: {@link ExcelRowHandler}
 * <li> 单元格处理器接口: {@link ExcelCellHandler}
 * @see net.fantesy84.common.util.excel.core.ExcelHeaderRowHandlerImpl
 * @see net.fantesy84.common.util.excel.core.ExcelDataRowHandlerImpl
 * @see net.fantesy84.common.util.excel.core.ExcelCellHandlerImpl
 * @author junjie.ge
 *
 */
public class ExcelBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(ExcelBuilder.class);
	private static ExcelBuilder instance;
	private ExcelRowHandler headerHandler;
	private ExcelRowHandler dataRowHandler;
	private ExcelCellHandler cellHandler;

	private ExcelBuilder(){}

	public static ExcelBuilder getInstance() {
		if (instance == null) {
			synchronized (ExcelBuilder.class) {
				if (instance == null) {
					instance = new ExcelBuilder();
				}
			}
		}
		return instance;
	}
	/**
	 * 根据给定的excel数据实体创建excel文件(Microsoft office 2007及以上版本).
	 * @param excelEntity excel数据实体
	 * @param targetPath excel文件存放路径
	 * @param fileName excel文件名
	 * @return excel文件的绝对路径字符串
	 * @throws Exception
	 */
	public String createFromEntity(ExcelEntity<?> excelEntity, String targetPath, String fileName) throws Exception{
		if (excelEntity == null) {
			throw new NullPointerException("ExcelEntity对象不能为NULL!");
		}
		Class<?> clazz = excelEntity.getSubClass();
		LOG.debug("用于创建Excel表的POJO类: [{}]", clazz);
		ExcelSheet sheetAnnotation = clazz.getAnnotation(ExcelSheet.class);
		if (sheetAnnotation != null) {
			Workbook wb = new XSSFWorkbook();
			Sheet sheet = null;
			String sheetName = sheetAnnotation.name();
			if (sheetName.trim().length() > 0) {
				sheet = wb.createSheet(sheetName);
			} else {
				sheet = wb.createSheet();
			}
			LOG.debug("创建工作表:{}", sheet.getSheetName());
			Field[] fields = clazz.getDeclaredFields();
			ExcelColumn[] annotationTempArray = new ExcelColumn[fields.length];
			Field[] annotationFieldTempArray = new Field[fields.length];

			for (int i = 0; i < fields.length; i++) {
				if (Modifier.PROTECTED < fields[i].getModifiers()) {
					continue;
				}
				ExcelColumn annotation = fields[i].getAnnotation(ExcelColumn.class);
				if (annotation != null) {
					if (annotation.index() > -1) {
						annotationTempArray[annotation.index()] = annotation;
						annotationFieldTempArray[annotation.index()] = fields[i];
					} else {
						annotationTempArray[i] = annotation;
						annotationFieldTempArray[i] = fields[i];
					}
				}
			}
			//去空
			List<ExcelColumn> templist1 = ArrayUtils.trim(annotationTempArray);
			List<Field> templist2 = ArrayUtils.trim(annotationFieldTempArray);
			ExcelColumn[] annotations = templist1.toArray(new ExcelColumn[0]);
			Field[] annotationFields = templist2.toArray(new Field[0]);

			//创建表头
			int rowIndex = 0;
			getHeaderHandler().createRow(clazz, annotations, 0, sheet);
			rowIndex++;
			List<?> datas = excelEntity.getExcelData();
			for (Object data : datas) {
				Row dataRow = getDataRowHandler().createRow(clazz, annotations, rowIndex, sheet);
				if (annotationFields.length == 0) {
					//类中的属性全都没标注ExcelColumn注解,则所有的属性都将参与填充
					for (int i = 0; i < fields.length; i++) {
						if (Modifier.PROTECTED < fields[i].getModifiers()) {
							continue;
						}
						fields[i].setAccessible(true);
						getCellHandler().createCell(fields[i].get(data), i, dataRow, null, wb);
					}
				} else {
					//只有标注了ExcelColumn注解的属性参与填充,由于已标注的属性集合下标和注解集合下标相同,所以可以直接用
					for (int i = 0; i < annotations.length; i++) {
						annotationFields[i].setAccessible(true);
						getCellHandler().createCell(annotationFields[i].get(data), i, dataRow, annotations[i], wb);
					}
				}
				rowIndex++;
			}
			File file = new File(targetPath, fileName);
			OutputStream out = new FileOutputStream(file);
			wb.write(out);
			out.close();
			wb.close();
			LOG.debug("Excel文件已生成,数据已保存于:{}", file.getAbsolutePath());
			return file.getAbsolutePath();
		} else {
			String errMsg = "Please use annotation [ExcelSheet] on type [" + clazz + "]";
			LOG.error(errMsg, new IllegalStateException(errMsg));
			throw new Exception(errMsg);
		}
	}

	public ExcelRowHandler getHeaderHandler() {
		if (headerHandler == null) {
			headerHandler = new ExcelHeaderRowHandlerImpl();
		}
		return headerHandler;
	}

	/**
	 * 设置自定义表头处理器
	 * @param headerHandler 表头处理器实例
	 */
	public void setHeaderHandler(ExcelRowHandler headerHandler) {
		this.headerHandler = headerHandler;
	}

	public ExcelRowHandler getDataRowHandler() {
		if (dataRowHandler == null) {
			dataRowHandler = new ExcelDataRowHandlerImpl();
		}
		return dataRowHandler;
	}

	/**
	 * 设置自定义行处理器
	 * @param dataRowHandler 行处理器实例
	 */
	public void setDataRowHandler(ExcelRowHandler dataRowHandler) {
		this.dataRowHandler = dataRowHandler;
	}

	public ExcelCellHandler getCellHandler() {
		if (cellHandler == null) {
			cellHandler = new ExcelCellHandlerImpl();
		}
		return cellHandler;
	}

	/**
	 * 设置单元格处理器
	 * @param cellHandler 单元格处理器实例
	 */
	public void setCellHandler(ExcelCellHandler cellHandler) {
		this.cellHandler = cellHandler;
	}

}
