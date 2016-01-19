/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util.async
 * 文件名: AsyncSubject.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月18日
 */
package net.fantesy84.common.util.async;

import java.util.List;

/**
 * @author Andronicus
 * @param <A>
 * @since 2016年1月18日
 */
public interface AsyncSubject<V> {
	void regist(CallableTask<V> task);
	CallableTask<V> remove(CallableTask<V> task);
	<A> List<V> execute(String[] taskNames, A data, CompletionHandler<V, A> handler) throws Exception;
}
