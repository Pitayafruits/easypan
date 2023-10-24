package com.cc.controller;


import com.cc.annotaion.GlobalInterceptor;
import com.cc.annotaion.VerifyParam;
import com.cc.entity.constants.Constants;
import com.cc.entity.dto.SessionWebUserDto;
import com.cc.entity.vo.ResponseVO;
import com.cc.enums.VerifyRegexEnum;
import com.cc.exception.BusinessException;
import com.cc.service.EmailCodeService;
import com.cc.service.UserService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cc.utils.CreateImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Resource
    private EmailCodeService emailCodeService;

    /**
     * 获取验证码
     */
    @GetMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws IOException {
        //生成验证码
        CreateImageCode createImageCode = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = createImageCode.getCode();
        if (type == null || type == 0) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        createImageCode.write(response.getOutputStream());
    }

    /**
     * 发送邮箱验证码
     */
    @PostMapping("/sendEmailCode")
    @GlobalInterceptor
    public ResponseVO sendEmailCode(HttpSession session,
                                    @VerifyParam(required = true,regex = VerifyRegexEnum.EMAIL,max = 150) String email,
                                    @VerifyParam(required = true) String checkCode,
                                    @VerifyParam(required = true) Integer type) throws BusinessException {
        try {
            //验证码比较
            if (checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL))) {
                throw new BusinessException("图片验证码错误！");
            }
            emailCodeService.sendEmailCode(email,type);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }
    }

    /**
     * 注册用户
     */
    @PostMapping("/register")
    @GlobalInterceptor
    public ResponseVO register(HttpSession session,
                                    @VerifyParam(required = true,regex = VerifyRegexEnum.EMAIL,max = 150) String email,
                                    @VerifyParam(required = true) String nickName,
                                    @VerifyParam(required = true,regex = VerifyRegexEnum.PASSWORD,min = 8,max = 18) String password,
                                    @VerifyParam(required = true) String checkCode,
                                    @VerifyParam(required = true) String emailCode) throws BusinessException {
        try {
            //验证码比较
            if (checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码错误！");
            }
            userService.register(email,nickName,password,emailCode);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @GlobalInterceptor
    public ResponseVO login(HttpSession session,
                               @VerifyParam(required = true,regex = VerifyRegexEnum.EMAIL,max = 150) String email,
                               @VerifyParam(required = true) String password,
                               @VerifyParam(required = true) String checkCode) throws BusinessException {
        try {
            //验证码比较
            if (checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码错误！");
            }
            SessionWebUserDto sessionWebUserDto = userService.login(email, password);
            session.setAttribute(Constants.SESSION_KEY,sessionWebUserDto);
            return getSuccessResponseVO(sessionWebUserDto);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }
}