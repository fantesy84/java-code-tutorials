/**
 * 项目名: java-code-tutorials-spring-redis-web
 * 包名:  net.fantesy84.user.controller
 * 文件名: UserController.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年2月23日
 */
package net.fantesy84.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fantesy84.common.controller.BaseController;
import net.fantesy84.user.domain.UserBase;
import net.fantesy84.user.dto.ReqUserBaseDTO;
import net.fantesy84.user.dto.ResUserBaseDTO;
import net.fantesy84.user.service.IUserBaseService;

/**
 * @author Andronicus
 * @since 2016年2月23日
 */
@RestController
@RequestMapping("/baseuser")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserController extends BaseController{
	@Autowired
	private IUserBaseService userBaseService;
	
	@RequestMapping(value="/save",method={RequestMethod.POST},consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResUserBaseDTO save(@RequestBody ReqUserBaseDTO parameter) {
		ResUserBaseDTO dto = new ResUserBaseDTO();
		if (parameter == null || parameter.getBase() == null) {
			dto.setCode(super.ERROR_CODE);
			dto.setMessage("参数错误!");
			return dto;
		}
		try {
			Boolean result = userBaseService.save(parameter.getBase());
			if (result) {
				dto.setCode(super.SUCCESS_CODE);
			}
		} catch (Exception e) {
			dto.setCode(super.ERROR_CODE);
			dto.setMessage(e.getMessage());
		}
		return dto;
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST},consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResUserBaseDTO update(@RequestBody ReqUserBaseDTO parameter) {
		ResUserBaseDTO dto = new ResUserBaseDTO();
		if (parameter == null || parameter.getBase() == null) {
			dto.setCode(super.ERROR_CODE);
			dto.setMessage("参数错误!");
			return dto;
		}
		try {
			UserBase update = userBaseService.update(parameter.getBase());
			if (update != null) {
				dto.setCode(super.SUCCESS_CODE);
				dto.setBase(update);
			}
		} catch (Exception e) {
			dto.setCode(super.ERROR_CODE);
			dto.setMessage(e.getMessage());
		}
		return dto;
	}
	
	@RequestMapping(value="/get/{id}")
	public ResUserBaseDTO getByPrimaryKey(@PathVariable("id")Integer id) {
		ResUserBaseDTO dto = new ResUserBaseDTO();
		if (id == null) {
			dto.setCode(super.ERROR_CODE);
			dto.setMessage("参数错误!");
			return dto;
		}
		try {
			UserBase base = userBaseService.findByPrimaryKey(id);
			if (base != null) {
				dto.setCode(super.SUCCESS_CODE);
				dto.setBase(base);
			}
		} catch (Exception e) {
			dto.setCode(super.ERROR_CODE);
			dto.setMessage(e.getMessage());
		}
		return dto;
	}
}
