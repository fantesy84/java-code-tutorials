/**
 * 项目名: java-code-tutorials-websocket-spring
 * 包名:  net.fantesy84.sys.user.controller
 * 文件名: UserController.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月31日
 */
package net.fantesy84.sys.user.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Andronicus
 * @since 2015年12月31日
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/user",produces={"application/json; charset=UTF-8"})
public class UserController {
	
	
}
