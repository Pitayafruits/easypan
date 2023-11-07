package com.cc.controller;

import com.cc.entity.constants.Constants;
import com.cc.entity.dto.SessionWebUserDto;
import com.cc.entity.vo.ResponseVO;

import com.cc.enums.ResponseCodeEnum;
import com.cc.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class ABaseController {

    protected static final String STATUC_SUCCESS = "success";
    protected static final String STATUC_ERROR = "error";

    protected static final Logger logger = LoggerFactory.getLogger(ABaseController.class);

    protected <T> ResponseVO getSuccessResponseVO(T t) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setStatus(STATUC_SUCCESS);
        responseVO.setCode(ResponseCodeEnum.CODE_200.getCode());
        responseVO.setInfo(ResponseCodeEnum.CODE_200.getMsg());
        responseVO.setData(t);
        return responseVO;
    }

    protected void readFile(HttpServletResponse response, String filePath) {
        if (!StringTools.pathIsOk(filePath)) {
            return;
        }
        try {
            OutputStream out = null;
            FileInputStream in = null;
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            in = new FileInputStream(file);
            byte[] byteData = new byte[1024];
            out = response.getOutputStream();
            int len = 0;
            while ((len = in.read(byteData)) != -1) {
                out.write(byteData, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            logger.error("读取文件异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("IO异常", e);
                }
            }
        }
    }

    protected SessionWebUserDto getUserInfoFromSession (HttpSession session) {
        SessionWebUserDto sessionWebUserDto = (SessionWebUserDto) session.getAttribute(Constants.SESSION_KEY);
        return sessionWebUserDto;
    }

}
