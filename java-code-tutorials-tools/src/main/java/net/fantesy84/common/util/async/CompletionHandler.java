/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util.async
 * 文件名: CompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月18日
 */
package net.fantesy84.common.util.async;

import java.util.concurrent.Future;

/**
 * @author Andronicus
 * @since 2016年1月18日
 */
public interface CompletionHandler<V, A> {
	V completed(Future<V> future, A attachment) throws Exception;
	void failed(Exception e, A attachment);
}
