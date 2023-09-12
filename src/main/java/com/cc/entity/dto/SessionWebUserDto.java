package com.cc.entity.dto;

import lombok.Data;

/**
 * 登录用户返回dto
 */
@Data
public class SessionWebUserDto {

    private String userId;

    private String nickName;

    private boolean isAdmin;

    private String avatar;
}
