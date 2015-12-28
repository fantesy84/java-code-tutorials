/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.command.impl
 * 文件名: RebuildProjectCode.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.command.impl;

import net.fantesy84.command.AbstractCommand;
import net.fantesy84.receiver.ICommandProcesser;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class RebuildProjectCode extends AbstractCommand<String> {
	/**
	 * @param processer
	 */
	public RebuildProjectCode(ICommandProcesser processer) {
		super(processer);
	}

	private static final long serialVersionUID = 810437335517286563L;

	/* (non-Javadoc)
	 * @see net.fantesy84.command.AbstractCommand#commandResultInitialization()
	 */
	@Override
	protected String commandResultInitialization() throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("重构").append("所有模块");
		return builder.toString();
	}

}
