/**
 * Project java-code-tutorials-webservice-java
 * File: RestfulApplication.java
 * CreateTime: 2015年11月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.servlet;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import net.fantesy84.user.api.IUserRestApi;

/**
 * TypeName: RestfulApplication
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月17日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class RestfulApplication extends Application {

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
//		IUserRestApi restapi = new UserRestApiImpl();
		classes.add(IUserRestApi.class);
		return classes;
	}
	
}
