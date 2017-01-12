/**
 * Created: 2016-12-05
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.common.util.excel.view;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import net.fantesy84.common.util.ArrayUtils;
import net.fantesy84.common.util.excel.annotations.ExcelColumn;
import net.fantesy84.common.util.excel.annotations.ExcelSheet;
import net.fantesy84.common.util.excel.core.ExcelCellHandler;
import net.fantesy84.common.util.excel.core.ExcelCellHandlerImpl;
import net.fantesy84.common.util.excel.core.ExcelDataRowHandlerImpl;
import net.fantesy84.common.util.excel.core.ExcelHeaderRowHandlerImpl;
import net.fantesy84.common.util.excel.core.ExcelRowHandler;

/**
 * 注解形式的Excel导出视图,使用时请继承本抽象类并定义注解过的实体类型.可自定义表头,行,单元格处理方式.
 * 使用方法:
 * <blockquote>
 * <h5>excel注解实体示例:</h5>
 * <code>
 * <tt>@ExcelSheet</tt><br/>
 * public class UserExcelEntity implements Serializable {<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;@ExcelColumn(name="用户ID", index=0)<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;private Long id;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;@ExcelColumn(name="用户名", index=2)<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;private String username;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;@ExcelColumn(name="密码", index=1)<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;private String password;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;@ExcelColumn(name="出生日期", cellType=CellType.DATE)<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;private Date birthday;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;@ExcelColumn(name="银行账户余额", formatterPattern="#,##0.00")<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;private BigDecimal bankAccountBalance;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;@ExcelColumn(name="已失效", cellType=CellType.BOOLEAN)<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;private Boolean delFlag;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;setter and getter ...<br/>
 * }<br/>
 * </code>
 * <h5>子View示例:</h5>
 * <code>
 * public class UserExcelView extends AnnotatedExcelView<<tt>UserExcelEntity</tt>> {}<br/>
 * </code>
 * <h5>controller示例:</h5>
 * <code>
 * <tt>@RequestMapping</tt>("/export/excel")<br/>
 * public ModelAndView exportExcel() {<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;UserExcelView view = new UserExcelView();<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;view.setAttachmentName("导出文件的名称");<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;List<<tt>UserExcelEntity</tt>> data = service.findData();<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;view.setData(data);<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;ModelAndView mav = new ModelAndView();<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;mav.setView(view);<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;return mav;<br/>
 * }<br/>
 * </code>
 * </blockquote>
 * @author junjie.ge
 *
 */
public abstract class AnnotatedExcelView<T> extends AbstractXlsxStreamingView {
	private static final Logger LOG = LoggerFactory.getLogger(AnnotatedExcelView.class);
	private static final String DEFAULT_ATTCHMENT_NAME = "export-excel";
	private static final String DEFAULT_CHARSET_NAME = "ISO-8859-1";
	private Class<T> annotationClass;
	private List<T> data;
	private String attachmentName = DEFAULT_ATTCHMENT_NAME;
	private String charsetName = DEFAULT_CHARSET_NAME;

	private ExcelRowHandler headerHandler;
	private ExcelRowHandler dataRowHandler;
	private ExcelCellHandler cellHandler;

