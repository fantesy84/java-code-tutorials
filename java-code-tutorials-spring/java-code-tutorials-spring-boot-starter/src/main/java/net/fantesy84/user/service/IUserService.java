/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.user.service
 * 文件名: IUserService.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.user.service;

import net.fantesy84.user.domain.User;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
public interface IUserService {
	User login(User user) throws Exception;
}
