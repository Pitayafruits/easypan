package com.cc.controller;


import com.cc.entity.constants.Constants;
import com.cc.service.UserService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cc.utils.CreateImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


/**
 * 用户信息Controller
 */
@RestController
@RequestMapping("/user")
public class UserController extends ABaseController {

	@Resource
	private UserService userService;

	/**
	 * 获取验证码
	 */
	@GetMapping("/checkCode")
	public void checkCode(HttpServletResponse response, HttpSession session,Integer type) throws IOException {
		//生成验证码
		CreateImageCode createImageCode = new CreateImageCode(130,38,5,10);
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires",0);
		response.setContentType("image/jpeg");
		String code = createImageCode.getCode();
		if(type == null || type == 0){
			session.setAttribute(Constants.CHECK_CODE_KEY,code);
		}else{
			session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL,code);
		}
		createImageCode.write(response.getOutputStream());
	}

}