/**
 * Project java-code-tutorials-webservice-java
 * File: IUserService.java
 * CreateTime: 2015年11月16日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.service;

import java.util.List;

import net.fantesy84.user.domain.User;

/**
 * TypeName: IUserService
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月16日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public interface IUserService {
	User save(User user) throws Exception;
	Boolean delete(Integer id) throws Exception;
	User update(User user) throws Exception;
	User findById(Integer id) throws Exception;
	List<User> findAll() throws Exception;
}
