/**
 * 项目名: java-code-tutorials-design-pattern-bridge
 * 包名:  net.fantesy84.bridge
 * 文件名: DriverManager.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.bridge;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class DriverManager {
	private static Bridge bridge;
	
	public static void loadDriver(String className){
		try {
			Class<?> clazz = Class.forName(className);
			bridge = (Bridge) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public static void getConnection(){
		bridge.createConnection();
	}
}
