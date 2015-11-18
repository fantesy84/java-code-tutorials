/**
 * 项目名: java-tutorials-webservice-cxf-restful
 * 包名:  net.fantesy84.user.service.impl
 * 文件名: UserServiceImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月4日
 */
package net.fantesy84.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import net.fantesy84.user.domain.User;
import net.fantesy84.user.service.IUserService;

/**
 * @author Andronicus
 * @since 2015年11月4日
 */
@Service
public class UserServiceImpl implements IUserService {
	private static Map<Integer, User> userMap;
	private static AtomicInteger atomic;
	
	static {
		userMap = new HashMap<Integer, User>(0);
		atomic = new AtomicInteger();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setName("姓名 - " + atomic.incrementAndGet());
			user.setId(atomic.get());
			user.setSex("男");
			userMap.put(atomic.get(), user);
		}
	}
	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#save(net.fantesy84.user.domain.User)
	 */
	@Override
	public Serializable save(User user) throws Exception {
		user.setId(atomic.incrementAndGet());
		userMap.put(atomic.get(), user);
		return atomic.get();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#getById(java.lang.Integer)
	 */
	@Override
	public User getById(Integer id) throws Exception {
		return userMap.get(id);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#findAll()
	 */
	@Override
	public List<User> findAll() throws Exception {
		List<User> users = new ArrayList<User>(0);
		for (Integer i : userMap.keySet()) {
			User temp = userMap.get(i);
			users.add(temp);
		}
		return users;
	}

}
