/**
 * Created: 2017-01-04
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import net.fantesy84.domain.RecordBean;
import net.fantesy84.domain.ValidationResult;
import net.fantesy84.thread.RecordValidator;

/**
 * @author junjie.ge
 *
 */
public class ValidationTest {
	private AtomicInteger ai;

	public ValidationTest() {
		this.ai = new AtomicInteger(1);
	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(200);
		BlockingQueue<ValidationResult> queue = new ArrayBlockingQueue<>(50000);
		ValidationTest tc = new ValidationTest();
		int size = 3000;
		List<RecordBean> records = tc.createData(size);
		Date begin = new Date();
		CountDownLatch latch = new CountDownLatch(size);
		for (RecordBean r : records) {
			es.execute(new RecordValidator(r, queue, latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Date end = new Date();
		System.out.println(size + "条数据验证完毕共消耗:" + (end.getTime()-begin.getTime()) + "ms");
		while (!queue.isEmpty()) {
			ValidationResult result = queue.poll();
			if (!result.getIsSuccess()) {
				System.err.println("记录未通过验证!" + result.getMsg());
			}
		}
		es.shutdown();
	}

	private List<RecordBean> createData(int size) {
		List<RecordBean> records = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			RecordBean record = new RecordBean();
			record.setId(ai.getAndIncrement());
			record.setName("name" + i);
			record.setType("type" + i);
			if (i % 100 == 0) {
				record.setName(null);
				record.setType(null);
			}
			records.add(record);
		}
		return records;
	}
}
