package com.cc.entity.query;

import java.util.Date;

/**
 * @Description: 用户信息表查询对象
 * @author: pitayafruit
 * @date: 2023/08/16
 */
public class UserQuery extends BaseQuery {
	/**
	 * 用户id
	 */
	private String userId;
	private String userIdFuzzy;
	/**
	 * 用户昵称
	 */
	private String nickName;
	private String nickNameFuzzy;
	/**
	 * 绑定邮箱
	 */
	private String email;
	private String emailFuzzy;
	/**
	 * 绑定QQid
	 */
	private String qqOpenId;
	private String qqOpenIdFuzzy;
	/**
	 * 头像
	 */
	private String qqAvatar;
	private String qqAvatarFuzzy;
	/**
	 * 密码
	 */
	private String password;
	private String passwordFuzzy;
	/**
	 * 注册时间
	 */
	private Date joinTime;
	private String joinTimeStart;
	private String joinTimeEnd;
	/**
	 * 上次登录时间
	 */
	private Date lastJoinTime;
	private String lastJoinTimeStart;
	private String lastJoinTimeEnd;
	/**
	 * 账号状态 0：禁用 1：启用
	 */
	private Integer status;
	/**
	 * 使用空间单位byte
	 */
	private Long userSpace;
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
	public void setUserSpace(Long userSpace) {
		this.userSpace = userSpace;
	}

	/**
	 * 使用空间单位byte
	 */
	public Long getUserSpace() {
		return this.userSpace;
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

	/**
	 * 
	 */
	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	/**
	 * 
	 */
	public String getUserIdFuzzy() {
		return this.userIdFuzzy;
	}

	/**
	 * 
	 */
	public void setNickNameFuzzy(String nickNameFuzzy) {
		this.nickNameFuzzy = nickNameFuzzy;
	}

	/**
	 * 
	 */
	public String getNickNameFuzzy() {
		return this.nickNameFuzzy;
	}

	/**
	 * 
	 */
	public void setEmailFuzzy(String emailFuzzy) {
		this.emailFuzzy = emailFuzzy;
	}

	/**
	 * 
	 */
	public String getEmailFuzzy() {
		return this.emailFuzzy;
	}

	/**
	 * 
	 */
	public void setQqOpenIdFuzzy(String qqOpenIdFuzzy) {
		this.qqOpenIdFuzzy = qqOpenIdFuzzy;
	}

	/**
	 * 
	 */
	public String getQqOpenIdFuzzy() {
		return this.qqOpenIdFuzzy;
	}

	/**
	 * 
	 */
	public void setQqAvatarFuzzy(String qqAvatarFuzzy) {
		this.qqAvatarFuzzy = qqAvatarFuzzy;
	}

	/**
	 * 
	 */
	public String getQqAvatarFuzzy() {
		return this.qqAvatarFuzzy;
	}

	/**
	 * 
	 */
	public void setPasswordFuzzy(String passwordFuzzy) {
		this.passwordFuzzy = passwordFuzzy;
	}

	/**
	 * 
	 */
	public String getPasswordFuzzy() {
		return this.passwordFuzzy;
	}

	/**
	 * 
	 */
	public void setJoinTimeStart(String joinTimeStart) {
		this.joinTimeStart = joinTimeStart;
	}

	/**
	 * 
	 */
	public String getJoinTimeStart() {
		return this.joinTimeStart;
	}

	/**
	 * 
	 */
	public void setJoinTimeEnd(String joinTimeEnd) {
		this.joinTimeEnd = joinTimeEnd;
	}

	/**
	 * 
	 */
	public String getJoinTimeEnd() {
		return this.joinTimeEnd;
	}

	/**
	 * 
	 */
	public void setLastJoinTimeStart(String lastJoinTimeStart) {
		this.lastJoinTimeStart = lastJoinTimeStart;
	}

	/**
	 * 
	 */
	public String getLastJoinTimeStart() {
		return this.lastJoinTimeStart;
	}

	/**
	 * 
	 */
	public void setLastJoinTimeEnd(String lastJoinTimeEnd) {
		this.lastJoinTimeEnd = lastJoinTimeEnd;
	}

	/**
	 * 
	 */
	public String getLastJoinTimeEnd() {
		return this.lastJoinTimeEnd;
	}

}