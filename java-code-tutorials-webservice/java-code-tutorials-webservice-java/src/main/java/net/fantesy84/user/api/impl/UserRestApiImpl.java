/**
 * Project java-code-tutorials-webservice-java
 * File: UserRestApiImpl.java
 * CreateTime: 2015年11月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.api.impl;

import java.util.List;

import net.fantesy84.user.api.IUserRestApi;
import net.fantesy84.user.domain.User;
import net.fantesy84.user.service.IUserService;
import net.fantesy84.user.service.impl.UserServiceImpl;

/**
 * TypeName: UserRestApiImpl
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月17日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class UserRestApiImpl implements IUserRestApi {

	private IUserService userService;
	public UserRestApiImpl() {
		userService = new UserServiceImpl();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.api.IUserApi#save(net.fantesy84.user.domain.User)
	 */
	@Override
	public User save(User user) {
		User u = null;
		try {
			u = userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.api.IUserApi#delete(java.lang.Integer)
	 */
	@Override
	public Boolean delete(Integer id) {
		Boolean result = Boolean.FALSE;
		try {
			result = userService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.api.IUserApi#update(net.fantesy84.user.domain.User)
	 */
	@Override
	public User update(User user) {
		User u = null;
		try {
			u = userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.api.IUserApi#findById(java.lang.Integer)
	 */
	@Override
	public User findById(Integer id) {
		User u = null;
		try {
			u = userService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.api.IUserApi#findAll()
	 */
	@Override
	public List<User> findAll() {
		List<User> users = null;
		try {
			users = userService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}