	@SuppressWarnings("unchecked")
	public AnnotatedExcelView() {
		super();
		super.setBeanName("Andronicus.Ge_Excel_Export_Tools");
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.annotationClass = (Class<T>) type.getActualTypeArguments()[0];
		this.headerHandler = new ExcelHeaderRowHandlerImpl();
		this.dataRowHandler = new ExcelDataRowHandlerImpl();
		this.cellHandler = new ExcelCellHandlerImpl();
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setCharacterEncoding(charsetName);
		String userAgent = request.getHeader("user-agent").toLowerCase();
		if (userAgent != null) {
            if (userAgent.contains("firefox")) {
                // firefox有默认的备用字符集是西欧字符集
                attachmentName = new String(attachmentName.getBytes("UTF-8"), "ISO8859-1");
            } else if (userAgent.contains("webkit") && (userAgent.contains("chrome") || userAgent.contains("safari"))) {
                // webkit核心的浏览器,主流的有chrome,safari,360
                attachmentName = new String(attachmentName.getBytes("UTF-8"), "ISO8859-1");
            } else {
                // 新老版本的IE都可直接用URL编码工具编码后输出正确的名称,无乱码
                attachmentName = URLEncoder.encode(attachmentName, "UTF-8");
            }
        }
		response.setHeader("Content-Disposition", "attachment;filename=" + attachmentName + ".xlsx");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Prama", "no-cache");

		LOG.debug("用于创建Excel表的实例类型: [{}]", annotationClass);
		ExcelSheet sheetAnnotation = annotationClass.getAnnotation(ExcelSheet.class);
		if (sheetAnnotation != null) {
			Sheet sheet = null;
			String sheetName = sheetAnnotation.name();
			if (sheetName.trim().length() > 0) {
				sheet = workbook.createSheet(sheetName);
			} else {
				sheet = workbook.createSheet();
			}
			LOG.debug("创建工作表:{}", sheet.getSheetName());
			Field[] fields = annotationClass.getDeclaredFields();
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
			ExcelColumn[] annotations = ArrayUtils.trim(annotationTempArray).toArray(new ExcelColumn[0]);
			Field[] annotationFields = ArrayUtils.trim(annotationFieldTempArray).toArray(new Field[0]);

			int rowIndex = 0;
			headerHandler.createRow(annotationClass, annotations, 0, sheet);
			if (data != null && !data.isEmpty()) {
				rowIndex++;
				for (T t : data) {
					Row dataRow = dataRowHandler.createRow(annotationClass, annotations, rowIndex, sheet);
					if (annotationFields.length == 0) {
						//类中的属性全都没标注ExcelColumn注解,则所有访问修饰符在protected以下的属性都将参与填充,此处需要过滤掉不符合要求的属性并且要过滤空值
						Field[] tempFields = new Field[fields.length];
						for (int i = 0; i < fields.length; i++) {
							if (Modifier.PROTECTED < fields[i].getModifiers()) {
								continue;
							}
							tempFields[i] = fields[i];
						}
						Field[] columnFields = ArrayUtils.trim(tempFields).toArray(new Field[0]);
						for (int i = 0; i < columnFields.length; i++) {
							columnFields[i].setAccessible(true);
							cellHandler.createCell(columnFields[i].get(t), i, dataRow, null, workbook);
						}
					} else {
						//只有标注了ExcelColumn注解的属性参与填充,由于已标注的属性集合下标和注解集合下标相同,所以可以直接用
						for (int i = 0; i < annotations.length; i++) {
							annotationFields[i].setAccessible(true);
							cellHandler.createCell(annotationFields[i].get(t), i, dataRow, annotations[i], workbook);
						}
					}
					rowIndex++;
				}
			}
		} else {
			String errMsg = "Please use annotation [ExcelSheet] on type [" + annotationClass + "]";
			LOG.error(errMsg, new IllegalStateException(errMsg));
			throw new Exception(errMsg);
		}
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
	/**
	 * @return the attachmentName
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * @param attachmentName the attachmentName to set
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	/**
	 * @return the charsetName
	 */
	public String getCharsetName() {
		return charsetName;
	}
	/**
	 * @param charsetName the charsetName to set
	 */
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}
	/**
	 * @return the annotationClass
	 */
	public Class<T> getAnnotationClass() {
		return annotationClass;
	}

	/**
	 * 设置表头处理器,默认实现: {@link ExcelHeaderRowHandlerImpl}
	 * @param headerHandler the headerHandler to set
	 */
	public void setHeaderHandler(ExcelRowHandler headerHandler) {
		this.headerHandler = headerHandler;
	}

	/**
	 * 设置数据行处理器,默认实现: {@link ExcelDataRowHandlerImpl}
	 * @param dataRowHandler the dataRowHandler to set
	 */
	public void setDataRowHandler(ExcelRowHandler dataRowHandler) {
		this.dataRowHandler = dataRowHandler;
	}

	/**
	 * 设置单元格处理器,默认实现: {@link ExcelCellHandlerImpl}
	 * @param cellHandler the cellHandler to set
	 */
	public void setCellHandler(ExcelCellHandler cellHandler) {
		this.cellHandler = cellHandler;
	}

}
