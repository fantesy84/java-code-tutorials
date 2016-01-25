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
public class Son implements Runnable {
	private BankAccount account;
	private Lock lock;
	private double out = 150.00;
	private int counter = 1;
	
	/**
	 * @param account
	 * @param lock
	 */
	public Son(BankAccount account, Lock lock) {
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
				account.setAmount(account.getAmount() - out);
				System.out.println("<<<<<<< 第"+ counter +"次,余额为: " + orgin + ",儿子取出"+ out +"元,账户余额为: " + account.getAmount());
				out += 50.00;
				counter += 1;
				lock.unlock();
				Thread.sleep(1000l);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
