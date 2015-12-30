/**
 * 项目名: java-code-tutorials-websocket-spring
 * 包名:  net.fantesy84.sys.user.service
 * 文件名: IUserService.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月30日
 */
package net.fantesy84.sys.user.service;

import net.fantesy84.sys.domain.User;

/**
 * @author Andronicus
 * @since 2015年12月30日
 */
public interface IUserService {
	User save() throws Exception;
}
