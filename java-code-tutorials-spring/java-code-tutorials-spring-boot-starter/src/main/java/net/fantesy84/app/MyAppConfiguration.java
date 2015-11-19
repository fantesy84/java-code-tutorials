/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.app
 * 文件名: MyAppConfiguration.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
//@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages={"net.fantesy84.*.dao","net.fantesy84.*.service","net.fantesy84.*.controller"})
public class MyAppConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(MyAppConfiguration.class, args);
	}
}
