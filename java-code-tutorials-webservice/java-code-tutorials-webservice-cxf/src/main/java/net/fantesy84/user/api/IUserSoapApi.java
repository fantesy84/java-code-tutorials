/**
 * 项目名: java-tutorials-webservice-cxf-restful
 * 包名:  net.fantesy84.user.api
 * 文件名: IUserAPI.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月4日
 */
package net.fantesy84.user.api;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import net.fantesy84.user.domain.User;

/**
 * @author Andronicus
 * @since 2015年11月4日
 */
@WebService
public interface IUserSoapApi {
	
	User getById(@WebParam(name="id") Integer id);
	
	List<User> getAll();
	
	User add(@WebParam(name="user") User user);
}
