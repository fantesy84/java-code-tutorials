/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util.async
 * 文件名: CallableTask.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月18日
 */
package net.fantesy84.common.util.async;

import java.util.concurrent.Callable;

/**
 * @author Andronicus
 * @since 2016年1月18日
 */
public interface CallableTask<V> extends Callable<V> {
	String getName();
}
