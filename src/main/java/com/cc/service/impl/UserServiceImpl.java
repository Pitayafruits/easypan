package com.cc.service.impl;

import com.cc.entity.po.User;
import com.cc.entity.query.UserQuery;
import com.cc.entity.vo.PaginationResultVO;
import com.cc.entity.query.SimplePage;
import com.cc.mappers.UserMapper;
import com.cc.enums.PageSize;
import com.cc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户信息表Service
 * @author: pitayafruit
 * @date: 2023/08/16
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper<User,UserQuery> userMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<User> findListByParam(UserQuery query) {
		return this.userMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public int findCountByParam(UserQuery query) {
		return this.userMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<User> findListByPage(UserQuery query) {
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<User> list = this.findListByParam(query);
		PaginationResultVO<User> result = new PaginationResultVO<>(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public int add(User bean) {
		return this.userMapper.insert(bean);
	}

	/**
	 * 新增或更新
	 */
	@Override
	public int addOrUpdate(User bean) {
		return this.userMapper.insertOrUpdate(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public int addBatch(List<User> listBean) {
		if(listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.userMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或更新
	 */
	@Override
	public int addOrUpdateBatch(List<User> listBean) {
		if(listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.userMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据UserId查询
	 */
	@Override
	public User getByUserId(String userId) {
		return this.userMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public int deleteByUserId(String userId) {
		return this.userMapper.deleteByUserId(userId);
	}

	/**
	 * 根据UserId更新
	 */
	@Override
	public int updateByUserId(User bean, String userId) {
		return this.userMapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据Email查询
	 */
	@Override
	public User getByEmail(String email) {
		return this.userMapper.selectByEmail(email);
	}

	/**
	 * 根据Email删除
	 */
	@Override
	public int deleteByEmail(String email) {
		return this.userMapper.deleteByEmail(email);
	}

	/**
	 * 根据Email更新
	 */
	@Override
	public int updateByEmail(User bean, String email) {
		return this.userMapper.updateByEmail(bean, email);
	}

	/**
	 * 根据QqOpenId查询
	 */
	@Override
	public User getByQqOpenId(String qqOpenId) {
		return this.userMapper.selectByQqOpenId(qqOpenId);
	}

	/**
	 * 根据QqOpenId删除
	 */
	@Override
	public int deleteByQqOpenId(String qqOpenId) {
		return this.userMapper.deleteByQqOpenId(qqOpenId);
	}

	/**
	 * 根据QqOpenId更新
	 */
	@Override
	public int updateByQqOpenId(User bean, String qqOpenId) {
		return this.userMapper.updateByQqOpenId(bean, qqOpenId);
	}

}