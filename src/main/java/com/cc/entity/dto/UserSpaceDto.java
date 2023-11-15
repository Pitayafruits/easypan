package com.cc.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户空间dto
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSpaceDto implements Serializable {

    private Long userSpace;

    private Long totalSpace;


}
