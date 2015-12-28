/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.command
 * 文件名: AbstractCommand.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.command;

import java.io.Serializable;

import net.fantesy84.receiver.ICommandProcesser;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public abstract class AbstractCommand<T extends Serializable> implements ICommand<T>, Serializable {
	private static final long serialVersionUID = -6528126125421304667L;
	private String commandInvokerName;
	
	private ICommandProcesser processer;
	
	/**
	 * @param processer
	 */
	protected AbstractCommand(ICommandProcesser processer) {
		super();
		this.processer = processer;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.command.ICommand#execute()
	 */
	@Override
	public T execute() throws Exception{
		T result = commandResultInitialization();
		processer.process(result);
		return result;
	}
	
	protected abstract T commandResultInitialization() throws Exception;
	
	protected void setProcesser(ICommandProcesser processer) {
		this.processer = processer;
	}

	/**
	 * @return the commandInvokerName
	 */
	public String getCommandInvokerName() {
		return commandInvokerName;
	}

	/**
	 * @param commandInvokerName the commandInvokerName to set
	 */
	public void setCommandInvokerName(String commandInvokerName) {
		this.commandInvokerName = commandInvokerName;
	}
}
