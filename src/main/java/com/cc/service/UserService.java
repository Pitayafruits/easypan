package com.cc.service;
import java.util.List;

import com.cc.entity.dto.SessionWebUserDto;
import com.cc.entity.po.User;
import com.cc.entity.query.UserQuery;
import com.cc.entity.vo.PaginationResultVO;
import com.cc.exception.BusinessException;

/**
 * @Description: 用户信息表Service
 * @author: pitayafruit
 * @date: 2023/08/16
 */
public interface UserService{

	/**
	 * 根据条件查询列表
	 */
	List<User> findListByParam(UserQuery query);

	/**
	 * 根据条件查询数量
	 */
	int findCountByParam(UserQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<User> findListByPage(UserQuery query);

	/**
	 * 新增
	 */
	int add(User bean);

	/**
	 * 新增或更新
	 */
	int addOrUpdate(User bean);

	/**
	 * 批量新增
	 */
	int addBatch(List<User> listBean);

	/**
	 * 批量新增或更新
	 */
	int addOrUpdateBatch(List<User> listBean);

	/**
	 * 根据UserId查询
	 */
	User getByUserId(String userId);

	/**
	 * 根据UserId删除
	 */
	int deleteByUserId(String userId);

	/**
	 * 根据UserId更新
	 */
	int updateByUserId(User bean, String userId);

	/**
	 * 根据Email查询
	 */
	User getByEmail(String email);

	/**
	 * 根据Email删除
	 */
	int deleteByEmail(String email);

	/**
	 * 根据Email更新
	 */
	int updateByEmail(User bean, String email);

	/**
	 * 根据QqOpenId查询
	 */
	User getByQqOpenId(String qqOpenId);

	/**
	 * 根据QqOpenId删除
	 */
	int deleteByQqOpenId(String qqOpenId);

	/**
	 * 根据QqOpenId更新
	 */
	int updateByQqOpenId(User bean, String qqOpenId);

	/**
	 * 注册新用户
	 */
	void register(String email,String nickName,String password,String emailCode) throws BusinessException;

	/**
	 * 用户登录
	 */
	SessionWebUserDto login(String email,String password) throws BusinessException;

}