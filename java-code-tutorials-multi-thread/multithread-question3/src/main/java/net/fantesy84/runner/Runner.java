/**
 * 项目名: multithread-question3
 * 包名:  net.fantesy84.runner
 * 文件名: Runner.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月26日
 */
package net.fantesy84.runner;

import net.fantesy84.thread.WaitExample_1;
import net.fantesy84.thread.WaitExample_2;

/**
 * @author Andronicus
 * @since 2016年1月26日
 */
public class Runner {
	public static void main(String[] args) {
		Integer counter = 0;
		new Thread(new WaitExample_1(counter)).start();
		new Thread(new WaitExample_2(counter)).start();
	}
}
