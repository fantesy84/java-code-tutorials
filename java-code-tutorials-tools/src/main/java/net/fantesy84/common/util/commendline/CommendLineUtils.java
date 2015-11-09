/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util.commendline
 * 文件名: CommendLineUtils.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月9日
 */
package net.fantesy84.common.util.commendline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import net.fantesy84.common.util.SystemPropUtils;
import net.fantesy84.common.util.SystemPropUtils.PropKeys;

/**
 * @author Andronicus
 * @since 2015年11月9日
 */
public class CommendLineUtils {
	private final static String OS_WINDOWS = "Windows";
	private final static String OS_LINUX = "Linux";
	private final static String OS_UNIX = "Unix";
	
	public static String run(String[] commends, String charsetName){
		String osName = SystemPropUtils.getProperty(PropKeys.OS_NAME);
		String result = null;
		try {
			if (osName != null) {
				String[] commendArray = null;
				LinkedList<String> commendList = new LinkedList<String>();
				for (int i = 0; i < commends.length; i++) {
					commendList.addLast(commends[i]);
				}
				if (osName.startsWith(OS_WINDOWS)) {
					commendList.addFirst("/c");
					commendList.addFirst("cmd.exe");
				} else if (osName.startsWith(OS_LINUX)) {
					commendList.addFirst("-c");
					commendList.addFirst("/bin/sh");
				} else if (osName.startsWith(OS_UNIX)) {
					
				}
				commendArray = commendList.toArray(new String[commendList.size()]);
				Process process = Runtime.getRuntime().exec(commendArray);
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName(charsetName)));
				StringBuffer buffer = new StringBuffer();
				while (reader.readLine() != null) {
					buffer.append(reader.readLine()).append(SystemPropUtils.getProperty(PropKeys.LINE_SEPARATOR));
				}
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
