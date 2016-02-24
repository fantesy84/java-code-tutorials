/**
 * 项目名: school-common
 * 包名:  com.shangde.school.common.poi
 * 文件名: PoiUtils.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月30日
 */
package net.fantesy84.common.util.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2015年11月30日
 */
public class PoiUtils {
	private final static Logger logger = LoggerFactory.getLogger(PoiUtils.class);
	private final static String SERVER_FILE_PATH = System.getProperty("user.dir");
	private final static String SERVER_FILE_SEPARATOR = System.getProperty("file.separator");
	private final static String DEFAULT_DIR = SERVER_FILE_PATH + SERVER_FILE_SEPARATOR + "tmp_excel";
	
	private String dir = DEFAULT_DIR;
	private IExcelFirstRowHandler firstRowHandler;
	private IExcelDataRowsHandler dataRowsHandler;
	
	private PoiUtils(){
		firstRowHandler = new DefaultFirstRowHandlerImpl();
	}
	
	public static PoiUtils getInstance(){
		return PoiUtilsInstanceFactory.newInstance;
	}
	
	private static class PoiUtilsInstanceFactory {
		public static PoiUtils newInstance = new PoiUtils();
	}
	
	public <T extends Serializable> String buildExcelFromTemplate(List<T> data, String templateFilePath) throws Exception {
		Workbook wb = WorkbookFactory.create(new File(templateFilePath));
		Sheet sheet = wb.getSheetAt(0);
		List<String> columnNames = firstRowHandler.firstRowProcess(sheet);
		dataRowsHandler.dataRowsProcess(sheet, columnNames, data);
		String fileName = UUID.randomUUID().toString().substring(0, 32);
		File dirFile = new File(dir);
		if (!dirFile.isDirectory() && !dirFile.exists()) {
			dirFile.mkdirs();
		}
		String target = dir + SERVER_FILE_SEPARATOR + fileName + ".xlsx";
		logger.debug("生成待下载的Excel文件:{}", target);
		OutputStream os = new FileOutputStream(target);
		wb.write(os);
		os.close();
		return target;
	}
	
	public <T extends Serializable> void responseExcelFile(HttpServletResponse response, List<T> data, String templateFilePath) throws Exception {
		Workbook wb = WorkbookFactory.create(new File(templateFilePath));
		Sheet sheet = wb.getSheetAt(0);
		List<String> columnNames = firstRowHandler.firstRowProcess(sheet);
		dataRowsHandler.dataRowsProcess(sheet, columnNames, data);
		logger.debug("将文件直接以流的形式返回页面...");
		response.reset();
		ServletOutputStream os = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment; filename=export.xlsx");
		response.setContentType("application/x-excel");
		wb.write(os);
		os.flush();
		os.close();
	}

	/**
	 * @return the firstRowHandler
	 */
	public IExcelFirstRowHandler getFirstRowHandler() {
		return firstRowHandler;
	}

	/**
	 * @param firstRowHandler the firstRowHandler to set
	 */
	public void setFirstRowHandler(IExcelFirstRowHandler firstRowHandler) {
		this.firstRowHandler = firstRowHandler;
	}

	/**
	 * @return the dataRowsHandler
	 */
	public IExcelDataRowsHandler getDataRowsHandler() {
		return dataRowsHandler;
	}

	/**
	 * @param dataRowsHandler the dataRowsHandler to set
	 */
	public void setDataRowsHandler(IExcelDataRowsHandler dataRowsHandler) {
		this.dataRowsHandler = dataRowsHandler;
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	
}
