/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.config
 * 文件名: AtomikosDataSourceConfig.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.J2eeUserTransaction;
import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class JtaTransactionManagerConfig {
	
	@Bean(name="userTransactionManager",initMethod="init",destroyMethod="close")
	public UserTransactionManager userTransactionManager() throws Exception{
		UserTransactionManager userTM = new UserTransactionManager();
		userTM.setForceShutdown(true);
		return userTM;
	}
	
	@Bean(name="atomikosUserTransaction")
	public J2eeUserTransaction atomikosUserTransaction() throws Exception{
		J2eeUserTransaction ut = new J2eeUserTransaction();
		ut.setTransactionTimeout(120);
		return ut;
	}
	
	@Bean(name="transactionManager")
	@DependsOn({"userTransactionManager","atomikosUserTransaction"})
	public PlatformTransactionManager transactionManager() throws Exception{
		JtaTransactionManager jtaTM = new JtaTransactionManager(atomikosUserTransaction(), userTransactionManager());
		jtaTM.setAllowCustomIsolationLevels(true);
		return jtaTM;
	}
	
	
}
