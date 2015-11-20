/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.config
 * 文件名: AtomikosDataSourceConfig.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class AtomikosDataSourceConfig {
	
	@Bean(name="masterDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource masterDataSource(){
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl("jdbc:mysql://localhost:3306/GOLD_BOSS_SYS?characterEncoding=utf-8");
		mysqlXADataSource.setUser("root");
		mysqlXADataSource.setPassword("root");
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		
		AtomikosDataSourceBean xaDataSource_master = new AtomikosDataSourceBean();
		xaDataSource_master.setXaDataSource(mysqlXADataSource);
		xaDataSource_master.setUniqueResourceName("db/goldboss/master");
		return xaDataSource_master;
	}
	
	@Bean(name="slaverCrmDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource slaverCrmDataSource(){
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl("jdbc:mysql://localhost:3306/GOLD_BOSS_CRM?characterEncoding=utf-8");
		mysqlXADataSource.setUser("root");
		mysqlXADataSource.setPassword("root");
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		
		AtomikosDataSourceBean xaDataSource_slaver_crm = new AtomikosDataSourceBean();
		xaDataSource_slaver_crm.setXaDataSource(mysqlXADataSource);
		xaDataSource_slaver_crm.setUniqueResourceName("db/goldboss/slaver_crm");
		return xaDataSource_slaver_crm;
	}
}
