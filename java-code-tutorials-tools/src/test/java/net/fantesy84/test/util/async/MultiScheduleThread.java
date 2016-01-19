/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.test.util.async
 * 文件名: MultiScheduleThread.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月19日
 */
package net.fantesy84.test.util.async;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Andronicus
 * @since 2016年1月19日
 */
public class MultiScheduleThread {
	private static final int THREAD_NUMBER = 10;
	private ScheduledExecutorService schedulerService;
	private int coreThread = THREAD_NUMBER;
	private ArrayBlockingQueue<Entity> queue = new ArrayBlockingQueue<>(5, false);
	/**
	 * 
	 */
	public MultiScheduleThread() {
		super();
		schedulerService = Executors.newScheduledThreadPool(coreThread);
		queue.add(new Entity("aaaa"));
		queue.add(new Entity("bbbb"));
		queue.add(new Entity("cccc"));
		queue.add(new Entity("dddd"));
		queue.add(new Entity("eeee"));
	}
	
	public String run() throws Exception {
		final List<String> list = new ArrayList<>();
		ScheduledFuture<?> schedule = schedulerService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				Iterator<Entity> itt = queue.iterator();
				System.out.println("Scanning...");
				while (itt.hasNext()) {
					Entity e = itt.next();
					if (e.isDone()) {
						System.out.println("Has done!");
						itt.remove();
						list.add(e.getStr());
					}
				}
			}
		}, 1l, 3l, TimeUnit.SECONDS);
		schedule.get();
		return list.toString();
	}
	
	public void change() throws Exception {
		Thread.sleep(5000l);
		System.out.println("Input new Entity");
		Entity entity = new Entity("ffff");
		entity.setDone(true);
		queue.add(entity);
	}
	public static void main(String[] args) throws Exception {
		MultiScheduleThread tc = new MultiScheduleThread();
		tc.run();
		tc.change();
	}
}
