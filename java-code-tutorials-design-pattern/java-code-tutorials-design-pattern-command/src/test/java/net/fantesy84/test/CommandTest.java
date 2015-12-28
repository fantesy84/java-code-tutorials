/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.test
 * 文件名: CommandTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.test;

import org.junit.Test;

import net.fantesy84.command.ICommand;
import net.fantesy84.command.impl.RebuildProjectCode;
import net.fantesy84.invoker.Invoker;

import net.fantesy84.invoker.impl.ProjectManagerInvoker;
import net.fantesy84.receiver.ICommandProcesser;
import net.fantesy84.receiver.impl.ProjectCoder;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class CommandTest {
	@Test
	public void test1() throws Exception{
		ICommandProcesser porcesser = new ProjectCoder();
		ICommand<?> c = new RebuildProjectCode(porcesser);
		Invoker i = new ProjectManagerInvoker(c);
		i.action(porcesser);
	}
}
