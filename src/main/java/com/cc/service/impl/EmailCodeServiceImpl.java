package com.cc.service.impl;

import com.cc.entity.constants.Constants;
import com.cc.entity.po.EmailCode;
import com.cc.entity.po.User;
import com.cc.entity.query.EmailCodeQuery;
import com.cc.entity.query.UserQuery;
import com.cc.entity.vo.PaginationResultVO;
import com.cc.entity.query.SimplePage;
import com.cc.exception.BusinessException;
import com.cc.mappers.EmailCodeMapper;
import com.cc.enums.PageSize;
import com.cc.mappers.UserMapper;
import com.cc.service.EmailCodeService;
import com.cc.utils.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: 邮箱验证码表Service
 * @author: pitayafruit
 * @date: 2023/08/17
 */
@Service("emailCodeService")
public class EmailCodeServiceImpl implements EmailCodeService {

	@Resource
	private EmailCodeMapper<EmailCode,EmailCodeQuery> emailCodeMapper;

	@Resource
	private UserMapper<User, UserQuery> userMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<EmailCode> findListByParam(EmailCodeQuery query) {
		return this.emailCodeMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public int findCountByParam(EmailCodeQuery query) {
		return this.emailCodeMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<EmailCode> findListByPage(EmailCodeQuery query) {
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<EmailCode> list = this.findListByParam(query);
		PaginationResultVO<EmailCode> result = new PaginationResultVO<>(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public int add(EmailCode bean) {
		return this.emailCodeMapper.insert(bean);
	}

	/**
	 * 新增或更新
	 */
	@Override
	public int addOrUpdate(EmailCode bean) {
		return this.emailCodeMapper.insertOrUpdate(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public int addBatch(List<EmailCode> listBean) {
		if(listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.emailCodeMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或更新
	 */
	@Override
	public int addOrUpdateBatch(List<EmailCode> listBean) {
		if(listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.emailCodeMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据EmailAndCode查询
	 */
	@Override
	public EmailCode getByEmailAndCode(String email, String code) {
		return this.emailCodeMapper.selectByEmailAndCode(email, code);
	}

	/**
	 * 根据EmailAndCode删除
	 */
	@Override
	public int deleteByEmailAndCode(String email, String code) {
		return this.emailCodeMapper.deleteByEmailAndCode(email, code);
	}

	/**
	 * 根据EmailAndCode更新
	 */
	@Override
	public int updateByEmailAndCode(EmailCode bean, String email, String code) {
		return this.emailCodeMapper.updateByEmailAndCode(bean, email, code);
	}

	/**
	 * 发送邮箱验证码
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void sendEmailCode(String email, Integer type) throws BusinessException {
		if (type == Constants.ZERO){
			User user = userMapper.selectByEmail(email);
			if (user != null){
				throw new BusinessException("该邮箱已被注册，请更换");
			}
		}
		String code = StringTools.getRandomNumber(Constants.LENGTH_5);

		//TODO 发送验证码

		emailCodeMapper.disabeleEmailCode(email);

		EmailCode emailCode = new EmailCode();
		emailCode.setCode(code);
		emailCode.setEmail(email);
		emailCode.setStatus(Constants.ZERO);
		emailCode.setCreatTime(new Date());
		emailCodeMapper.insert(emailCode);
	}

}