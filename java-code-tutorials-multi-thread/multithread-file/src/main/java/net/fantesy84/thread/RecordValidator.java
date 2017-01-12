/**
 * Created: 2017-01-04
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.domain.RecordBean;
import net.fantesy84.domain.ValidationResult;

/**
 * @author junjie.ge
 *
 */
public class RecordValidator implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(RecordValidator.class);
	private RecordBean record;
	private BlockingQueue<ValidationResult> queue;
	private CountDownLatch latch;

	public RecordValidator(RecordBean record, BlockingQueue<ValidationResult> queue, CountDownLatch latch) {
		super();
		this.record = record;
		this.queue = queue;
		this.latch = latch;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		//LOG.debug("进入线程{},处理记录", Thread.currentThread().getName());
		try {
			Boolean success = Boolean.TRUE;
			// validate
			String name = record.getName();
			if (name == null) {
				success = Boolean.FALSE;
			}
			String type = record.getType();
			if (type == null) {
				success = Boolean.FALSE;
			}
			// 模拟耗时
			Thread.sleep(1850l);
			//LOG.debug("线程{}验证完成!", Thread.currentThread().getName());
			ValidationResult result = new ValidationResult();
			result.setIsSuccess(success);
			if (!success) {
				result.setMsg("ID为:"+record.getId()+"的记录验证未通过!");
			}
			queue.offer(result);
			latch.countDown();
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), new IllegalStateException(e));
		}
	}

}
