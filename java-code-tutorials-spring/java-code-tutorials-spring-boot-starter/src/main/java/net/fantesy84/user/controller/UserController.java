/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.user.controller
 * 文件名: UserController.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fantesy84.user.domain.User;
import net.fantesy84.user.domain.UserDTO;
import net.fantesy84.user.domain.UserReq;
import net.fantesy84.user.service.IUserService;
import net.fantesy84.web.util.ResponseStatusCode;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/login",method={RequestMethod.POST},consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public UserDTO login(@RequestBody UserReq req){
		UserDTO dto = null;
		try {
			User model = new User();
			model.setAccount(req.getAccount());
			model.setPassword(req.getPassword());
			User logined = userService.login(model);
			dto = new UserDTO();
			dto.setStatusCode(String.valueOf(ResponseStatusCode.SCCUESS.getKey()));
			dto.setMsg(ResponseStatusCode.SCCUESS.getValue());
			List<User> users = new ArrayList<User>();
			users.add(logined);
			dto.setUsers(users);
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		}
		return dto;
	}
}
