/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.receiver
 * 文件名: IResultHandler.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.receiver;

/**
 * 命令处理器.
 * <P>处理器不关心谁发出的命令,只关心怎么处理命令.该接口的方法针对命令接口进行相关操作.
 * @author Andronicus
 * @since 2015年12月23日
 */
public interface ICommandProcesser {
	void process(Object obj) throws Exception;
}
