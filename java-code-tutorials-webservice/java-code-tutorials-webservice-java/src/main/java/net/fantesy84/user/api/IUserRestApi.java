/**
 * Project java-code-tutorials-webservice-java
 * File: IUserRestApi.java
 * CreateTime: 2015年11月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.fantesy84.user.domain.User;

/**
 * TypeName: IUserRestApi
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月17日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
@Path("/user")
public interface IUserRestApi {
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	User save(User user);
	
	@GET
	@Path("/del/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Boolean delete(@PathParam("id")Integer id);
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	User update(User user);
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	User findById(@PathParam("id")Integer id);
	
	@GET
	@Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	List<User> findAll();
}
