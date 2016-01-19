/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.common.util.async
 * 文件名: AbstractAsyncSubject.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月18日
 */
package net.fantesy84.common.util.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Andronicus
 * @since 2016年1月18日
 */
public abstract class AbstractAsyncSubject<V> implements AsyncSubject<V> {
	private static final int DEFAULT_THREAD_NUMBER = 15;
	private int asyncPoolThreadNumber = DEFAULT_THREAD_NUMBER;
	private long delay = 1;
	private ScheduledExecutorService schedulerService;
	private CompletionService<V> completionService;
	private Map<String, CallableTask<V>> taskMap;
	/**
	 * 
	 */
	public AbstractAsyncSubject() {
		schedulerService = Executors.newScheduledThreadPool(asyncPoolThreadNumber >> 2);
		completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(asyncPoolThreadNumber));
		taskMap = new HashMap<>();
	}
	/**
	 * @param completionService
	 */
	public AbstractAsyncSubject(CompletionService<V> completionService) {
		this.completionService = completionService;
		schedulerService = Executors.newScheduledThreadPool(asyncPoolThreadNumber >> 2);
		taskMap = new HashMap<>();
	}
	/* (non-Javadoc)
	 * @see net.fantesy84.common.util.async.AsyncSubject#regist(net.fantesy84.common.util.async.CallableTask)
	 */
	@Override
	public void regist(CallableTask<V> task) {
		taskMap.put(task.getName(), task);
	}
	/* (non-Javadoc)
	 * @see net.fantesy84.common.util.async.AsyncSubject#remove(net.fantesy84.common.util.async.CallableTask)
	 */
	@Override
	public CallableTask<V> remove(CallableTask<V> task) {
		return taskMap.remove(task.getName());
	}
	/* (non-Javadoc)
	 * @see net.fantesy84.common.util.async.AsyncSubject#execute(java.lang.String[], java.lang.Object, net.fantesy84.common.util.async.CompletionHandler)
	 */
	@Override
	public <A> List<V> execute(String[] taskNames, A data, CompletionHandler<V, A> handler) throws Exception {
		Collection<Future<V>> c = new HashSet<>(0);
		synchronized (this) {
			for (int i = 0; i < taskNames.length; i++) {
				String name = taskNames[i];
				if (taskMap.containsKey(name)) {
					c.add(completionService.submit(taskMap.get(name)));
				}
			}
		}
		final ArrayBlockingQueue<Future<V>> fs = new ArrayBlockingQueue<>(c.size(), false, c);
		ScheduledFuture<Set<Future<V>>> scanSchedule = schedulerService.schedule(new Callable<Set<Future<V>>>(){

			@Override
			public Set<Future<V>> call() throws Exception {
				Set<Future<V>> finished = new HashSet<>(0);
				System.out.println("scanning async result");
				while (!fs.isEmpty()) {
					for (Future<V> f : fs) {
						if (f.isDone()) {
							finished.add(f);
						}
					}
				}
				return finished;
			}
			
		}, delay, TimeUnit.SECONDS);
		Set<Future<V>> futures = scanSchedule.get();
		List<V> results = new ArrayList<>(0);
		for (Future<V> f : futures) {
			V ele = null;
			try {
				ele = handler.completed(f, data);
			} catch (Exception e) {
				handler.failed(e, data);
			}
			results.add(ele);
		}
		return results;
	}
	
	/**
	 * @return the asyncPoolThreadNumber
	 */
	public int getAsyncPoolThreadNumber() {
		return asyncPoolThreadNumber;
	}
	/**
	 * @param asyncPoolThreadNumber the asyncPoolThreadNumber to set
	 */
	public void setAsyncPoolThreadNumber(int asyncPoolThreadNumber) {
		this.asyncPoolThreadNumber = asyncPoolThreadNumber;
	}
	/**
	 * @return the delay
	 */
	public long getDelay() {
		return delay;
	}
	/**
	 * TimeUnit: Seconds
	 * @param delay the delay to set
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
}
