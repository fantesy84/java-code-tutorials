/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.invoker
 * 文件名: Invoker.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.invoker;

import net.fantesy84.receiver.ICommandProcesser;

/**
 * 命令调用者.
 * <P>命令调用者只有一个方法action,该方法的实现必须返回执行什么命令
 * @author Andronicus
 * @since 2015年12月23日
 */
public interface Invoker {
	void action(ICommandProcesser processer);
}
