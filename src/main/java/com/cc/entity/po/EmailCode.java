package com.cc.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.cc.enums.DateTimePatternEnum;
import com.cc.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description: 邮箱验证码表
 * @author: pitayafruit
 * @date: 2023/08/17
 */
public class EmailCode implements Serializable {
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	/**
	 * 状态 0:未使用 1:已使用
	 */
	@JsonIgnore
	private Integer status;
	/**
	 * 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 验证码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 验证码
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 创建时间
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	/**
	 * 创建时间
	 */
	public Date getCreatTime() {
		return this.creatTime;
	}

	/**
	 * 状态 0:未使用 1:已使用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 状态 0:未使用 1:已使用
	 */
	public Integer getStatus() {
		return this.status;
	}

	@Override
	public String toString() {
		return "EmailCode {" + "邮箱 : " + (email == null ? "空" : email) + "," + "验证码 : " + (code == null ? "空" : code) + "," + "创建时间 : " + (creatTime == null ? "空" : DateUtils.format(creatTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "," + "状态 0:未使用 1:已使用 : " + (status == null ? "空" : status) + "}";
	}
}