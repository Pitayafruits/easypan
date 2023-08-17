package com.cc.mappers;


import org.apache.ibatis.annotations.Param;

/**
 * @Description: 用户信息表
 * @author: pitayafruit
 * @date: 2023/08/16
 */
public interface UserMapper<T,P> extends BaseMapper {
	/**
	 * 根据UserId查询
	 */
	T selectByUserId(@Param("userId") String userId);

	/**
	 * 根据UserId删除
	 */
	int deleteByUserId(@Param("userId") String userId);

	/**
	 * 根据UserId更新
	 */
	int updateByUserId(@Param("bean") T t, @Param("userId") String userId);

	/**
	 * 根据Email查询
	 */
	T selectByEmail(@Param("email") String email);

	/**
	 * 根据Email删除
	 */
	int deleteByEmail(@Param("email") String email);

	/**
	 * 根据Email更新
	 */
	int updateByEmail(@Param("bean") T t, @Param("email") String email);

	/**
	 * 根据QqOpenId查询
	 */
	T selectByQqOpenId(@Param("qqOpenId") String qqOpenId);

	/**
	 * 根据QqOpenId删除
	 */
	int deleteByQqOpenId(@Param("qqOpenId") String qqOpenId);

	/**
	 * 根据QqOpenId更新
	 */
	int updateByQqOpenId(@Param("bean") T t, @Param("qqOpenId") String qqOpenId);

}