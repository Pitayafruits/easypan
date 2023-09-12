package com.cc.service.impl;

import com.cc.component.RedisComponent;
import com.cc.config.AppConfig;
import com.cc.entity.constants.Constants;
import com.cc.entity.dto.SessionWebUserDto;
import com.cc.entity.dto.SysSettingsDto;
import com.cc.entity.dto.UserSpaceDto;
import com.cc.entity.po.User;
import com.cc.entity.query.UserQuery;
import com.cc.entity.vo.PaginationResultVO;
import com.cc.entity.query.SimplePage;
import com.cc.enums.UserStatusEnum;
import com.cc.exception.BusinessException;
import com.cc.mappers.UserMapper;
import com.cc.enums.PageSize;
import com.cc.service.EmailCodeService;
import com.cc.service.UserService;
import com.cc.utils.StringTools;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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

	@Resource
	private EmailCodeService emailCodeService;

	@Resource
	private RedisComponent redisComponent;

	@Resource
	private AppConfig appConfig;

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

	/**
	 * 注册新用户
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void register(String email, String nickName, String password, String emailCode) throws BusinessException {
		//校验邮箱是否被注册
		User emailUser = userMapper.selectByEmail(email);
		if (emailUser != null){
			throw new BusinessException("该邮箱已被注册！");
		}
		//校验昵称是否存在
		User nickNameUser = userMapper.selectByNickName(nickName);
		if (nickNameUser != null){
			throw new BusinessException("该昵称已被占用");
		}
		//校验邮箱验证码
		emailCodeService.checkCode(email,emailCode);
		//设置用户信息
		String userId = StringTools.getRandomNumber(Constants.LENGTH_10);
		User user = new User();
		user.setUserId(userId);
		user.setNickName(nickName);
		user.setEmail(email);
		user.setPassword(StringTools.encodeByMd5(password));
		user.setJoinTime(new Date());
		user.setStatus(UserStatusEnum.ENABLE.getStatus());
		user.setUseSpace(0L);
		SysSettingsDto sysSettingsDto = redisComponent.getSysSettingsDto();
		user.setTotalSpace(sysSettingsDto.getUserInitUseSpace() * Constants.MB);
		userMapper.insert(user);
	}

	/**
	 * 用户登录
	 */
	@Override
	public SessionWebUserDto login(String email, String password) throws BusinessException {
		//校验账号密码是否错误
		User user = userMapper.selectByEmail(email);
		if (user == null || user.getPassword().equals(password)){
			throw new BusinessException("登陆失败，账号或密码错误！");
		}
		//检验账号是否被禁用
		if (user.getStatus().equals(UserStatusEnum.DISABLE.getStatus())){
			throw new BusinessException("登录失败，账号已被禁用！");
		}
		User loginUser = new User();
		loginUser.setLastJoinTime(new Date());
		userMapper.updateByUserId(loginUser, loginUser.getUserId());
		//创建返回dto
		SessionWebUserDto sessionWebUserDto = new SessionWebUserDto();
		sessionWebUserDto.setNickName(user.getNickName());
		sessionWebUserDto.setUserId(user.getUserId());
		if (ArrayUtils.contains(appConfig.getAdminEmails().split(":"),email)){
			sessionWebUserDto.setAdmin(true);
		}else{
			sessionWebUserDto.setAdmin(false);
		}
		//用户空间
		UserSpaceDto userSpaceDto = new UserSpaceDto();
		//TODO 文件表建好后查询数据库set
		//userSpaceDto.setUserSpace();
		userSpaceDto.setTotalSpace(user.getTotalSpace());
		redisComponent.saveUserSpaceUse(user.getUserId(),userSpaceDto);
		return null;
	}

}