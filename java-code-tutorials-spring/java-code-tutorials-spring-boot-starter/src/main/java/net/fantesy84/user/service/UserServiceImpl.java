/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.user.service
 * 文件名: UserServiceImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fantesy84.user.dao.mybatis.UserMapper;
import net.fantesy84.user.domain.User;
import net.fantesy84.user.domain.UserExample;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserService#login(net.fantesy84.user.domain.User)
	 */
	@Override
	public User login(User user) throws Exception {
		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(user.getAccount()).andPasswordEqualTo(user.getPassword());
		List<User> result = userMapper.selectByExample(example);
		User u = null;
		if (result != null && !result.isEmpty()) {
			u = result.get(0);
		}
		return u;
	}

}
