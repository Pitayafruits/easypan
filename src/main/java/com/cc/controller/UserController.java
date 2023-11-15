package com.cc.controller;


import com.cc.annotaion.GlobalInterceptor;
import com.cc.annotaion.VerifyParam;
import com.cc.component.RedisComponent;
import com.cc.config.AppConfig;
import com.cc.entity.constants.Constants;
import com.cc.entity.dto.SessionWebUserDto;
import com.cc.entity.dto.UserSpaceDto;
import com.cc.entity.po.User;
import com.cc.entity.vo.ResponseVO;
import com.cc.enums.VerifyRegexEnum;
import com.cc.exception.BusinessException;
import com.cc.service.EmailCodeService;
import com.cc.service.UserService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cc.utils.CreateImageCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 用户信息Controller
 */
@RestController
@RequestMapping("/user")
public class UserController extends ABaseController {

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private EmailCodeService emailCodeService;

    @Resource
    private AppConfig appConfig;

    @Resource
    private RedisComponent redisComponent;

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
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public ResponseVO sendEmailCode(HttpSession session,
                                    @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                                    @VerifyParam(required = true) String checkCode,
                                    @VerifyParam(required = true) Integer type) throws BusinessException {
        try {
            //验证码比较
            if (checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL))) {
                throw new BusinessException("图片验证码错误！");
            }
            emailCodeService.sendEmailCode(email, type);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }
    }

    /**
     * 注册用户
     */
    @PostMapping("/register")
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public ResponseVO register(HttpSession session,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                               @VerifyParam(required = true) String nickName,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8, max = 18) String password,
                               @VerifyParam(required = true) String checkCode,
                               @VerifyParam(required = true) String emailCode) throws BusinessException {
        try {
            //验证码比较
            if (checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码错误！");
            }
            userService.register(email, nickName, password, emailCode);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public ResponseVO login(HttpSession session,
                            @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                            @VerifyParam(required = true) String password,
                            @VerifyParam(required = true) String checkCode) throws BusinessException {
        try {
            //验证码比较
            if (checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码错误！");
            }
            SessionWebUserDto sessionWebUserDto = userService.login(email, password);
            session.setAttribute(Constants.SESSION_KEY, sessionWebUserDto);
            return getSuccessResponseVO(sessionWebUserDto);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    /**
     * 找回密码
     */
    @PostMapping("/resetPwd")
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public ResponseVO resetPwd(HttpSession session,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8, max = 18) String password,
                               @VerifyParam(required = true) String checkCode,
                               @VerifyParam(required = true) String emailCode) throws BusinessException {
        try {
            //验证码比较
            if (checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码错误！");
            }
            userService.restPwd(email, password, emailCode);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    /**
     * 获取头像
     */
    @GetMapping("/getAvatar/{userId}")
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public void getAvatar(HttpServletResponse response, @VerifyParam(required = true) @PathVariable("userId") String userId) {
            //声明头像存储地址
            String avatarFoldName = Constants.FILE_FOLDER_FILE + Constants.FILE_FOLDER_AVATAR_NAME;
            File folder = new File(avatarFoldName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String avatarPath = appConfig.getProjectFolder() + avatarFoldName + userId + Constants.AVATAR_SUFFIX;
            File file = new File(avatarPath);
            if (!file.exists()) {
                if (!new File(appConfig.getProjectFolder() + avatarFoldName + Constants.AVATAR_DEFAULT).exists()) {
                    printNoDefaultImage(response);
                }
                avatarPath = appConfig.getProjectFolder() + avatarFoldName + Constants.AVATAR_DEFAULT;
            }
            response.setContentType("image/jpg");
            readFile(response, avatarPath);
        }

    /**
     * 头像输出异常提示
     */
    private void printNoDefaultImage(HttpServletResponse response){
        response.setHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print("请在头像目录下放置默认头像default_avatar.jpg");
            writer.close();
        } catch (IOException e) {
            logger.error("输出无默认图失败", e);
        }finally {
            writer.close();
        }
    }

    /**
     * 获得用户信息
     */
    @RequestMapping("/getUserInfo")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO getUserInfo(HttpSession session){
        SessionWebUserDto sessionWebUserDto = getUserInfoFromSession(session);
        return getSuccessResponseVO(sessionWebUserDto);
    }

    /**
     * 获取用户使用空间
     */
    @RequestMapping("/getUseSpace")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO getUseSpace(HttpSession session){
        SessionWebUserDto sessionWebUserDto = getUserInfoFromSession(session);
        UserSpaceDto spaceDto = redisComponent.getUserSpaceUse(sessionWebUserDto.getUserId());
        return getSuccessResponseVO(spaceDto);
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO logout(HttpSession session){
        session.invalidate(); //清除session
        return getSuccessResponseVO(null);
    }

    /**
     * 上传头像
     */
    @RequestMapping("/updateUserAvatar")
    @GlobalInterceptor
    public ResponseVO updateUserAvatar(HttpSession session, MultipartFile avatar){
        SessionWebUserDto webUserDto = getUserInfoFromSession(session);
        String baseFolder = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE;
        File targetFireFolder = new File(baseFolder + Constants.FILE_FOLDER_AVATAR_NAME);
        if (!targetFireFolder.exists()){
            targetFireFolder.mkdirs();
        }
        File targetFile = new File(targetFireFolder.getPath() + "/" + webUserDto.getUserId() + Constants.AVATAR_SUFFIX);
        try {
            avatar.transferTo(targetFile);
        } catch (Exception e){
            logger.error("上传头像失败！",e);
        }
        User user = new User();
        user.setQqAvatar("");
        userService.updateByUserId(user, webUserDto.getUserId());
        webUserDto.setAvatar(null);
        session.setAttribute(Constants.SESSION_KEY, webUserDto);
        return getSuccessResponseVO(null);
    }

}