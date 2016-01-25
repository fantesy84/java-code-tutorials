/**
 * Project multithread-question2
 * File: BankAccountHistory.java
 * CreateTime: 2016年1月26日
 * Creator: junjie.ge
 * copy right ©2016 葛俊杰
 */
package net.fantesy84.runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.fantesy84.action.Father;
import net.fantesy84.action.Son;
import net.fantesy84.domain.BankAccount;

/**
 * TypeName: BankAccountHistory
 * <P>TODO
 * 
 * <P>CreateTime: 2016年1月26日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class BankAccountHistory {
	public static void main(String[] args) {
		BankAccount account = new BankAccount();
		account.setAmount(500.0);
		Lock lock = new ReentrantLock();
		ExecutorService pool = Executors.newCachedThreadPool();
		Father father = new Father(account, lock);
		Son son = new Son(account, lock);
		pool.execute(father);
		pool.execute(son);
	}
}
