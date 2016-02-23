/**
 * 项目名: java-code-tutorials-spring-redis-web
 * 包名:  net.fantesy84.user.service
 * 文件名: IUserBaseService.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年2月23日
 */
package net.fantesy84.user.service;

import java.util.List;

import net.fantesy84.user.domain.UserBase;

/**
 * @author Andronicus
 * @since 2016年2月23日
 */
public interface IUserBaseService {
	Boolean save(UserBase base) throws Exception;
	UserBase update(UserBase raw) throws Exception;
	List<UserBase> findAll() throws Exception;
}
