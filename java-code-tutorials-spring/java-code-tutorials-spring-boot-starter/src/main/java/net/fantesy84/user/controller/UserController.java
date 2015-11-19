/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.user.controller
 * 文件名: UserController.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fantesy84.user.domain.UserDTO;
import net.fantesy84.user.domain.UserReq;
import net.fantesy84.web.util.ResponseStatusCode;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value="/login",method={RequestMethod.POST},consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public UserDTO login(@RequestBody UserReq req){
		UserDTO dto = null;
		try {
			dto = new UserDTO();
			dto.setStatusCode(String.valueOf(ResponseStatusCode.SCCUESS.getKey()));
			dto.setMsg(ResponseStatusCode.SCCUESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		}
		return dto;
	}
}
