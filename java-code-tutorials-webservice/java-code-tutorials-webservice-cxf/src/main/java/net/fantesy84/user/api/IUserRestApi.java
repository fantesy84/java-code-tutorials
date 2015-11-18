/**
 * 项目名: java-tutorials-webservice-cxf-restful
 * 包名:  net.fantesy84.user.api
 * 文件名: IUserAPI.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月4日
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
 * @author Andronicus
 * @since 2015年11月4日
 */
@Path("/user")
public interface IUserRestApi {
	
	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	User getById(@PathParam("id")Integer id);
	
	@Path("/get/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<User> getAll();
	
	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	User add(User user);
}
