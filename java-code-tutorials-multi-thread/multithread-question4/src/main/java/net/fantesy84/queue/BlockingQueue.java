/**
 * 项目名: multithread-question4
 * 包名:  net.fantesy84.queue
 * 文件名: BlockingQueue.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月27日
 */
package net.fantesy84.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Andronicus
 * @since 2016年1月27日
 */
public class BlockingQueue<T> {
	private List<T> queue = new LinkedList<>();
	private int capacity;
	/**
	 * 
	 */
	public BlockingQueue() {
		super();
	}
	/**
	 * @param initialCapacity
	 */
	public BlockingQueue(int initialCapacity) {
		super();
		this.capacity = initialCapacity;
	}
	
	public synchronized void enqueue(T item) throws InterruptedException {
		while (queue.size() == capacity) {
			wait();
		}
		if (queue.size() == 0) {
			notifyAll();
		}
		this.queue.add(item);
	}
	
	public synchronized T dequeue() throws InterruptedException {
		while (queue.size() == 0) {
			wait();
		}
		if (queue.size() == capacity) {
			notifyAll();
		}
		return queue.remove(0);
	}
	
	/**
	 * @return the queue
	 */
	public List<T> getQueue() {
		return queue;
	}
	
}
