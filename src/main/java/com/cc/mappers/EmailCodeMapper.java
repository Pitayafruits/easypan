package com.cc.mappers;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.cc.enums.DateTimePatternEnum;
import com.cc.utils.DateUtils;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 邮箱验证码表
 * @author: pitayafruit
 * @date: 2023/08/17
 */
public interface EmailCodeMapper<T,P> extends BaseMapper {
	/**
	 * 根据EmailAndCode查询
	 */
	T selectByEmailAndCode(@Param("email") String email, @Param("code") String code);

	/**
	 * 根据EmailAndCode删除
	 */
	int deleteByEmailAndCode(@Param("email") String email, @Param("code") String code);

	/**
	 * 根据EmailAndCode更新
	 */
	int updateByEmailAndCode(@Param("bean") T t, @Param("email") String email, @Param("code") String code);

}