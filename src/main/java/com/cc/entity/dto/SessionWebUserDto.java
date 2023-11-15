package com.cc.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户返回dto
 */
@Data
public class SessionWebUserDto implements Serializable {

    private String userId;

    private String nickName;

    private boolean isAdmin;

    private String avatar;
}
