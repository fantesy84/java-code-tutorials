/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.command
 * 文件名: ICommand.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.command;

import java.io.Serializable;

/**
 * 命令接口.
 * <P>该接口定义了命令的各种方法,用于给命令处理器使用
 * @author Andronicus
 * @since 2015年12月23日
 */
public interface ICommand<T extends Serializable> {
	T execute() throws Exception;
}
