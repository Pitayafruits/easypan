package com.cc.service;
import java.util.List;
import com.cc.entity.po.EmailCode;
import com.cc.entity.query.EmailCodeQuery;
import com.cc.entity.vo.PaginationResultVO;
import com.cc.exception.BusinessException;

/**
 * @Description: 邮箱验证码表Service
 * @author: pitayafruit
 * @date: 2023/08/17
 */
public interface EmailCodeService{

	/**
	 * 根据条件查询列表
	 */
	List<EmailCode> findListByParam(EmailCodeQuery query);

	/**
	 * 根据条件查询数量
	 */
	int findCountByParam(EmailCodeQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<EmailCode> findListByPage(EmailCodeQuery query);

	/**
	 * 新增
	 */
	int add(EmailCode bean);

	/**
	 * 新增或更新
	 */
	int addOrUpdate(EmailCode bean);

	/**
	 * 批量新增
	 */
	int addBatch(List<EmailCode> listBean);

	/**
	 * 批量新增或更新
	 */
	int addOrUpdateBatch(List<EmailCode> listBean);

	/**
	 * 根据EmailAndCode查询
	 */
	EmailCode getByEmailAndCode(String email, String code);

	/**
	 * 根据EmailAndCode删除
	 */
	int deleteByEmailAndCode(String email, String code);

	/**
	 * 根据EmailAndCode更新
	 */
	int updateByEmailAndCode(EmailCode bean, String email, String code);

	/**
	 * 发送邮箱验证码
	 */
    void sendEmailCode(String email, Integer type) throws BusinessException;

	/**
	 * 校验邮箱验证码
	 */
	void checkCode(String email,String code) throws BusinessException;

	/**
	 * 找回密码
	 */
	//todo 未完成
	void resetPwd(String email,String password,String emailCode);
}