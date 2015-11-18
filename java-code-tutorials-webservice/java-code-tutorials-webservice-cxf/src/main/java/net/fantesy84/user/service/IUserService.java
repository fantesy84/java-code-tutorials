/**
 * 项目名: java-tutorials-webservice-cxf-restful
 * 包名:  net.fantesy84.user.service
 * 文件名: IUserService.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月4日
 */
package net.fantesy84.user.service;

import java.io.Serializable;
import java.util.List;

import net.fantesy84.user.domain.User;

/**
 * @author Andronicus
 * @since 2015年11月4日
 */
public interface IUserService {
	Serializable save(User user) throws Exception;
	User getById(Integer id) throws Exception;
	List<User> findAll() throws Exception;
}
