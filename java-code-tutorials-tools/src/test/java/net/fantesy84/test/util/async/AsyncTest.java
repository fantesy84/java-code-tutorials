/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.test.util.async
 * 文件名: AsyncTest.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月19日
 */
package net.fantesy84.test.util.async;

import java.util.List;
import java.util.concurrent.Future;

import org.junit.Test;

import net.fantesy84.common.util.async.AbstractAsyncSubject;
import net.fantesy84.common.util.async.AsyncSubject;
import net.fantesy84.common.util.async.CallableTask;
import net.fantesy84.common.util.async.CompletionHandler;

/**
 * @author Andronicus
 * @since 2016年1月19日
 */
public class AsyncTest {
	@Test
	public void test1() throws Exception{
		AsyncSubject<String> subject = new StringAsyncSubjectImpl();
		CallableTask<String> task = new StringCallableTaskImpl();
		subject.regist(task);
		AfterCalledTaskHandler handler = new AfterCalledTaskHandler();
		List<String> result = subject.execute(new String[]{task.getName()}, subject, handler);
		for (String s : result) {
			System.out.println(s);
		}
	}
	private class StringAsyncSubjectImpl extends AbstractAsyncSubject<String> {
		@SuppressWarnings("unused")
		private String className;

		/**
		 * 
		 */
		public StringAsyncSubjectImpl() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	}
	private class StringCallableTaskImpl implements CallableTask<String> {

		/* (non-Javadoc)
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public String call() throws Exception {
			String str = "Hello! This is Andronicus.Ge calling.";
			System.out.println("StringCallableTaskImpl#call print: " + str);
			Thread.sleep(3000l);
			return str;
		}

		/* (non-Javadoc)
		 * @see net.fantesy84.common.util.async.CallableTask#getName()
		 */
		@Override
		public String getName() {
			return this.getClass().getName();
		}
		
	}
	private class AfterCalledTaskHandler implements CompletionHandler<String, AsyncSubject<String>> {

		/* (non-Javadoc)
		 * @see net.fantesy84.common.util.async.CompletionHandler#completed(java.util.concurrent.Future, java.lang.Object)
		 */
		@Override
		public String completed(Future<String> future, AsyncSubject<String> attachment) throws Exception {
			String str = future.get();
			str = str.substring(0, str.indexOf("."));
			System.out.println("AfterCalledTaskHandler#completed: " + str);
			return str;
		}

		/* (non-Javadoc)
		 * @see net.fantesy84.common.util.async.CompletionHandler#failed(java.lang.Exception, java.lang.Object)
		 */
		@Override
		public void failed(Exception e, AsyncSubject<String> attachment) {
			System.err.println(e.getMessage());
		}
		
	}
}
