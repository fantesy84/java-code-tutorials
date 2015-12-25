/**
 * Project java-code-tutorials-spring-commit-token
 * File: UserServiceImpl.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import net.fantesy84.user.domain.User;

/**
 * TypeName: UserServiceImpl
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月23日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	private Map<Integer, User> userMap;
	private AtomicInteger ati = new AtomicInteger();
	
	@PostConstruct
	public void initData() {
		Calendar c = Calendar.getInstance();
		userMap = new HashMap<Integer, User>();
		c.set(1985, 1, 10);
		User u1 = new User("Kate", "Female", c.getTime());
		u1.setId(ati.incrementAndGet());
		c.set(1982, 3, 4);
		User u2 = new User("John", "Male", c.getTime());
		u2.setId(ati.incrementAndGet());
		c.set(1991, 0, 2);
		User u3 = new User("Lili", "Male", c.getTime());
		u3.setId(ati.incrementAndGet());
		
		userMap.put(u1.getId(), u1);
		userMap.put(u2.getId(), u2);
		userMap.put(u3.getId(), u3);
	}
	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#save(net.fantesy84.user.domain.User)
	 */
	@Override
	public Boolean save(User user) throws Exception {
		userMap.put(ati.incrementAndGet(), user);
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#delete(java.lang.Integer)
	 */
	@Override
	public Boolean delete(Integer id) throws Exception {
		User remove = userMap.remove(id);
		if (remove == null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#update(net.fantesy84.user.domain.User)
	 */
	@Override
	public User update(User user) throws Exception {
		return userMap.put(user.getId(), user);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#findAll()
	 */
	@Override
	public List<User> findAll() throws Exception {
		return new ArrayList<User>(userMap.values());
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#findById(java.lang.Integer)
	 */
	@Override
	public User findById(Integer id) throws Exception {
		return userMap.get(id);
	}

}
