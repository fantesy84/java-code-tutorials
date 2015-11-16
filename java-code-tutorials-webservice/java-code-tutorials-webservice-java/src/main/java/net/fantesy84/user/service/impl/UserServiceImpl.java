/**
 * Project java-code-tutorials-webservice-java
 * File: UserServiceImpl.java
 * CreateTime: 2015年11月16日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import net.fantesy84.user.domain.User;
import net.fantesy84.user.service.IUserService;

/**
 * TypeName: UserServiceImpl
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月16日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class UserServiceImpl implements IUserService {
	private static Map<Integer, User> userMap;
	private static AtomicInteger ai;
	
	static {
		userMap = new HashMap<Integer, User>(0);
		ai = new AtomicInteger();
		for (int i = 0; i < 10; i++) {
			Integer num = ai.incrementAndGet();
			User u = new User();
			u.setId(num);
			u.setUserName("userName_" + num);
			u.setPassword(UUID.randomUUID().toString());
			u.setBirthday(new Date());
			u.setDeleteFlag(Boolean.FALSE);
			userMap.put(num, u);
		}
	}
	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#save(net.fantesy84.user.domain.User)
	 */
	@Override
	public User save(User user) throws Exception {
		user.setId(ai.incrementAndGet());
		return userMap.put(ai.get(), user);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#delete(java.lang.Integer)
	 */
	@Override
	public Boolean delete(Integer id) throws Exception {
		User del = null;
		if (userMap.containsKey(id)) {
			del = userMap.remove(id);
		}
		Boolean result = Boolean.FALSE;
		if (del != null) {
			result = Boolean.TRUE; 
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#update(net.fantesy84.user.domain.User)
	 */
	@Override
	public User update(User user) throws Exception {
		User updateUser = null;
		if (user != null && user.getId() != null) {
			if (userMap.containsKey(user.getId())) {
				updateUser = userMap.put(user.getId(), user);
			}
		}
		return updateUser;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#findById(java.lang.Integer)
	 */
	@Override
	public User findById(Integer id) throws Exception {
		return userMap.get(id);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#findAll()
	 */
	@Override
	public List<User> findAll() throws Exception {
		List<User> users = new ArrayList<User>();
		for (User user : userMap.values()) {
			if (user != null) {
				users.add(user);
			}
		}
		return users;
	}

}
