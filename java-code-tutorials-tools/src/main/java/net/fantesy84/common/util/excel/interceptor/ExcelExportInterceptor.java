package net.fantesy84.common.util.excel.interceptor;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.fantesy84.common.util.excel.annotations.ExcelExport;
import net.fantesy84.common.util.excel.core.ExcelBuilder;
import net.fantesy84.common.util.excel.domain.ExcelEntity;


public class ExcelExportInterceptor implements HandlerInterceptor {
	private static final Logger LOG = LoggerFactory.getLogger(ExcelExportInterceptor.class);
	public static final String EXCEL_ENTITY_KEY = "_excel";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.debug("**********************************************************************************");
		LOG.debug("Welcome to simple excel export tools!");
		//TODO 初始化POI的一些对象,例如默认的样式等
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ExcelExport exportAnn = handlerMethod.getMethodAnnotation(ExcelExport.class);
		if (exportAnn != null) {
			ExcelEntity<?> excelEntity = (ExcelEntity<?>) request.getAttribute(EXCEL_ENTITY_KEY);
			/*
			 * default name is export-execel
			 * When use XSSF the suffix is xlsx
			 * use UrlEncode format name will be better
			 */
			String attachmentName = exportAnn.attachmentName() + ".xlsx";
			LOG.debug("Method [{}] will create outPutStream for excel file named [{}]", new Object[]{handlerMethod.getMethod().getName(), attachmentName});
			String charsetName = exportAnn.charsetName();
			String contentType = exportAnn.contentType();
			/*
			 * default charset is ISO-8859-1,default content type is application/octet-stream
			 */
			request.setAttribute("contentType", contentType);
			request.setAttribute("encoding", charsetName);
			request.setAttribute("attachmentName", attachmentName);
			//完成数据填充,生成excel表
			String tempName = "excel-builder_" + UUID.randomUUID().toString() + ".xlsx.tmp";
			String tempFilePath = ExcelBuilder.getInstance().createFromEntity(excelEntity, System.getProperty("java.io.tmpdir"), tempName);
			request.setAttribute("tempFilePath", tempFilePath);
			request.getRequestDispatcher("/fantesy84.net/excel/download").forward(request, response);
		} else {
			LOG.debug("Not found annotation [ExcelExport] on method [{}], skip interceptor", handlerMethod.getMethod().getName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOG.debug("Excel export stream completed! See you next time.");
		Timer timer = new Timer(true);
		// 3分钟后删除临时文件
		timer.schedule(new TempFileCleanTask((String)request.getAttribute("tempFilePath")), 3*60*1000);
		LOG.debug("**********************************************************************************");
	}

	private class TempFileCleanTask extends TimerTask {
		private final Logger LOG = LoggerFactory.getLogger(TempFileCleanTask.class);
		private String tempFileName;

		public TempFileCleanTask(String tempFileName) {
			super();
			this.tempFileName = tempFileName;
		}


		@Override
		public void run() {
			File delFile = new File(tempFileName);
			if (delFile.exists()) {
				if (delFile.canWrite()) {
					LOG.debug("下载完成后,将立即删除文件:{}", tempFileName);
					boolean hasDelete = delFile.delete();
					if (!hasDelete) {
						LOG.warn("临时文件{}删除失败,将在退出java虚拟机时删除该文件!", tempFileName);
						delFile.deleteOnExit();
					}
				} else {
					LOG.debug("临时文件{}被占用,将在退出java虚拟机时删除该文件!", tempFileName);
					delFile.deleteOnExit();
				}
			}
		}

	}
}
