package com.cc.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户空间dto
 */
@Data
public class UserSpaceDto implements Serializable {

    private Long userSpace;

    private Long totalSpace;


}
