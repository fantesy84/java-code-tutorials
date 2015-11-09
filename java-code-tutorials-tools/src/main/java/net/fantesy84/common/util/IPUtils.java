/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util
 * 文件名: IPUtils.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月9日
 */
package net.fantesy84.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2015年11月9日
 */
public class IPUtils {
	private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);
	private static String LOCALHOST_NAME;
	
	static {
		try {
			LOCALHOST_NAME = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
	}
	
	/**
	 * 获取本地网络适配器IP地址
	 * <P>有多个网卡时会有多个IP,一一对应
	 * @return 网卡地址列表
	 */
	public static String[] getLocalIPs(){
		String[] adds = null;
		try {
			InetAddress[] addresses = InetAddress.getAllByName(LOCALHOST_NAME);
			if (CollectionUtils.notNullAndEmptyArray(addresses)) {
				List<String> temps = new ArrayList<String>(0);
				for (int i = 0; i < addresses.length; i++) {
					if (addresses[i].isSiteLocalAddress()){
						temps.add(addresses[i].getHostAddress());
					}
				}
				adds = temps.toArray(new String[temps.size()]);
			}
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return adds;
	}
	
	/**
	 * 获取远程访问者IP地址
	 * <P> 关于代理请求,通过代理过滤进行查询.有多层代理时,第一个IP地址即为真实地址.
	 * @param request 网络请求
	 * @return 远程访问者IP地址
	 */
	public static String getRemoteIP(HttpServletRequest request){
		String ip = null;
		ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				logger.error(e.getMessage(), new IllegalStateException(e));
			}
		}
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		if (ip == null) {
			logger.error("未获取到任何IP地址!请检查网络配置!", new IllegalStateException());
		}
		return ip;
	}
}
