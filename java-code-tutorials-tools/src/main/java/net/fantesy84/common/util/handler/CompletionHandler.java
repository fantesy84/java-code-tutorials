/**
 * 项目名: async-callback
 * 包名:  net.fantesy84.callback
 * 文件名: CompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月14日
 */
package net.fantesy84.common.util.handler;

/**
 * @author Andronicus
 * @since 2016年1月14日
 */
public interface CompletionHandler<V, A> {
	V completed(V value, A attachment) throws Exception;
	
	void failed(Throwable exc, A attachment);
}
