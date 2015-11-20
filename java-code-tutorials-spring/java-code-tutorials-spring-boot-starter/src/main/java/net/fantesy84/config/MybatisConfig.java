/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.app
 * 文件名: MybatisConfig.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
@Configuration
@EnableTransactionManagement
//@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@MapperScan(basePackages={"net.fantesy84.*.dao.mybatis"})
public class MybatisConfig implements EnvironmentAware{
	private final static Logger logger = LoggerFactory.getLogger(MybatisConfig.class);
	
	private RelaxedPropertyResolver mybatisProperties;
	
	@Autowired
	private DataSource dataSource;
	
	/* (non-Javadoc)
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(Environment environment) {
		this.mybatisProperties = new RelaxedPropertyResolver(environment, "mybatis.");
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(){
		SqlSessionFactory factory = null;
		try {
			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			factoryBean.setDataSource(dataSource);
			factoryBean.setTypeAliasesPackage(mybatisProperties.getProperty("typeAliasesPackage"));
			factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(mybatisProperties.getProperty("configLocation")));
			factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatisProperties.getProperty("mapperLocations")));
			return factoryBean.getObject();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return factory;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource);
	}
}
