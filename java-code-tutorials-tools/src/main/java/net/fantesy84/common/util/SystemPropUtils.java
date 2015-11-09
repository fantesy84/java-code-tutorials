/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util
 * 文件名: SystemPropUtils.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月9日
 */
package net.fantesy84.common.util;

/**
 * @author Andronicus
 * @since 2015年11月9日
 */
public class SystemPropUtils {
	
	public static String getProperty(PropKeys key) {
		return System.getProperty(key.getValue());
	}
	
	public enum PropKeys {
		/**
		 * Java 运行时环境版本
		 */
		JAVA_VERSION("java.version"),
		/**
		 * Java 运行时环境供应商
		 */
		JAVA_VENDOR("java.vendor"),
		/**
		 * Java 供应商的 URL
		 */
		JAVA_VENDOR_URL("java.vendor.url"),
		/**
		 * Java 安装目录
		 */
		JAVA_HOME("java.home"),
		/**
		 * Java 虚拟机规范版本
		 */
		JAVA_VM_SPECIFICATION_VERSION("java.vm.specification.version"),
		/**
		 * Java 虚拟机规范供应商
		 */
		JAVA_VM_SPECIFICATION_VENDOR("java.vm.specification.vendor"),
		/**
		 * Java 虚拟机规范名称
		 */
		JAVA_VM_SPECIFICATION_NAME("java.vm.specification.name"),
		/**
		 * Java 虚拟机实现版本
		 */
		JAVA_VM_VERSION("java.vm.version"),
		/**
		 * Java 虚拟机实现供应商
		 */
		JAVA_VM_VENDOR("java.vm.vendor"),
		/**
		 * Java 虚拟机实现名称
		 */
		JAVA_VM_NAME("java.vm.name"),
		/**
		 * Java 运行时环境规范版本
		 */
		JAVA_SPECIFICATION_VERSION("java.specification.version"),
		/**
		 * Java 运行时环境规范供应商
		 */
		JAVA_SPECIFICATION_VENDOR("java.specification.vendor"),
		/**
		 * Java 运行时环境规范名称
		 */
		JAVA_SPECIFICATION_NAME("java.specification.name"),
		/**
		 * Java 类格式版本号
		 */
		JAVA_CLASS_VERSION("java.class.version"),
		/**
		 * Java 类路径
		 */
		JAVA_CLASS_PATH("java.class.path"),
		/**
		 * 加载库时搜索的路径列表
		 */
		JAVA_LIBRARY_PATH("java.library.path"),
		/**
		 * 默认的临时文件路径
		 */
		JAVA_IO_TMPDIR("java.io.tmpdir"),
		/**
		 * 要使用的 JIT 编译器的名称
		 */
		JAVA_COMPILER("java.compiler"),
		/**
		 * 一个或多个扩展目录的路径
		 */
		JAVA_EXT_DIRS("java.ext.dirs"),
		/**
		 * 操作系统的名称
		 */
		OS_NAME("os.name"),
		/**
		 * 操作系统的架构
		 */
		OS_ARCH("os.arch"),
		/**
		 * 操作系统的版本
		 */
		OS_VERSION("os.version"),
		/**
		 * 文件分隔符(在 UNIX 系统中是"<tt>/</tt>",Windows系统中是"<tt>\</tt>")
		 */
		FILE_SEPARATOR("file.separator"),
		/**
		 * 路径分隔符(在 UNIX 系统中是"<tt>:</tt>")
		 */
		PATH_SEPARATOR("path.separator"),
		/**
		 * 行分隔符(在 UNIX 系统中是"<tt>/n</tt>",Windows系统是"<tt>/rn</tt>")
		 */
		LINE_SEPARATOR("line.separator"),
		/**
		 * 用户的账户名称
		 */
		USER_NAME("user.name"),
		/**
		 * 用户的主目录
		 */
		USER_HOME("user.home"),
		/**
		 * 用户的当前工作目录
		 */
		USER_DIR("user.dir");

		private String key;
		
		private PropKeys(String name) {
			this.key = name;
		}
		
		public String getValue(){
			return key;
		}
	}
}
