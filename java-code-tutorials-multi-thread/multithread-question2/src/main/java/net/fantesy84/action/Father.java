/**
 * Project multithread-question2
 * File: Father.java
 * CreateTime: 2016年1月26日
 * Creator: junjie.ge
 * copy right ©2016 葛俊杰
 */
package net.fantesy84.action;

import java.util.concurrent.locks.Lock;

import net.fantesy84.domain.BankAccount;

/**
 * TypeName: Father
 * <P>TODO
 * 
 * <P>CreateTime: 2016年1月26日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class Father implements Runnable {
	private BankAccount account;
	private Lock lock;
	
	/**
	 * @param account
	 * @param lock
	 */
	public Father(BankAccount account, Lock lock) {
		super();
		this.account = account;
		this.lock = lock;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (true) {
				lock.lock();
				Double orgin = account.getAmount();
				account.setAmount(account.getAmount() + 500.00);
				System.out.println(">>>>>>> 余额为: " + orgin + ",父亲存入500元,账户余额为: " + account.getAmount());
				lock.unlock();
				Thread.sleep(3*1000l);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
