/**
 * Project java-code-tutorials-spring-commit-token
 * File: UserController.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fantesy84.sys.web.annotation.DuplicateSubmitValidate;
import net.fantesy84.user.domain.User;
import net.fantesy84.user.dto.UserDTO;
import net.fantesy84.user.service.IUserService;

/**
 * TypeName: UserController
 * 
 * <P>CreateTime: 2015年12月23日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/save")
	@DuplicateSubmitValidate(createToken=true,reset=true,resetTime=30000)
	public UserDTO save(@RequestBody User user) {
		UserDTO dto = new UserDTO();
		try {
			if (userService.save(user)) {
				dto.setCode("SUCCESS");
			}
		} catch (Exception e) {
			dto.setCode("ERROR");
			dto.setMessage(e.getMessage());
			logger.error(e.getMessage(), new IllegalStateException(e));
		}
		return dto;
	}
	
	public UserDTO query(Integer id) {
		return null;
	}
}
