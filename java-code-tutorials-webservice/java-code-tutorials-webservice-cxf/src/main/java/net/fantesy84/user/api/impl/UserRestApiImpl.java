/**
 * 项目名: java-tutorials-webservice-cxf-restful
 * 包名:  net.fantesy84.user.api.impl
 * 文件名: UserAPIImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月4日
 */
package net.fantesy84.user.api.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import net.fantesy84.user.api.IUserRestApi;
import net.fantesy84.user.domain.User;
import net.fantesy84.user.service.IUserService;

/**
 * @author Andronicus
 * @since 2015年11月4日
 */
public class UserRestApiImpl implements IUserRestApi {
	private static final Logger log = LoggerFactory.getLogger(UserRestApiImpl.class);
	@Autowired
	private IUserService userService;
	
	@Override
	public User getById(Integer id) {
		User user = null;
		try {
			user = userService.getById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> users = null;
		try {
			users = userService.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		}
		return users;
	}

	@Override
	public User add(User user) {
		User result = null;
		try {
			Serializable id = userService.save(user);
			if (id != null) {
				user.setId((Integer)id);
				result = user;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		}
		return result;
	}

}
