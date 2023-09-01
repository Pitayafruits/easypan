package com.cc.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.cc.enums.DateTimePatternEnum;
import com.cc.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description: 用户信息表
 * @author: pitayafruit
 * @date: 2023/08/16
 */
public class User implements Serializable {
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 绑定邮箱
	 */
	private String email;
	/**
	 * 绑定QQid
	 */
	private String qqOpenId;
	/**
	 * 头像
	 */
	private String qqAvatar;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinTime;
	/**
	 * 上次登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastJoinTime;
	/**
	 * 账号状态 0：禁用 1：启用
	 */
	@JsonIgnore
	private Integer status;
	/**
	 * 使用空间单位byte
	 */
	private Long useSpace;
	/**
	 * 
	 */
	private Long totalSpace;
	/**
	 * 用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 用户id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 用户昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 用户昵称
	 */
	public String getNickName() {
		return this.nickName;
	}

	/**
	 * 绑定邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 绑定邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 绑定QQid
	 */
	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	/**
	 * 绑定QQid
	 */
	public String getQqOpenId() {
		return this.qqOpenId;
	}

	/**
	 * 头像
	 */
	public void setQqAvatar(String qqAvatar) {
		this.qqAvatar = qqAvatar;
	}

	/**
	 * 头像
	 */
	public String getQqAvatar() {
		return this.qqAvatar;
	}

	/**
	 * 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 注册时间
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	/**
	 * 注册时间
	 */
	public Date getJoinTime() {
		return this.joinTime;
	}

	/**
	 * 上次登录时间
	 */
	public void setLastJoinTime(Date lastJoinTime) {
		this.lastJoinTime = lastJoinTime;
	}

	/**
	 * 上次登录时间
	 */
	public Date getLastJoinTime() {
		return this.lastJoinTime;
	}

	/**
	 * 账号状态 0：禁用 1：启用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 账号状态 0：禁用 1：启用
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 使用空间单位byte
	 */
	public void setUseSpace(Long useSpace) {
		this.useSpace = useSpace;
	}

	/**
	 * 使用空间单位byte
	 */
	public Long getUseSpace() {
		return this.useSpace;
	}

	/**
	 * 
	 */
	public void setTotalSpace(Long totalSpace) {
		this.totalSpace = totalSpace;
	}

	/**
	 * 
	 */
	public Long getTotalSpace() {
		return this.totalSpace;
	}

	@Override
	public String toString() {
		return "User {" + "用户id : " + (userId == null ? "空" : userId) + "," + "用户昵称 : " + (nickName == null ? "空" : nickName) + "," + "绑定邮箱 : " + (email == null ? "空" : email) + "," + "绑定QQid : " + (qqOpenId == null ? "空" : qqOpenId) + "," + "头像 : " + (qqAvatar == null ? "空" : qqAvatar) + "," + "密码 : " + (password == null ? "空" : password) + "," + "注册时间 : " + (joinTime == null ? "空" : DateUtils.format(joinTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "," + "上次登录时间 : " + (lastJoinTime == null ? "空" : DateUtils.format(lastJoinTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "," + "账号状态 0：禁用 1：启用 : " + (status == null ? "空" : status) + "," + "使用空间单位byte : " + (useSpace == null ? "空" : useSpace) + "," + " : " + (totalSpace == null ? "空" : totalSpace) + "}";
	}
}