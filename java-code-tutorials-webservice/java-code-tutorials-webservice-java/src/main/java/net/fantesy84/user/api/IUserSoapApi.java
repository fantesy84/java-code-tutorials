/**
 * Project java-code-tutorials-webservice-java
 * File: IUserApi.java
 * CreateTime: 2015年11月16日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.api;

import java.util.List;

import javax.jws.WebService;

import net.fantesy84.user.domain.User;

/**
 * TypeName: IUserApi
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月16日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
@WebService
public interface IUserSoapApi {
	User save(User user);
	Boolean delete(Integer id);
	User update(User user);
	User findById(Integer id);
	List<User> findAll();
}
