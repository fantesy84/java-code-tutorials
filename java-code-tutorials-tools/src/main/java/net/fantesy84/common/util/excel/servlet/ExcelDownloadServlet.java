package net.fantesy84.common.util.excel.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class ExcelDownloadServlet
 */
@WebServlet("/fantesy84.net/excel/download")
public class ExcelDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ExcelDownloadServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("进入EXCEL文件下载控制器!");
		String contentType = (String)request.getAttribute("contentType");
		String encoding = request.getAttribute("encoding").toString();
		String tempFilePath = (String)request.getAttribute("tempFilePath");
		String tempAttachmentName = (String)request.getAttribute("attachmentName");
		String attachmentName = new String(tempAttachmentName.getBytes("UTF-8"), encoding);
		LOG.debug("内容类型:{},编码:{},附件名称:{},临时文件路径:{}", new Object[]{contentType,encoding,tempAttachmentName,tempFilePath});

		response.setContentType(contentType);
		response.setCharacterEncoding(encoding);
		response.addHeader("Content-Disposition", "attachment;filename=" + attachmentName);
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Prama", "no-cache");

		if (tempFilePath == null) {
			throw new IllegalStateException("临时文件不存在!无法进行下载");
		}
		/*
		 * 读取临时文件夹中生成的excel临时文件,并写入响应的字节流以供下载
		 */
		OutputStream out = null;
		InputStream in = null;
		try {
			File tempFile = new File(tempFilePath);
			out = response.getOutputStream();
			if (tempFile.exists() && tempFile.isFile()) {
				if (tempFile.canRead()) {
					response.setContentLength((int)tempFile.length());
					in = new FileInputStream(tempFile);
					int i;
					while ((i = in.read()) != -1) {
						out.write(i);
					}
				}
			}
		} catch (FileNotFoundException e) {
			String msg = "未找到文件:" + tempFilePath;
			LOG.error(msg, new IllegalStateException(e));
			try {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
			} catch (IOException e1) {
				LOG.error("与客户端连接断开!", new IllegalStateException(e1));
			}
		} catch (IOException e) {
			LOG.error("服务器IO错误!", new IllegalStateException(e));
		} finally {
			LOG.debug("下载控制器任务执行完毕!");
			if (in != null) {
				try {
					in.close();
					out.close();
				} catch (IOException e) {
					LOG.error("服务器IO错误!", new IllegalStateException(e));
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
