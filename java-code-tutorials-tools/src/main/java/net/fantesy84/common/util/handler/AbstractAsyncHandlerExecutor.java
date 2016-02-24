/**
 * 项目名: async-callback
 * 包名:  net.fantesy84.callback.impl
 * 文件名: AbstractHandlerExecutorImpl.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月14日
 */
package net.fantesy84.common.util.handler;

import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @param <V>
 * @since 2016年1月14日
 */
public abstract class AbstractAsyncHandlerExecutor<V> implements HandlerExecutor<V> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractAsyncHandlerExecutor.class);
	private Callable<V> call;
	private CompletionService<V> completionService;
	
	/**
	 * 
	 */
	public AbstractAsyncHandlerExecutor() {
		super();
	}

	/**
	 * @param completionService
	 */
	public AbstractAsyncHandlerExecutor(CompletionService<V> completionService) {
		super();
		this.completionService = completionService;
	}

	/**
	 * @param call
	 * @param completionService
	 */
	public AbstractAsyncHandlerExecutor(Callable<V> call, CompletionService<V> completionService) {
		super();
		this.call = call;
		this.completionService = completionService;
	}
	
	protected <A> A attachmentProcess(A attachment) {
		return attachment;
	}
	
	/* (non-Javadoc)
	 * @see net.fantesy84.callback.HandlerExecutor#execute(java.lang.Object, java.lang.Object)
	 */
	@Override
	public <A> V execute(V value, A attachment) throws Exception {
		V result = null;
		if (call != null) {
			completionService.submit(call);
			result = completionService.take().get();
		}
		logger.debug("AsynchrounsExecutor finished! The result is: {}", result);
		return result;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.callback.HandlerExecutor#execute(java.lang.Object, java.lang.Object, long, java.util.concurrent.TimeUnit, net.fantesy84.callback.CompletionHandler)
	 */
	@Override
	public <A> V execute(V value, A attachment, CompletionHandler<V, A> handler) throws Exception {
		logger.debug("====================== Method 'execute' with CompletionHandler:[{}] begin ======================", handler.getClass());
		V result = null;
		try {
			if (call != null) {
				if (attachment != null) {
					@SuppressWarnings("rawtypes")
					Class<? extends Callable> clazz = call.getClass();
					Field[] fields = clazz.getDeclaredFields();
					for (Field f : fields) {
						if (f.getType() == attachment.getClass() || f.getType() == attachment.getClass().getSuperclass()) {
							f.setAccessible(true);
							f.set(call, attachment);
						}
					}
				}
				completionService.submit(call);
				result = completionService.take().get();
			}
			logger.debug("AsynchrounsExecutor finished! The result is: {}", result);
			attachment = attachmentProcess(attachment);
			if (handler != null) {
				if (value == null && result == null) {
					result = handler.completed(value, attachment);
				} else {
					result = handler.completed(result, attachment);
				}
			}
		} catch (Throwable exc) {
			handler.failed(exc, attachment);
			throw new IllegalStateException(exc);
		}
		logger.debug("====================== Method 'execute' end ======================");
		return result;
	}

	/**
	 * @return the call
	 */
	public Callable<V> getCall() {
		return call;
	}

	/**
	 * @param call the call to set
	 */
	public void setCall(Callable<V> call) {
		this.call = call;
	}

	/**
	 * @return the completionService
	 */
	public CompletionService<V> getCompletionService() {
		return completionService;
	}

	/**
	 * @param completionService the completionService to set
	 */
	public void setCompletionService(CompletionService<V> completionService) {
		this.completionService = completionService;
	}
	
}
