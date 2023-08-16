package com.cc.exception;

import com.cc.enums.ResponseCodeEnum;

public class BusinessException extends Exception{
    private ResponseCodeEnum codeEnum;
    private int code;
    private String message;

    public BusinessException(String message,Throwable e){
        super(message,e);
        this.message = message;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(ResponseCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.codeEnum = codeEnum;
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ResponseCodeEnum getCodeEnum() {
        return codeEnum;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 重写方法，业务异常不需要堆栈信息，提高效率
     * @return
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
