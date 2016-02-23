/**
 * 项目名: java-code-tutorials-spring-redis-web
 * 包名:  net.fantesy84.user.service
 * 文件名: UserBaseServiceImpl.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年2月23日
 */
package net.fantesy84.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fantesy84.user.domain.UserBase;
import net.fantesy84.user.domain.UserBaseExample;
import net.fantesy84.user.mapper.UserBaseMapper;

/**
 * @author Andronicus
 * @since 2016年2月23日
 */
@Service("userBaseService")
public class UserBaseServiceImpl implements IUserBaseService {
	private static final Logger logger = LoggerFactory.getLogger(UserBaseServiceImpl.class);
	@Autowired
	private UserBaseMapper userBaseMapper;

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserBaseService#save(net.fantesy84.user.domain.UserBase)
	 */
	@Override
	public Boolean save(UserBase base) throws Exception {
		logger.info("Save action!");
		int insert = userBaseMapper.insert(base);
		if (insert == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserBaseService#update(net.fantesy84.user.domain.UserBase)
	 */
	@Override
	public UserBase update(UserBase raw) throws Exception {
		logger.info("Update action!");
		int update = userBaseMapper.updateByPrimaryKey(raw);
		if (update == 1) {
			return raw;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.user.service.IUserBaseService#findAll()
	 */
	@Override
	public List<UserBase> findAll() throws Exception {
		logger.info("Find all data...It's a long time wast!");
		return userBaseMapper.selectByExample(new UserBaseExample());
	}

}
