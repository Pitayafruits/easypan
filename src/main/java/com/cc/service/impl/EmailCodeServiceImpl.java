package com.cc.service.impl;

import com.cc.entity.po.EmailCode;
import com.cc.entity.query.EmailCodeQuery;
import com.cc.entity.vo.PaginationResultVO;
import com.cc.entity.query.SimplePage;
import com.cc.mappers.EmailCodeMapper;
import com.cc.enums.PageSize;
import com.cc.service.EmailCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

}