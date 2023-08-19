package com.cc.entity.query;

import java.util.Date;

/**
 * @Description: 邮箱验证码表查询对象
 * @author: pitayafruit
 * @date: 2023/08/17
 */
public class EmailCodeQuery extends BaseQuery {
	/**
	 * 邮箱
	 */
	private String email;
	private String emailFuzzy;
	/**
	 * 验证码
	 */
	private String code;
	private String codeFuzzy;
	/**
	 * 创建时间
	 */
	private Date creatTime;
	private String creatTimeStart;
	private String creatTimeEnd;
	/**
	 * 状态 0:未使用 1:已使用
	 */
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
	public void setCodeFuzzy(String codeFuzzy) {
		this.codeFuzzy = codeFuzzy;
	}

	/**
	 * 
	 */
	public String getCodeFuzzy() {
		return this.codeFuzzy;
	}

	/**
	 * 
	 */
	public void setCreatTimeStart(String creatTimeStart) {
		this.creatTimeStart = creatTimeStart;
	}

	/**
	 * 
	 */
	public String getCreatTimeStart() {
		return this.creatTimeStart;
	}

	/**
	 * 
	 */
	public void setCreatTimeEnd(String creatTimeEnd) {
		this.creatTimeEnd = creatTimeEnd;
	}

	/**
	 * 
	 */
	public String getCreatTimeEnd() {
		return this.creatTimeEnd;
	}

}